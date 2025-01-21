package com.salesianostriana.EjercicioEnClase2.repository;

import com.salesianostriana.EjercicioEnClase2.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
