package com.salesianostriana.dam.ejercicio_evaluacion1.service;
import com.salesianostriana.dam.ejercicio_evaluacion1.model.Place;
import com.salesianostriana.dam.ejercicio_evaluacion1.repository.PlaceRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public boolean comprobarId(Long id){
        return repositorioPlace.existsById(id);
    }
    public Place guardarLugar(Place lugar){
        return repositorioPlace.save(lugar);
    }
    public Place modificarLugar(Place lugarNuevo){
        return buscarPorId(lugarNuevo.getId()).map(lugarExistente -> {
            lugarExistente.setNombre(lugarNuevo.getNombre());
            lugarExistente.setTags(lugarNuevo.getTags());
            lugarExistente.setDireccion(lugarNuevo.getDireccion());
            lugarExistente.setDescripcion(lugarNuevo.getDescripcion());
            lugarExistente.setImagen(lugarNuevo.getImagen());
            lugarExistente.setLatitud(lugarNuevo.getLatitud());
            lugarExistente.setLongitud(lugarNuevo.getLongitud());
            return guardarLugar(lugarExistente);
        }).orElse(null);
    }
    public void eliminarLugar(Long id){
        if(comprobarId(id)){
            repositorioPlace.deleteById(id);
        }
    }
}
