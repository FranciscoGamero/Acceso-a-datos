package com.salesianostriana.jwt.security.user.dto;

public record CreateUserDto(
        String username,
        String password,
        String verifyPassword
) {
}
