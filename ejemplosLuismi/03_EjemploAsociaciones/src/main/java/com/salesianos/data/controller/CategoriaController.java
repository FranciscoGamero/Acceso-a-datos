package com.salesianos.data.controller;

import com.salesianos.data.dtos.EditCategoriaCmd;
import com.salesianos.data.dtos.EditProductoCmd;
import com.salesianos.data.dtos.GetCategoriaDto;
import com.salesianos.data.dtos.GetProductoDto;
import com.salesianos.data.model.Categoria;
import com.salesianos.data.model.Producto;
import com.salesianos.data.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public List<GetCategoriaDto> getAll() {
        return categoriaService.findAll()
                .stream()
                .map(GetCategoriaDto::of)
                .toList();
    }

    @GetMapping("/{id}")
    public GetCategoriaDto getById(@PathVariable Long id) {

        return GetCategoriaDto.of(categoriaService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody EditCategoriaCmd nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        categoriaService.save(nuevo));
    }

    @PutMapping("/{id}")
    public Categoria edit(@RequestBody EditCategoriaCmd aEditar,
                         @PathVariable Long id) {
        return categoriaService.edit(aEditar, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
