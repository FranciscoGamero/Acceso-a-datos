package com.salesianostriana.EjercicioEnClase2.service;

import com.salesianostriana.EjercicioEnClase2.model.Categoria;
import com.salesianostriana.EjercicioEnClase2.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public void createCategoria(Categoria c){
        categoriaRepository.save(c);
    }
    public void deleteCategoria(Categoria c){
        categoriaRepository.delete(c);
    }
    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }
    public Optional<Categoria> findById(Long id){
        return categoriaRepository.findById(id);
    }
}
