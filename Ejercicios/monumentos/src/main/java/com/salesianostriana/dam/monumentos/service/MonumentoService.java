package com.salesianostriana.dam.monumentos.service;

import com.salesianostriana.dam.monumentos.error.MonumentoNotFoundException;
import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.repository.MonumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonumentoService {

    private final MonumentoRepository repository;

    public List<Monumento> listarMonumentos(){
        List<Monumento> result = repository.getAll();
        if (result.isEmpty())
            throw new MonumentoNotFoundException();
        return result;
    }

    public List<Monumento> ordenarPorNombre(String sortDirection) {
        List<Monumento> result = repository.query(sortDirection);
        if (result.isEmpty())
            throw new MonumentoNotFoundException();
        return result;
    }

    public Monumento getMonumentoPorId(Long id) {
        return repository.get(id)
                .orElseThrow(() -> new MonumentoNotFoundException(id));
    }

    public Monumento crearMonumento(Monumento product) {
        return repository.add(product);
    }

    public Monumento editarMonumento(Long id, Monumento newValue) {
        return repository.edit(id, newValue)
                .orElseThrow(() -> new MonumentoNotFoundException(id));
    }

    public void eliminarMonumento(Long id) {
        repository.delete(id);
    }
}
