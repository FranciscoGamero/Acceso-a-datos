package com.salesianostriana.ProyectoConecta.service;

import com.salesianostriana.ProyectoConecta.error.EmpresaNotFoundException;
import com.salesianostriana.ProyectoConecta.models.Contacto;
import com.salesianostriana.ProyectoConecta.models.Empresa;
import com.salesianostriana.ProyectoConecta.repository.EmpresaRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EntityManager entityManager;

    public Empresa guardarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> buscarTodos(Boolean borrado) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("empresaBorradaFiltro");
        filter.setParameter("isBorrado", borrado);
        List<Empresa> listaEmpresas =  empresaRepository.findAll();
        session.disableFilter("empresaBorradaFiltro");
        return listaEmpresas;
    }

    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
    }

    public Empresa editarEmpresa(Empresa empresa, Long empresaId) {
        return empresaRepository.findById(empresaId).map(old -> {
                    old.setCif(empresa.getCif());
                    old.setNombre(empresa.getNombre());
                    old.setDireccion(empresa.getDireccion());
                    old.setCoordenadas(empresa.getCoordenadas());
                    return empresaRepository.save(old);
                })
                .orElseThrow(() -> new EmpresaNotFoundException("No hay empresa con ID: "+ empresaId));
    }

    public void borrarEmpresaPorId(Long id) {
        empresaRepository.deleteById(id);
    }
}
