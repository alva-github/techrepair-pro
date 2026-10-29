
package com.techrepair.reparaciones;

public record ReparacionEventoDTO(
        Long reparacionId,
        String dispositivo,
        String estadoAnterior,
        String estadoNuevo
) {
}
