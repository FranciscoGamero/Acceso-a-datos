package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.dto.ContactoPackage.ContactoDto;
import com.salesianostriana.ProyectoConecta.models.Profesor;

import java.util.List;


public record ProfesorDto(String nombre,
                          String apellidos,
                          String email,
                          String telefono,
                          String username,
                          List<ContactoDto> listaContacto,
                          List<CursoDto> listaCurso){

    public static ProfesorDto of (Profesor p){
        return new ProfesorDto(p.getNombre(),
                p.getApellidos(),
                p.getEmail(),
                p.getTelefono(),
                p.getUsuario().getUsername(),
                p.getListaContactos().stream().map(ContactoDto::of).toList(),
                p.getCursos().stream().map(CursoDto::of).toList());

    }
}
