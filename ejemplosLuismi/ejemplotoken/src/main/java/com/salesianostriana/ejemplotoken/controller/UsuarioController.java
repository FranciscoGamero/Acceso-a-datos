package com.salesianostriana.ejemplotoken.controller;

import com.salesianostriana.ejemplotoken.dto.CrearUsuarioDto;
import com.salesianostriana.ejemplotoken.model.Usuario;
import com.salesianostriana.ejemplotoken.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping("/nuevoUsuario")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody CrearUsuarioDto u){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuarioConRolUsuario(u));
    }

    @PostMapping("/nuevoAdmin")
    public ResponseEntity<Usuario> crearAdmin(@RequestBody CrearUsuarioDto u){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuarioConRolAdmin(u));
    }

    @PutMapping("/{id}")
    public Usuario editarUsuario(@RequestBody Usuario u){
        return usuarioService.editarUsuario(u);
    }
}
