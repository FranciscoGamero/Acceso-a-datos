package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.ProfesorNotFoundException;
import com.salesianostriana.ProyectoConecta.models.Profesor;
import com.salesianostriana.ProyectoConecta.models.Usuario;
import com.salesianostriana.ProyectoConecta.repository.ProfesorRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final EntityManager entityManager;
    private final UsuarioService usuarioService;


    public Profesor guardarProfesor(Profesor profesor) {

        Usuario u = Usuario.builder()
                .username(profesor.getNombre() +  '_' + profesor.getApellidos())
                .password("1234578")
                .role("ROLE_PROFESOR")
                .build();
        usuarioService.guardarUsuario(u);
        profesor.setUsuario(u);




        return profesorRepository.save(profesor);
    }



    public Profesor buscarPorId(Long id){
        return profesorRepository.findById(id).orElseThrow(() -> new ProfesorNotFoundException(id));
    }


    public List<Profesor> buscarTodos(boolean borrado){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("borradoProfesorFilter");
        filter.setParameter("isBorrado", borrado );
        List<Profesor> listaProfesor =  profesorRepository.findAll();
        session.disableFilter("borradoProfesorFilter");
        return listaProfesor;
    }

    public Profesor editarProfesor(Profesor profesor, Long id) {
        return profesorRepository.findById(id)
                .map(old -> {
                    old.setNombre(profesor.getNombre());
                    old.setApellidos(profesor.getApellidos());
                    old.setEmail(profesor.getEmail());
                    old.setTelefono(profesor.getTelefono());
                 return profesorRepository.save(old);
                })
                .orElseThrow(() -> new ProfesorNotFoundException("No hay Profesor con ID: "+ id));

    }

    public void borrarProfesor (Long id){
        profesorRepository.deleteById(id);
    }
}
