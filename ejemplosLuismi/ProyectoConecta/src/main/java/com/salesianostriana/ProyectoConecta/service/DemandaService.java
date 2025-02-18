package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.DemandaNotFoundException;
import com.salesianostriana.ProyectoConecta.models.*;
import com.salesianostriana.ProyectoConecta.repository.ConvocatoriaRepository;
import com.salesianostriana.ProyectoConecta.repository.DemandaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandaService {

    private final DemandaRepository demandaRepository;
    private final CursoService cursoService;
    private final EmpresaService empresaService;
    private final EntityManager entityManager;
    private final ConvocatoriaService convocatoriaService;

    @Transactional
    public Demanda guardarDemanda(Demanda nueva) {

        Empresa e = empresaService.buscarPorId(nueva.getEmpresa().getId());
        Curso c = cursoService.obtenerCursoPorId(nueva.getCurso().getId());
        Convocatoria con = convocatoriaService.buscarPorId(nueva.getConvocatoria().getId());

        nueva.addConvocatoria(con);
        nueva.addEmpresa(e);
        nueva.addCurso(c);

        demandaRepository.save(nueva);
        cursoService.guardarCurso(c);
        convocatoriaService.guardarConvocatoria(con);
        empresaService.guardarEmpresa(e);

        return nueva;
    }

    public List<Demanda> buscarTodos(boolean borrado) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("demandaBorradaFiltro");
        filter.setParameter("isBorrado", borrado);
        List<Demanda> listaDemanda =  demandaRepository.findAll();
        session.disableFilter("demandaBorradaFiltro");
        return listaDemanda;
    }

    public Demanda buscarPorId(Long id) {
        return demandaRepository.findById(id).orElseThrow(() -> new DemandaNotFoundException(id));
    }

    public List<Demanda> buscarPorNombreEmpresaYCurso(String nombreEmpresa, Long idCurso){
        return demandaRepository.demandasPorEmpresaYCurso(nombreEmpresa,idCurso);
    }

    public List<Demanda> buscarDemandasPorFamiliaProfesional(Long familiaId){
        return demandaRepository.demandasPorFamiliaProfesional(familiaId);
    }

    public Demanda editarDemanda(Demanda demanda, Long demandaId) {
        return demandaRepository.findById(demandaId).map(old -> {
            Empresa e = empresaService.buscarPorId(demanda.getEmpresa().getId());
            Curso c = cursoService.obtenerCursoPorId(demanda.getCurso().getId());
            Convocatoria con = convocatoriaService.buscarPorId(demanda.getConvocatoria().getId());

            old.setCantidadAlumnos(demanda.getCantidadAlumnos());
            old.setRequisitos(demanda.getRequisitos());

            old.removeCurso(old.getCurso());
            old.removeEmpresa(old.getEmpresa());
            old.removeConvocatoria(old.getConvocatoria());

            old.addConvocatoria(con);
            old.addCurso(c);
            old.addEmpresa(e);

            cursoService.guardarCurso(c);
            convocatoriaService.guardarConvocatoria(con);
            empresaService.guardarEmpresa(e);
            return demandaRepository.save(old);
        }).orElseThrow(() -> new DemandaNotFoundException("No hay demanda con ID: " + demandaId));
    }

    public void borrarDemandaPorId(Long id) {

        Demanda d = buscarPorId(id);

        Empresa e = empresaService.buscarPorId(d.getId());
        Curso c = cursoService.obtenerCursoPorId(d.getId());
        Convocatoria con = convocatoriaService.buscarPorId(d.getId());

        d.removeConvocatoria(con);
        d.removeEmpresa(e);
        d.removeCurso(c);


        cursoService.guardarCurso(c);
        convocatoriaService.guardarConvocatoria(con);
        empresaService.guardarEmpresa(e);
        demandaRepository.deleteById(id);
    }
}