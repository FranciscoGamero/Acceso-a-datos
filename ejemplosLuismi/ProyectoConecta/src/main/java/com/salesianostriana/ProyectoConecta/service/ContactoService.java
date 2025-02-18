package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.ContactoNotFoundException;
import com.salesianostriana.ProyectoConecta.error.TrabajadorNotFoundException;
import com.salesianostriana.ProyectoConecta.models.Contacto;
import com.salesianostriana.ProyectoConecta.models.Profesor;
import com.salesianostriana.ProyectoConecta.models.Trabajador;
import com.salesianostriana.ProyectoConecta.repository.ContactoRepository;
import com.salesianostriana.ProyectoConecta.repository.ProfesorRepository;
import com.salesianostriana.ProyectoConecta.repository.TrabajadorRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoService {

    private final ContactoRepository contactoRepository;
    private final ProfesorService profesorService;
    private final TrabajadorService trabajadorService;
    private final EntityManager entityManager;


    public Contacto guardarContacto(Contacto nuevo) {
        nuevo.setProfesor(profesorService.buscarPorId(nuevo.getContactoPK().getProfesor_id()));
        nuevo.setTrabajador(trabajadorService.buscarPorId(nuevo.getContactoPK().getTrabajador_id()));

        Profesor p = profesorService.buscarPorId(nuevo.getContactoPK().getProfesor_id());
        nuevo.addProfesor(p);


        contactoRepository.save(nuevo);
        profesorService.editarProfesor(p, p.getId());


        return nuevo;
    }
    public List<Contacto> mostrarTodos(Boolean borrado) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("contactoBorradoFiltro");
        filter.setParameter("isBorrado", borrado);
        List<Contacto> listaContactos =  contactoRepository.findAll();
        session.disableFilter("contactoBorradoFiltro");
        return listaContactos;
    }

    public Contacto findByIdAutogenerado(Long id) {
        return contactoRepository.findByIdAutogenerado(id).orElseThrow(() -> new ContactoNotFoundException(id));
    }

    public Contacto editarContacto(Contacto contacto, Long contactoId) {
        return contactoRepository.findByIdAutogenerado(contactoId).map(old -> {
                    Profesor p = profesorService.buscarPorId(contacto.getContactoPK().getProfesor_id());
                    old.setCanal(contacto.getCanal());
                    old.setFecha(contacto.getFecha());
                    old.setResumen(contacto.getResumen());

                    old.removeProfesor(old.getProfesor());

                    old.addProfesor(p);

                    return contactoRepository.save(old);
                })
                .orElseThrow(() -> new TrabajadorNotFoundException("No hay contacto con ID: "+ contactoId));
    }

    @Transactional
    public void borrarContactoPorId(Long id) {
        contactoRepository.deleteByIdAutogenerado(id);
    }

    public List<Contacto> buscarContactosPorNombreEmpresa(String nombreEmpresa){
        return contactoRepository.contactosPorEmpresa(nombreEmpresa);
    }
    public List<Contacto> buscarContactosPorNombreFamiliaProfesional(String nombreFamiliaProfesional){
        return contactoRepository.contactosPorFamiliaProfesional(nombreFamiliaProfesional);
    }

    public List<Contacto>buscarContactoPorCurso(String nombreCurso){
        return contactoRepository.contactosPorCurso(nombreCurso);
    }
}
