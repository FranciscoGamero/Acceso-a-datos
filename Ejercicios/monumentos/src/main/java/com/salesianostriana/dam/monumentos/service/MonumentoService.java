package com.salesianostriana.dam.monumentos.service;

import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.repository.MonumentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonumentoService {

    @Autowired
    private MonumentoRepository monumentoRepository;

    public List<Monumento> obtenerTodos() {
        return monumentoRepository.findAll();

    }

    public Optional<Monumento> obtenerPorId(Long id) {
        return monumentoRepository.findById(id);

    }
    public boolean comprobar(Long id){

        return monumentoRepository.existsById(id);
    }
    public Monumento guardar(Monumento monumento){ //Guarda y actualiza

        return monumentoRepository.save(monumento);
    }

    public Monumento modificarMonumento(Monumento monumento) {
        return obtenerPorId(monumento.getId())
                .map(existingMonumento -> {
                    existingMonumento.setNombreMonumento(monumento.getNombreMonumento());
                    existingMonumento.setCodPais(monumento.getCodPais());
                    existingMonumento.setNombrePais(monumento.getNombrePais());
                    existingMonumento.setNombreCiudad(monumento.getNombreCiudad());
                    existingMonumento.setDescripcionMonumento(monumento.getDescripcionMonumento());
                    existingMonumento.setLatitud(monumento.getLatitud());
                    existingMonumento.setLongitud(monumento.getLongitud());
                    return guardar(existingMonumento);
                }).orElseThrow(() -> new EntityNotFoundException("Monumento no encontrado"));
    }

    public void eliminarMonumento(Long id){
        if(obtenerPorId(id).isPresent())
            monumentoRepository.deleteById(id);
    }
}
