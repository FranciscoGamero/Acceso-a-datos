package com.salesianostriana.dam.monumentos.repository;

import com.salesianostriana.dam.monumentos.model.Monumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonumentoRepository extends JpaRepository<Monumento, Long> {
}
