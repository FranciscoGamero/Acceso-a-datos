package com.salesianos.data.service;

import com.salesianos.data.dtos.EditCategoriaCmd;
import com.salesianos.data.dtos.EditProductoCmd;
import com.salesianos.data.model.Categoria;
import com.salesianos.data.model.Producto;
import com.salesianos.data.repos.CategoriaRepository;
import com.salesianos.data.repos.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ProductoService productoRepository;

    public List<Categoria> findAll() {
        /*
            Obtener todos los productos
            Si la lista está vacía
                Lanzo excepción
         */
        List<Categoria> result = categoriaRepository.findAll();
        if (result.isEmpty())
            throw new EntityNotFoundException("No hay categorias con esos criterios de búsqueda");
        return result;
    }

    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No hay categoria con ID: "+ id));
    }

    public Categoria save(EditCategoriaCmd nuevo) {

        return categoriaRepository.save(Categoria.builder()
                .nombreCategoria(nuevo.nombre())
                .build());
    }

    public Categoria edit(EditCategoriaCmd categoria, Long id) {
        return categoriaRepository.findById(id)
                .map(old -> {
                    old.setNombreCategoria(categoria.nombre());
                    if(categoria.listaProductos() != null) {
                    old.setListaProductos(categoria.listaProductos());
                    }
                    return categoriaRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay categoría con ID: "+ id));

    }

    public void delete(Long id) {

        Categoria categoria = categoriaRepository.findById(id).orElseGet(() -> categoriaRepository.findById(0L).get());

        Categoria sinCategoria = categoriaRepository.findById(0L).get();


        List<Producto> productos = new ArrayList<>(categoria.getListaProductos());


        productos.forEach(producto -> {
            producto.eliminarCategoria(categoria);
            producto.setCategoria(sinCategoria);
            productoRepository.edit(new EditProductoCmd(
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    sinCategoria.getId()
            ), producto.getId());
        });

        categoriaRepository.deleteById(id);
    }
}
