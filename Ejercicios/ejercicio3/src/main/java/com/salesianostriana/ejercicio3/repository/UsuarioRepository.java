package com.salesianostriana.ejercicio3.repository;

import com.salesianostriana.ejercicio3.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
