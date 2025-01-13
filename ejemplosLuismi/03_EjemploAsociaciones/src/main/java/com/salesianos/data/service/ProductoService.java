package com.salesianos.data.service;

import com.salesianos.data.dtos.EditProductoCmd;
import com.salesianos.data.model.Producto;
import com.salesianos.data.repos.CategoriaRepository;
import com.salesianos.data.repos.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<Producto> findAll() {
        /*
            Obtener todos los productos
            Si la lista está vacía
                Lanzo excepción
         */
        List<Producto> result = productoRepository.findAll();
        if (result.isEmpty())
            throw new EntityNotFoundException("No hay productos con esos criterios de búsqueda");
        return result;
    }

    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No hay producto con ID: "+ id));
    }

    public Producto save(EditProductoCmd nuevo) {
        Producto productoCreado = Producto.builder()
                .nombre(nuevo.nombre())
                .precio(nuevo.precio())
                .descripcion(nuevo.descripcion())
                .build();
        productoCreado.addCategoria(categoriaRepository
                .findById(nuevo.categoriaId()).orElse(null));
        return productoRepository.save(productoCreado);
    }

    public Producto edit(EditProductoCmd producto, Long id) {
        return productoRepository.findById(id)
                .map(old -> {
                    old.setNombre(producto.nombre());
                    old.setDescripcion(producto.descripcion());
                    old.setPrecio(producto.precio());
                    old.eliminarCategoria(categoriaRepository.findById(producto.categoriaId()).orElse(null));
                    old.addCategoria(categoriaRepository.findById(producto.categoriaId()).orElse(null));
                    return productoRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay producto con ID: "+ id));

    }

    public void delete(Long id) {
       Optional<Producto> p = productoRepository.findById(id);
       if(p.isPresent()) {
           p.get().eliminarCategoria(p.get().getCategoria());
           productoRepository.deleteById(p.get().getId());
       }
    }


}
