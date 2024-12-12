package com.salesianostriana.dam.ejercicioDTO1.service;


import com.salesianostriana.dam.ejercicioDTO1.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {
    @Autowired
    private AsignaturaRepository asignaturaRepository;
}
