package com.salesianostriana.ejemplotoken.dto;

import com.salesianostriana.ejemplotoken.model.Usuario;

public record CrearUsuarioDto(
        String username,
        String password,
        String verifyPassword,
        String avatar,
        String fullName
){}
