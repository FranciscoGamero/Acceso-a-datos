package com.salesianos.data.Ejemplo2.repository;

import com.salesianos.data.Ejemplo2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
