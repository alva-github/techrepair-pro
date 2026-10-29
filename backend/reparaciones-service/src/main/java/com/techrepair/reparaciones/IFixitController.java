
package com.techrepair.reparaciones;

import com.techrepair.reparaciones.client.IFixitClient;
import com.techrepair.reparaciones.dto.DetalleGuiaIFixitDTO;
import com.techrepair.reparaciones.dto.GuiaIFixitDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/ifixit")
public class IFixitController {

    private final IFixitClient ifixitClient;

    public IFixitController(IFixitClient ifixitClient) {
        this.ifixitClient = ifixitClient;
    }

    @GetMapping(
            value = "/buscar",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GuiaIFixitDTO>> buscarGuias(
            @RequestParam String dispositivo) {

        List<GuiaIFixitDTO> resultado =
                ifixitClient.buscarGuias(dispositivo);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping(
            value = "/guias/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DetalleGuiaIFixitDTO> obtenerGuia(
            @PathVariable Long id) {

        DetalleGuiaIFixitDTO resultado =
                ifixitClient.obtenerDetalleGuia(id);

        return ResponseEntity.ok(resultado);
    }

}
