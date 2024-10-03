package com.salesianostriana.dam.ejercicio_evaluacion1.controller;

import com.salesianostriana.dam.ejercicio_evaluacion1.model.Place;
import com.salesianostriana.dam.ejercicio_evaluacion1.service.PlaceService;
import com.salesianostriana.dam.ejercicio_evaluacion1.service.TagService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
// @RestController = @Controller + @ResponseBody en cada método
@RequiredArgsConstructor
@RequestMapping("/place/")
public class PlaceController {

    @Autowired
    private PlaceService servicioPlace;

    @GetMapping
    public ResponseEntity<List<Place>> mostrarLugares(){
        List<Place> encontrados = servicioPlace.buscarTodo();
        if(encontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> mostrarLugar(@PathVariable  Long id){
        if(servicioPlace.buscarPorId(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servicioPlace.buscarPorId(id).get());
    }
    @PostMapping("/place/")
    public ResponseEntity<Place> crearLugar(@RequestBody Place lugar){
        Place nuevoLugar = servicioPlace.guardarLugar(lugar);
        return ResponseEntity.ok(lugar);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Place> modificarLugar(@PathVariable Long id, @RequestBody Place lugar){
        Optional<Place> lugarExistente = servicioPlace.buscarPorId(id);
        if (lugarExistente.isPresent()){
            Place lugarActualizado = servicioPlace.modificarLugar(lugar);
            return ResponseEntity.ok(lugarActualizado);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Place> eliminarLugar(@PathVariable Long id){
        if(servicioPlace.comprobarId(id)){
            servicioPlace.eliminarLugar(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.noContent().build();
    }
}
