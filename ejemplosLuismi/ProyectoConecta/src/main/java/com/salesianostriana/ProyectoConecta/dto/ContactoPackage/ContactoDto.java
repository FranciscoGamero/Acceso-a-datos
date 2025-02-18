package com.salesianostriana.ProyectoConecta.dto.ContactoPackage;

import com.salesianostriana.ProyectoConecta.models.Contacto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record ContactoDto(
        @NotEmpty
        @NotBlank
        String nombreProfesor,
        @NotEmpty
        @NotBlank
        String nombreTrabajador,
        @NotEmpty
        @NotBlank
        String resumen,
        @NotEmpty
        @NotBlank
        String canal,
        @Future
        LocalDateTime fecha
) {
    public static ContactoDto of(Contacto contacto) {
        return new ContactoDto(
                contacto.getProfesor().getNombre(),
                contacto.getTrabajador().getNombre(),
                contacto.getResumen(),
                contacto.getCanal(),
                contacto.getFecha()
        );
    }
}
