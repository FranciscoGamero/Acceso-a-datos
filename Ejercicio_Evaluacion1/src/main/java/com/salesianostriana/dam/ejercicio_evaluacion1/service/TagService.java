package com.salesianostriana.dam.ejercicio_evaluacion1.service;


import com.salesianostriana.dam.ejercicio_evaluacion1.model.Tag;
import com.salesianostriana.dam.ejercicio_evaluacion1.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository repositorioTag;
    public boolean comprobarId(Long id){
        return repositorioTag.existsById(id);
    }
    public Optional<Tag> buscarPorId(Long id){
        return repositorioTag.findById(id);
    }
}
