package com.salesianostriana.ejemplotoken.service;

import com.salesianostriana.ejemplotoken.dto.CrearUsuarioDto;
import com.salesianostriana.ejemplotoken.error.UsuarioNotFoundException;
import com.salesianostriana.ejemplotoken.model.RolUsuario;
import com.salesianostriana.ejemplotoken.model.Usuario;
import com.salesianostriana.ejemplotoken.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(CrearUsuarioDto dto, EnumSet<RolUsuario> roles){

        Usuario u = Usuario.builder()
                .username(dto.username())
                .password(dto.password())
                .avatar(dto.avatar())
                .fullName(dto.fullName())
                .roles(roles)
                .build();

        return usuarioRepository.save(u);
    }

    public Usuario crearUsuarioConRolUsuario(CrearUsuarioDto dto) {
        return crearUsuario(dto, EnumSet.of(RolUsuario.USER));
    }

    public Usuario crearUsuarioConRolAdmin(CrearUsuarioDto dto) {
        return crearUsuario(dto, EnumSet.of(RolUsuario.ADMIN));
    }


    public Usuario editarUsuario(Usuario usuario){
        return usuarioRepository.findById(usuario.getId())
                .map(u -> {
                    u.setAvatar(usuario.getAvatar());
                    u.setFullName(usuario.getFullName());
                    return usuarioRepository.save(u);
                }).orElseThrow(UsuarioNotFoundException::new);
    }

}
