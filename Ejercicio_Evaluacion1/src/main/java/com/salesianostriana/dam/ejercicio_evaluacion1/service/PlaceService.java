package com.salesianostriana.dam.ejercicio_evaluacion1.service;
import com.salesianostriana.dam.ejercicio_evaluacion1.model.Place;
import com.salesianostriana.dam.ejercicio_evaluacion1.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository repositorioPlace;

    public List<Place> buscarTodo(){
        return repositorioPlace.findAll();
    }
    public Optional<Place> buscarPorId(Long id){
        return repositorioPlace.findById(id);
    }
    public Place guardarLugar(Place lugar){
        return repositorioPlace.save(lugar);
    }
}
