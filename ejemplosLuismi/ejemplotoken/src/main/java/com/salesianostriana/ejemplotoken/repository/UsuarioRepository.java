package com.salesianostriana.ejemplotoken.repository;

import com.salesianostriana.ejemplotoken.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
