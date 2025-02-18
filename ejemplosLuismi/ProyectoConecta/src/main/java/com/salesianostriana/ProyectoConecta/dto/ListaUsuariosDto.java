package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Usuario;

import java.util.List;

public record ListaUsuariosDto(int cantidadUsuarios, List<UsuarioDto> List) {

    public static ListaUsuariosDto of (List<Usuario> listaUsuario){
        return new ListaUsuariosDto(listaUsuario.size(), listaUsuario.stream().map(UsuarioDto::of).toList());

    }

}
