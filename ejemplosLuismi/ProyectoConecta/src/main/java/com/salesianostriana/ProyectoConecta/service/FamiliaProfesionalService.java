package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.FamiliaProfesionalNotFoundException;
import com.salesianostriana.ProyectoConecta.models.Empresa;
import com.salesianostriana.ProyectoConecta.models.FamiliaProfesional;

import com.salesianostriana.ProyectoConecta.models.Profesor;
import com.salesianostriana.ProyectoConecta.repository.FamiliaProfesionalRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FamiliaProfesionalService {

    private final FamiliaProfesionalRepository familiaProfesionalRepository;
    private final EntityManager entityManager;
    private  final EmpresaService empresaService;
    private final ProfesorService profesorService;


    public FamiliaProfesional agregarFamiliaProfesional(FamiliaProfesional fp) {
        Set<Empresa> listaEntera = fp.getListaEmpresas().stream()
                .map(e -> empresaService.buscarPorId(e.getId()))
                .collect(Collectors.toSet());

        fp.setListaEmpresas(listaEntera);

        fp.addEmpresas(listaEntera);
        familiaProfesionalRepository.save(fp);
        listaEntera.stream().map(empresaService::guardarEmpresa).close();
        return fp;
    }


    public FamiliaProfesional obtenerFamiliaProfesional (Long id){
        return familiaProfesionalRepository.findById(id).
                orElseThrow(() -> new FamiliaProfesionalNotFoundException(id));
    }


    public List<FamiliaProfesional>  obtenerFamiliasProfesionales(boolean borrado){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("borradofamiliaProfesionalFilter");
        filter.setParameter("isBorrado", borrado);
        List<FamiliaProfesional> listaFamiliasProfesionales =  familiaProfesionalRepository.findAll();
        session.disableFilter("borradofamiliaProfesionalFilter");
        return listaFamiliasProfesionales;
    }

    public FamiliaProfesional editarFamiliaProfesional(FamiliaProfesional fp, Long id) {
        return familiaProfesionalRepository.findById(id).map(old -> {


            old.setNombre(fp.getNombre());
            old.setTitulos(fp.getTitulos());

            Set<Empresa> listaEntera = fp.getListaEmpresas().stream()
                    .map(e -> empresaService.buscarPorId(e.getId())) // Busca cada empresa por su ID
                    .collect(Collectors.toSet());


            old.removeEmpresas(old.getListaEmpresas());
            old.addEmpresas(listaEntera);


            listaEntera.stream().map(empresaService::guardarEmpresa).close();

            return familiaProfesionalRepository.save(old);

        }).orElseThrow(() -> new FamiliaProfesionalNotFoundException(id));
    }


    @Transactional
    public void borrarFamiliaProfesional(Long id) {

        FamiliaProfesional fp = obtenerFamiliaProfesional(id);

        Set<Empresa> listaEntera = new HashSet<>(fp.getListaEmpresas());


        fp.removeEmpresas(listaEntera);


        listaEntera.forEach(empresaService::guardarEmpresa); // Solo si necesitas persistir cambios adicionales en empresas

        familiaProfesionalRepository.delete(fp);

    }



}
