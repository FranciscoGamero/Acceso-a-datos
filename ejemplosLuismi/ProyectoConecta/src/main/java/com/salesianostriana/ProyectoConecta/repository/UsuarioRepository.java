package com.salesianostriana.ProyectoConecta.repository;

import com.salesianostriana.ProyectoConecta.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
