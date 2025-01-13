package com.salesianos.data.service;

import com.salesianos.data.model.Categoria;
import com.salesianos.data.repos.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

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

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria edit(Categoria categoria, Long id) {
        return categoriaRepository.findById(id)
                .map(old -> {
                    old.setNombreCategoria(categoria.getNombreCategoria());
                    return categoriaRepository.save(old);
                })
                .orElseThrow(() -> new EntityNotFoundException("No hay categoria con ID: "+ id));

    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }
}
