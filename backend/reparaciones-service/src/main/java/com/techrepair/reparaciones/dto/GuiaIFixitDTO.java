
package com.techrepair.reparaciones.dto;

public record GuiaIFixitDTO(
        Long id,
        String titulo,
        String dispositivo,
        String componente,
        String dificultad,
        String url
) {
}
