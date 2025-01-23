package com.salesianostriana.ejercicio3.controller;


import com.salesianostriana.ejercicio3.model.Bicicleta;
import com.salesianostriana.ejercicio3.service.BicicletaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bicicleta")
@RequiredArgsConstructor
public class BicicletaController {

    private final BicicletaService bicicletaService;

    @GetMapping
    public List<Bicicleta> getListBicicleta() {
        return bicicletaService.getAllBicicletas();
    }
    @GetMapping("/{id}")
    public Bicicleta getBicicleta(@PathVariable Long id) {
        return bicicletaService.getBicicleta(id);
    }
    @PostMapping
    public ResponseEntity<Bicicleta> createBicicleta(@RequestBody Bicicleta bicicleta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bicicletaService.createBicicleta(bicicleta));
    }
    @PutMapping("/{id}")
    public Bicicleta updateBicicleta(@RequestBody Bicicleta bicicleta, @PathVariable Long id) {
        return bicicletaService.editBicicleta(id, bicicleta);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBicicleta(@PathVariable Long id) {
        bicicletaService.deleteBicicleta(id);
        return ResponseEntity.noContent().build();
    }
}
