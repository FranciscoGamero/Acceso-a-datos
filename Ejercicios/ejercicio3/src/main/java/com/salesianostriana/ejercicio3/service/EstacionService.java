package com.salesianostriana.ejercicio3.service;


import com.salesianostriana.ejercicio3.error.EstacionNotFoundException;
import com.salesianostriana.ejercicio3.model.Bicicleta;
import com.salesianostriana.ejercicio3.model.Estacion;
import com.salesianostriana.ejercicio3.model.Uso;
import com.salesianostriana.ejercicio3.repository.BicicletaRepository;
import com.salesianostriana.ejercicio3.repository.EstacionRepository;
import com.salesianostriana.ejercicio3.repository.UsoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstacionService {
    private final EstacionRepository estacionRepository;

    public List<Estacion> getAllEstaciones(){
        return estacionRepository.findAll();
    }
    public Estacion getEstacion(Long id){
        return estacionRepository.findById(id).orElseThrow(() -> new EstacionNotFoundException(id));
    }

    public Estacion createEstacion(Estacion estacion){
        return estacionRepository.save(estacion);
    }
    public Estacion editEstacion(Long id, Estacion estacion){
        return estacionRepository.findById(id)
                .map(e -> {
                    e.setCapacidad(estacion.getCapacidad());
                    e.setListaUsos(estacion.getListaUsos());
                    e.setListaBicicletas(estacion.getListaBicicletas());
                    e.setCoordenadas(estacion.getCoordenadas());
                    e.setNombre(estacion.getNombre());
                    e.setNumero(estacion.getNumero());

                    return estacionRepository.save(e);
                })
                .orElseThrow(() -> new EstacionNotFoundException(id));
    }
    public void deleteEstacion(Long id) {
        estacionRepository.deleteById(id);
    }

}
