package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.ConvocatoriaNotFoundException;
import com.salesianostriana.ProyectoConecta.models.Convocatoria;
import com.salesianostriana.ProyectoConecta.models.Demanda;
import com.salesianostriana.ProyectoConecta.repository.ConvocatoriaRepository;
import com.salesianostriana.ProyectoConecta.repository.DemandaRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvocatoriaService {

    private final ConvocatoriaRepository convocatoriaRepository;
    private final EntityManager entityManager;

    public Convocatoria guardarConvocatoria(Convocatoria convocatoria) {
        return convocatoriaRepository.save(convocatoria);
    }

    public List<Convocatoria> obtenerConvocatoriasPorCurso(Long cursoId){
        return convocatoriaRepository.obtenerConvocatoriasPorCurso(cursoId);
    }

    public List<Convocatoria> buscarConvocatorias(boolean borrado) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("convocatoriaBorradaFiltro");
        filter.setParameter("isBorrado", borrado);
        List<Convocatoria> listaConvocatoria =  convocatoriaRepository.findAll();
        session.disableFilter("convocatoriaBorradaFiltro");
        return listaConvocatoria;
    }

    public Convocatoria buscarPorId(Long id) {
        return convocatoriaRepository.findById(id).orElseThrow(() -> new ConvocatoriaNotFoundException(id));
    }

    public Convocatoria editarConvocatoria(Convocatoria convocatoria, Long id){
        return convocatoriaRepository.findById(id).map(old -> {
            old.setNombre(convocatoria.getNombre());
            old.setCursoEscolar(convocatoria.getCursoEscolar());
            return convocatoriaRepository.save(old);
        }).orElseThrow(() -> new ConvocatoriaNotFoundException("No se pudo editar la convocatoria:  "+id));
    }

    public void borrarConvocatoriaPorId(Long id) {
        convocatoriaRepository.deleteById(id);
    }
}
