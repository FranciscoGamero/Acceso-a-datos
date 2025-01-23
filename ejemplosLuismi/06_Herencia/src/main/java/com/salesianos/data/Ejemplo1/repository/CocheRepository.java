package com.salesianos.data.Ejemplo1.repository;

import com.salesianos.data.Ejemplo1.model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CocheRepository extends JpaRepository<Coche, Long> {
}
