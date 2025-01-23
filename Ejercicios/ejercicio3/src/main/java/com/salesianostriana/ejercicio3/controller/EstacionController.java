package com.salesianostriana.ejercicio3.controller;

import com.salesianostriana.ejercicio3.model.Bicicleta;
import com.salesianostriana.ejercicio3.model.Estacion;
import com.salesianostriana.ejercicio3.repository.EstacionRepository;
import com.salesianostriana.ejercicio3.service.EstacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/estacion")
@RequiredArgsConstructor
public class EstacionController {

    private final EstacionService estacionService;

    @GetMapping
    public List<Estacion> getListEstaciones() {
        return estacionService.getAllEstaciones();
    }
    @GetMapping("/{id}")
    public Estacion getEstacion(@PathVariable Long id) {
        return estacionService.getEstacion(id);
    }
    @PostMapping
    public ResponseEntity<Estacion> createBicicleta(@RequestBody Estacion estacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionService.createEstacion(estacion));
    }
    @PutMapping("/{id}")
    public Estacion updateBicicleta(@RequestBody Estacion estacion, @PathVariable Long id) {
        return estacionService.editEstacion(id, estacion);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBicicleta(@PathVariable Long id) {
        estacionService.deleteEstacion(id);
        return ResponseEntity.noContent().build();
    }
}
