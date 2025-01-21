package com.salesianostriana.EjercicioEnClase2.service;

import com.salesianostriana.EjercicioEnClase2.model.Producto;
import com.salesianostriana.EjercicioEnClase2.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductoService {
    private final ProductoRepository productoRepository;

    public void createProducto(Producto p){
        productoRepository.save(p);
    }
    public void deleteProducto(Producto p){
        productoRepository.delete(p);
    }
    public List<Producto> findAll(){
        return productoRepository.findAll();
    }
    public Optional<Producto> findById(Long id){
        return productoRepository.findById(id);
    }
}
