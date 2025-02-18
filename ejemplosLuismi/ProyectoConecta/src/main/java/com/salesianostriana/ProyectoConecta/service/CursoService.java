package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.CursoNotFoundException;
import com.salesianostriana.ProyectoConecta.models.Curso;
import com.salesianostriana.ProyectoConecta.models.Profesor;
import com.salesianostriana.ProyectoConecta.models.Titulo;
import com.salesianostriana.ProyectoConecta.repository.CursoRepository;
import com.salesianostriana.ProyectoConecta.repository.TituloRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final EntityManager entityManager;
    private final TituloService tituloService;
    private final TituloRepository tituloRepository;
    private final ProfesorService profesorService;


    public Curso guardarCurso(Curso curso){

        Set<Profesor> listaEntera = curso.getProfesores().stream()
                .map(p -> profesorService.buscarPorId(p.getId()))
                .collect(Collectors.toSet());

        curso.setProfesores(listaEntera);


        Titulo t = tituloService.obtenerTituloPorId(curso.getTitulo().getId());
        curso.addTitulo(t);

        curso.addProfesor(listaEntera);


        listaEntera.stream().map(profesorService::guardarProfesor).close();
        tituloService.guardarTitulo(curso.getTitulo());
        cursoRepository.save(curso);

        return curso;
    }




    public Curso obtenerCursoPorId(Long id){
        return cursoRepository.findById(id).orElseThrow(() -> new CursoNotFoundException(id));
    }

    public List<Curso> obtenerCursos(boolean borrado){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("borradoCursoFilter");
        filter.setParameter("isBorrado", borrado );
        List<Curso> listaCurso =  cursoRepository.findAll();
        session.disableFilter("borradoCursoFilter");
        return listaCurso;
    }


    public Curso editarCurso(Curso curso, Long id){
        return cursoRepository.findById(id).map(old -> {

            Titulo t = tituloService.obtenerTituloPorId(old.getTitulo().getId());
            old.setNombre(curso.getNombre());
            old.setHorasEmpresa(curso.getHorasEmpresa());

            old.removeProfesor(old.getProfesores());
            old.removeTitulo(t);
            old.addTitulo(t);
            old.addProfesor(curso.getProfesores());
            tituloService.editarTitulo(t, t.getId());
            curso.getProfesores().stream().map(profesor -> profesorService.guardarProfesor(profesor).getId()).close();

            return cursoRepository.save(old);
        }).orElseThrow(() -> new CursoNotFoundException("No se pudo editar dicho curso"+id));
    }


    public void borrarCurso(Long id){

        Curso c = obtenerCursoPorId(id);
        Titulo t = tituloService.obtenerTituloPorId(c.getTitulo().getId());

        c.removeTitulo(c.getTitulo());
        c.removeProfesor(c.getProfesores());

        tituloRepository.save(t);
        c.getProfesores().stream().map(profesor -> profesorService.guardarProfesor(profesor).getId()).close();
        cursoRepository.deleteById(id);
    }


}
