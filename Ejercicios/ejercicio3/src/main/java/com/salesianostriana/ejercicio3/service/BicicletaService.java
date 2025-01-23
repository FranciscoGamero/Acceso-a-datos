package com.salesianostriana.ejercicio3.service;

import com.salesianostriana.ejercicio3.error.BicicletaNotFoundException;
import com.salesianostriana.ejercicio3.model.Bicicleta;
import com.salesianostriana.ejercicio3.repository.BicicletaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BicicletaService {

    private final BicicletaRepository bicicletaRepository;

    public List<Bicicleta> getAllBicicletas(){
        return bicicletaRepository.findAll();
    }
    public Bicicleta getBicicleta(Long id){
        return bicicletaRepository.findById(id).orElseThrow(() -> new BicicletaNotFoundException(id));
    }

    public Bicicleta createBicicleta(Bicicleta bicicleta){
        return bicicletaRepository.save(bicicleta);
    }
    public Bicicleta editBicicleta(Long id, Bicicleta bicicleta){
        return bicicletaRepository.findById(id)
                .map(b -> {
                    b.setEstacion(bicicleta.getEstacion());
                    b.setEstado(bicicleta.isEstado());
                    b.setMarca(bicicleta.getMarca());
                    b.setModelo(bicicleta.getModelo());
                    b.setListaUsos(bicicleta.getListaUsos());
                    return bicicletaRepository.save(b);
                })
                .orElseThrow(() -> new BicicletaNotFoundException(id));
    }
    public void deleteBicicleta(Long id){
        bicicletaRepository.deleteById(id);
    }
}
