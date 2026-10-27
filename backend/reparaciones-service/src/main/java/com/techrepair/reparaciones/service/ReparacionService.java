
package com.techrepair.reparaciones.service;

import com.techrepair.reparaciones.MqttPublisher;
import com.techrepair.reparaciones.model.Reparacion;
import com.techrepair.reparaciones.repository.ReparacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReparacionService {

    private final ReparacionRepository repository;
    private final MqttPublisher mqttPublisher;

    public ReparacionService(ReparacionRepository repository, MqttPublisher mqttPublisher) {
        this.repository = repository;
        this.mqttPublisher = mqttPublisher;
    }

    // Obtener todas las reparaciones
    public List<Reparacion> obtenerReparaciones() {
        return repository.findAll();
    }

    // Buscar una reparación por su ID
    public Reparacion buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Registrar una nueva reparación
    public Reparacion registrarReparacion(Reparacion nuevaReparacion) {
        return repository.save(nuevaReparacion);
    }

    // Actualizar el estado de una reparación
    public Reparacion actualizarReparacion(Long id, String nuevoEstado) {

        // Buscamos la reparación en la base de datos.
        Reparacion reparacion = buscarPorId(id);

        if (reparacion == null) {
            return null;
        }

        // Guardamos el estado anterior.
        String estadoAnterior = reparacion.getEstado();

        // Actualizamos el estado.
        reparacion.setEstado(nuevoEstado);

        // Guardamos el cambio en PostgreSQL.
        Reparacion guardada = repository.save(reparacion);

        // Solo notificamos si el estado ha cambiado.
        if (!java.util.Objects.equals(estadoAnterior, nuevoEstado)) {

            String mensaje = "Reparacion " + id
                    + ": " + nuevoEstado;

            // Publicamos el evento MQTT.
            mqttPublisher.publicar(mensaje);
        }

        return guardada;
    }


    // Actualizar la avería
    public Reparacion actualizarAveria(Long id, String averia) {

        Reparacion reparacion = buscarPorId(id);

        if (reparacion == null) {
            return null;
        }

        reparacion.setAveria(averia);

        return repository.save(reparacion);
    }

    // Eliminar una reparación
    public boolean eliminarReparacion(Long id) {

        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);

        return true;
    }
}
