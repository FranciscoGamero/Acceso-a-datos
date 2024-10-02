package com.salesianostriana.dam.monumentos.service;

import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.repository.MonumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonumentoService {

    @Autowired
    private MonumentoRepository monumentoRepository;

    public Optional<Monumento> obtenerPorId(long id){
        return monumentoRepository.findById(id);
    }
    public Monumento guardar(Monumento monumento){ //Guarda y actualiza
        return monumentoRepository.save(monumento);
    }
    public Monumento modificarMonumento(Long id, Monumento monumento){
        return null;
    }
    public void eliminarMonumento(long id){
        monumentoRepository.deleteById(id);
    }
}
