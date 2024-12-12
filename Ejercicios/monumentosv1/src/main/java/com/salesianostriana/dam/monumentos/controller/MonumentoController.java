package com.salesianostriana.dam.monumentos.controller;
import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.repository.MonumentoRepository;
import com.salesianostriana.dam.monumentos.service.MonumentoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequiredArgsConstructor
@RequestMapping("/api/monumentos")
public class MonumentoController {

    @Autowired
    private MonumentoService monumentoService;

    @GetMapping
    public ResponseEntity<List<Monumento>> listaMonumentos() {
        List<Monumento> encontrados = monumentoService.obtenerTodos();
        if (encontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrados);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Monumento> obtenerMonumento(@PathVariable Long id) {
        return ResponseEntity.of(monumentoService.obtenerPorId(id));
    }
    @PostMapping
    public ResponseEntity<Monumento> crearMonumento(@RequestBody Monumento monumento) {
        Monumento monumentoNuevo = monumentoService.guardar(monumento);
        return ResponseEntity.status(201).body(monumentoNuevo);
    }
@PutMapping("/{id}")
public ResponseEntity<Monumento> actualizarMonumento(@PathVariable Long id, @RequestBody Monumento monumento) {
    Optional<Monumento> monumentoExistente = monumentoService.obtenerPorId(id);
    if (monumentoExistente.isPresent()) {
        Monumento actualizado = monumentoService.modificarMonumento(monumento);
        return ResponseEntity.ok(actualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarMonumento(@PathVariable Long id) {
       if(monumentoService.comprobar(id)){
           monumentoService.eliminarMonumento(id);
           return ResponseEntity.status(204).build();
       }
       return ResponseEntity.noContent().build();
    }
}
