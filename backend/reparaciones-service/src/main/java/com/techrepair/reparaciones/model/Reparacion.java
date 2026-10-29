
package com.techrepair.reparaciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "reparaciones")
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dispositivo;
    private String averia;
    private String estado;

    public Reparacion() {
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setAveria(String averia) {
        this.averia = averia;
    }

    public Reparacion(Long id, String dispositivo,
                      String averia, String estado) {
        this.id = id;
        this.dispositivo = dispositivo;
        this.averia = averia;
        this.estado = estado;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }



    public Long getId() {
        return id;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public String getAveria() {
        return averia;
    }

    public String getEstado() {
        return estado;
    }

}
