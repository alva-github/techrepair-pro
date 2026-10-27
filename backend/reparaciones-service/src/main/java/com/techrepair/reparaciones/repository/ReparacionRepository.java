package com.techrepair.reparaciones.repository;

import com.techrepair.reparaciones.model.Reparacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReparacionRepository
        extends JpaRepository<Reparacion, Long> {
}
