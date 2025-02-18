package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.dto.TituloDto;
import com.salesianostriana.ProyectoConecta.error.TituloNotFoundException;
import com.salesianostriana.ProyectoConecta.models.FamiliaProfesional;
import com.salesianostriana.ProyectoConecta.models.Titulo;
import com.salesianostriana.ProyectoConecta.repository.FamiliaProfesionalRepository;
import com.salesianostriana.ProyectoConecta.repository.TituloRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TituloService {

    private final TituloRepository tituloRepository;
    private final EntityManager entityManager;
    private final FamiliaProfesionalService familiaProfesionalService;
    private final FamiliaProfesionalRepository familiaProfesionalRepository;



    public Titulo guardarTitulo (Titulo titulo){

        FamiliaProfesional fp = familiaProfesionalService.obtenerFamiliaProfesional(titulo.getFamiliaProfesional().getId());
        titulo.addFamiliaProfesional(fp);

        tituloRepository.save(titulo);
        familiaProfesionalService.agregarFamiliaProfesional(fp);
        return titulo;
    }

    public Titulo obtenerTituloPorId(Long id){
        return tituloRepository.findById(id).orElseThrow(() -> new TituloNotFoundException(id));
    }

    public List<Titulo> obtenerTitulos(boolean borrado){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("borradoTituloFilter");
        filter.setParameter("isBorrado", borrado );
        List<Titulo> listaTitulos =  tituloRepository.findAll();
        session.disableFilter("borradoTituloFilter");
        return listaTitulos;
    }


    public Titulo editarTitulo(Titulo titulo, Long id){
        return tituloRepository.findById(id).map(old -> {
            FamiliaProfesional fp = familiaProfesionalService.obtenerFamiliaProfesional(old.getFamiliaProfesional().getId());
            FamiliaProfesional fpNueva = familiaProfesionalService.obtenerFamiliaProfesional(titulo.getFamiliaProfesional().getId());
            old.setNombre(titulo.getNombre());
            old.setDuracion(titulo.getDuracion());
            old.setGrado(titulo.getGrado());

            old.removeFamiliaProfesional(fp);
            old.addFamiliaProfesional(fpNueva);
            familiaProfesionalService.editarFamiliaProfesional(fp, fp.getId());


            return tituloRepository.save(old);
        }).orElseThrow(() -> new TituloNotFoundException(id));
    }

    public void borrarTitulo(Long id){



        Titulo t = obtenerTituloPorId(id);
        FamiliaProfesional fp = familiaProfesionalService.obtenerFamiliaProfesional(t.getFamiliaProfesional().getId());

        t.removeFamiliaProfesional(t.getFamiliaProfesional());



        familiaProfesionalRepository.save(fp);
        tituloRepository.deleteById(id);
    }
}
