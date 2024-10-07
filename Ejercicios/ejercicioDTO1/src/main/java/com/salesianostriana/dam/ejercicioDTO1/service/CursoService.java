package com.salesianostriana.dam.ejercicioDTO1.service;

import com.salesianostriana.dam.ejercicioDTO1.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository servicioCurso;
}
