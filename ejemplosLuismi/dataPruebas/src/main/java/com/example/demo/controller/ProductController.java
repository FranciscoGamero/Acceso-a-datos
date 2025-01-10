package com.example.demo.controller;

import com.example.demo.dto.GetProductListDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductRepository;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "El controlador de productos")
public class ProductController {


    private final ProductRepository productoRepository;
    private final ProductService productService;

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
    @Operation(summary = "Obtiene todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado productos",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetProductListDto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                        [
                                            {"id": 1, "name": "Laptop", "price": 1234.56},
                                            {"id": 2, "name": "Smartphone", "price": 999.99}
                                        ]
                                        """
                            )})
                    }),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún producto",
                    content = @Content)
    })
@GetMapping
public List<Product> getAll(@RequestParam(required = false, value = "maxPrice", defaultValue = "-1") double max,
                                            @RequestParam(required = false, value = "sort", defaultValue = "no") String sortDirection){

    return productService.query(max, sortDirection);
}
    @PostMapping
    public ResponseEntity<Product> create (@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoRepository.add(product));
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.get(id);
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
