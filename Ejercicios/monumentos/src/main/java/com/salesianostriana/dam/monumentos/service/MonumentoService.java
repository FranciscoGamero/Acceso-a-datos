package com.salesianostriana.dam.monumentos.service;

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

    public Monumento crearMonumento(Monumento monumento) {
        return repository.add(monumento);
    }
    public List<Monumento> listarMonumentos() {
        return repository.getAll();
    }
    public Optional<Monumento> getMonumentoPorId(Long id) {
        return repository.get(id);
    }
    public Optional<Monumento> editarMonumento(Long id, Monumento monumentoActualizado) {
        return repository.edit(id, monumentoActualizado);
    }
    public void eliminarMonumento(Long id) {
        repository.delete(id);
    }
}
