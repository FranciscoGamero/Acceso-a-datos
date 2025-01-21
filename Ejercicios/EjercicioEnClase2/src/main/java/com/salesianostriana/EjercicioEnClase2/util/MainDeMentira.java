package com.salesianostriana.EjercicioEnClase2.util;

import com.salesianostriana.EjercicioEnClase2.model.Categoria;
import com.salesianostriana.EjercicioEnClase2.model.Producto;
import com.salesianostriana.EjercicioEnClase2.repository.CategoriaRepository;
import com.salesianostriana.EjercicioEnClase2.service.CategoriaService;
import com.salesianostriana.EjercicioEnClase2.service.ProductoService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class MainDeMentira {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final CategoriaRepository categoriaRepository;

    @PostConstruct
    public void run(){
        // Crear productos con nombre y Pvp

        Categoria subCategoria = Categoria.builder()
                .nombre("Accesorios Electrónicos")
                .build();
        categoriaService.createCategoria(subCategoria);

        Categoria categoria = Categoria.builder()
                .nombre("Tecnología")
                .build();

        categoria.addCategoria(subCategoria);
        categoriaService.createCategoria(categoria);

        Producto producto1 = Producto.builder()
                .nombre("Auriculares")
                .Pvp(20.00)
                .categoria(categoria)
                .build();

        Producto producto2 = Producto.builder()
                .nombre("Power Bank")
                .Pvp(19.99)
                .categoria(categoria)
                .build();
        Producto producto3 = Producto.builder()
                .nombre("Teclado")
                .Pvp(49.99)
                .categoria(categoria)
                .build();
        productoService.createProducto(producto1);
        productoService.createProducto(producto2);
        productoService.createProducto(producto3);

        System.out.println(producto1);
        System.out.println(producto2);
        System.out.println(producto3);

        categoria.addProducto(producto1);
        categoriaRepository.save(categoria);
        categoria.addProducto(producto2);
        categoriaRepository.save(categoria);
        categoria.addProducto(producto3);
        categoriaRepository.save(categoria);
    }
}
