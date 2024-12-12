package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
public class ProductController {


    private final ProductRepository productoRepository;

/*
    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        /*
        1. Obtener del repositorio la lista de productos
        2. Si la lista está vacía, devolver 404
        3. Si la lista tiene productos, devolver 200 con la lista
        *
        List<Product> resultado = productoRepository.getAll();
        if (resultado.isEmpty()){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(resultado);
    }
    */
@GetMapping
public ResponseEntity<List<Product>> getAll(@RequestParam(required = false, value = "maxPrice", defaultValue = "-1") double max,
                                            @RequestParam(required = false, value = "sort", defaultValue = "no") String sortDirection){
    List<Product> result = productoRepository.query(max, sortDirection);
    if (result.isEmpty())
        return ResponseEntity.notFound().build();
    else {
        return ResponseEntity.ok(result);
    }
}
    @PostMapping
    public ResponseEntity<Product> create (@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoRepository.add(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return ResponseEntity.of(productoRepository.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        return ResponseEntity.of(productoRepository.edit(id, product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
            productoRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
