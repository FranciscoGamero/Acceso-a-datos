package com.salesianostriana.ejercicio3.repository;

import com.salesianostriana.ejercicio3.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
