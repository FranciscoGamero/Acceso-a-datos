package com.salesianostriana.EjercicioEnClase2.repository;

import com.salesianostriana.EjercicioEnClase2.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
