
package com.techrepair.reparaciones.client;

import com.techrepair.reparaciones.dto.DetalleGuiaIFixitDTO;
import com.techrepair.reparaciones.dto.GuiaIFixitDTO;
import com.techrepair.reparaciones.dto.PasoIFixitDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class IFixitClient {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public IFixitClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        this.restClient = RestClient.builder()
                .baseUrl("https://www.ifixit.com/api/2.0")
                .build();
    }

    public List<GuiaIFixitDTO> buscarGuias(String dispositivo) {

        String json = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/suggest/{query}")
                        .queryParam("doctypes", "guide")
                        .build(dispositivo))
                .retrieve()
                .body(String.class);

        List<GuiaIFixitDTO> guias = new ArrayList<>();

        if (json == null || json.isBlank()) {
            return guias;
        }

        JsonNode raiz = objectMapper.readTree(json);
        JsonNode resultados = raiz.path("results");

        if (!resultados.isArray()) {
            return guias;
        }

        for (JsonNode guia : resultados) {

            GuiaIFixitDTO dto = new GuiaIFixitDTO(
                    guia.path("guideid").asLong(),
                    guia.path("title").asText(),
                    guia.path("category").asText(),
                    guia.path("subject").asText(),
                    guia.path("difficulty").asText(),
                    guia.path("url").asText()
            );

            guias.add(dto);
        }

        return guias;
    }

    public String obtenerGuia(Long id) {

        return restClient.get()
                .uri("/guides/{id}", id)
                .retrieve()
                .body(String.class);
    }


    public DetalleGuiaIFixitDTO obtenerDetalleGuia(Long id) {

        // Reutilizamos la petición GET que ya funciona.
        String json = obtenerGuia(id);
        JsonNode raiz = objectMapper.readTree(json);

        // Extraemos los nombres de las herramientas.
        List<String> herramientas = new ArrayList<>();
        for (JsonNode herramienta : raiz.path("tools")) {
            herramientas.add(herramienta.path("text").asText());
        }

        // Extraemos los nombres de las piezas.
        List<String> piezas = new ArrayList<>();
        for (JsonNode pieza : raiz.path("parts")) {
            piezas.add(pieza.path("text").asText());
        }

        // Extraemos los pasos y sus instrucciones.
        List<PasoIFixitDTO> pasos = new ArrayList<>();
        int numero = 1;

        for (JsonNode paso : raiz.path("steps")) {

            StringBuilder descripcion = new StringBuilder();

            for (JsonNode linea : paso.path("lines")) {
                if (!descripcion.isEmpty()) {
                    descripcion.append("\n");
                }
                descripcion.append(linea.path("text_raw").asText());
            }

            pasos.add(new PasoIFixitDTO(
                    numero++,
                    paso.path("title").asText(),
                    descripcion.toString()
            ));
        }

        return new DetalleGuiaIFixitDTO(
                raiz.path("guideid").asLong(),
                raiz.path("title").asText(),
                raiz.path("difficulty").asText(),
                herramientas,
                piezas,
                pasos
        );
    }

}
