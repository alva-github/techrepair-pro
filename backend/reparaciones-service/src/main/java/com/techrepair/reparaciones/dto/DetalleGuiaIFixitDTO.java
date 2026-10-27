
package com.techrepair.reparaciones.dto;

import java.util.List;

public record DetalleGuiaIFixitDTO(
        Long id,
        String titulo,
        String dificultad,
        List<String> herramientas,
        List<String> piezas,
        List<PasoIFixitDTO> pasos
) {
}
