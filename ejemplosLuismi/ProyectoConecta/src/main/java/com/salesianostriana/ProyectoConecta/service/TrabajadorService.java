package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.TrabajadorNotFoundException;
import com.salesianostriana.ProyectoConecta.models.Trabajador;
import com.salesianostriana.ProyectoConecta.repository.TrabajadorRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.hibernate.Filter;
import org.hibernate.Session;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final EntityManager entityManager;

    public Trabajador guardarTrabajador(Trabajador nuevo) {
        return trabajadorRepository.save(nuevo);
    }

    public Trabajador buscarPorId(Long id) {
        return trabajadorRepository.findById(id).orElseThrow(() -> new TrabajadorNotFoundException(id));
    }

    public List<Trabajador> buscarTodos(boolean borrado) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("trabajadorBorradoFiltro");
        filter.setParameter("isBorrado", borrado);
        List<Trabajador> listaTrabajadores =  trabajadorRepository.findAll();
        session.disableFilter("trabajadorBorradoFiltro");
        return listaTrabajadores;
    }

    public Trabajador editarTrabajador(Trabajador trabajador, Long id) {
        return trabajadorRepository.findById(id)
                .map(old -> {
                    old.setNombre(trabajador.getNombre());
                    old.setApellidos(trabajador.getApellidos());
                    old.setEmail(trabajador.getEmail());
                    old.setTelefono(trabajador.getTelefono());
                    old.setEmpresa(trabajador.getEmpresa());
                    old.setArea(trabajador.getArea());
                    old.setPuesto(trabajador.getPuesto());
                    return trabajadorRepository.save(old);
                })
                .orElseThrow(() -> new TrabajadorNotFoundException("No hay trabajador con ID: "+ id));

    }
    public void borrarTrabajadorPorId(Long trabajadorId){
        trabajadorRepository.deleteById(trabajadorId);
    }
}
