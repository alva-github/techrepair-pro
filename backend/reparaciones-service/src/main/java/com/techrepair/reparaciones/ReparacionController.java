
package com.techrepair.reparaciones;

import com.techrepair.reparaciones.model.Reparacion;
import com.techrepair.reparaciones.service.ReparacionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {

    private final ReparacionService reparacionService;

    public ReparacionController(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }

    @GetMapping
    public List<Reparacion> obtenerReparaciones() {
        return reparacionService.obtenerReparaciones();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Reparacion> obtenerPorId(@PathVariable Long id) {

        Reparacion reparacion = (Reparacion) reparacionService.buscarPorId(id);

        if (reparacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reparacion);
    }


    @PostMapping
    public Reparacion registrarReparacion(@RequestBody Reparacion nuevaReparacion) {

        return reparacionService.registrarReparacion(nuevaReparacion);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Reparacion> actualizarReparacion(
            @PathVariable Long id,
            @RequestBody String nuevoEstado) {

        Reparacion reparacion =
                reparacionService.actualizarReparacion(id, nuevoEstado);

        if (reparacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reparacion);
    }

    @PutMapping("/{id}/averia")
    public ResponseEntity<Reparacion> actualizarAveria(
            @PathVariable Long id,
            @RequestBody String nuevaAveria) {

        Reparacion averia =
                reparacionService.actualizarAveria(id, nuevaAveria);

        if (averia == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(averia);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarItem(@PathVariable Long id) {

        boolean borrar = reparacionService.eliminarReparacion(id);

        if (borrar) {

            System.out.println("Item " + id + " borrado correctamente");

            return ResponseEntity.noContent().build();// envia notificacion a http

        } else {

            System.out.println("No existe el item " + id);

            return ResponseEntity.notFound().build();// envía notificación a http
        }
    }


    @PutMapping("/{id}/dispositivo")
    public ResponseEntity<Reparacion> actualizarDispositivo(
            @PathVariable Long id,
            @RequestBody String nuevoDispositivo) {

        Reparacion reparacion =
                reparacionService.actualizarDispositivo(id, nuevoDispositivo);

        if (reparacion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reparacion);
    }



}
