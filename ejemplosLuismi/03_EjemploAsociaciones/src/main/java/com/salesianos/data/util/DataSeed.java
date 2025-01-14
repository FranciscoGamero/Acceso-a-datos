package com.salesianos.data.util;

import com.salesianos.data.model.Categoria;
import com.salesianos.data.model.Producto;
import com.salesianos.data.repos.CategoriaRepository;
import com.salesianos.data.repos.ProductoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @PostConstruct
    public void run() {

        Optional<Categoria> c = categoriaRepository.findById(1L);
        
        if (c.isPresent()) {
            Producto p = Producto.builder()
                    .nombre("Un producto")
                    .descripcion("Se trata de un producto de nuestro catálogo")
                    .precio(123.45)
                    .categoria(c.get())
                    .build();


            productoRepository.save(p);

            Producto p2 = Producto.builder()
                    .nombre("Otro producto")
                    .descripcion("Verás como tiene ID 3")
                    .precio(234.56)
                    .categoria(c.get())
                    .build();
            productoRepository.saveAll(List.of(p, p2));

        }
    }

}

