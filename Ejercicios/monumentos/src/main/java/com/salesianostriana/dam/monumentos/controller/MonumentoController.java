package com.salesianostriana.dam.monumentos.controller;

import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.service.MonumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/monumento")
public class MonumentoController {

    private final MonumentoService monumentoService;

    @GetMapping
    public ResponseEntity<List<Monumento>> listaMonumentos() {
        List<Monumento> resultado = monumentoService.listarMonumentos();
        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monumento> obtenerMonumento(@PathVariable Long id) {
        return ResponseEntity.of(monumentoService.getMonumentoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Monumento> guardarMonumento(@RequestBody Monumento monumento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(monumentoService.crearMonumento(monumento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monumento> actualizarMonumento(@PathVariable Long id, @RequestBody Monumento monumento) {
        return ResponseEntity.of(monumentoService.editarMonumento(id, monumento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMonumento(@PathVariable Long id) {
        monumentoService.eliminarMonumento(id);
        return ResponseEntity.noContent().build();
    }
}
