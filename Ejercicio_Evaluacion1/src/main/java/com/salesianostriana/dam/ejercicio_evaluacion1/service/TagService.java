package com.salesianostriana.dam.ejercicio_evaluacion1.service;

import com.salesianostriana.dam.ejercicio_evaluacion1.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository repositorioTag;

}
