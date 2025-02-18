package com.salesianostriana.ProyectoConecta.repository;

import com.salesianostriana.ProyectoConecta.models.Demanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DemandaRepository extends JpaRepository<Demanda, Long> {



    @Query("""
            SELECT d
            FROM Demanda d
            JOIN d.empresa e
            JOIN d.curso c
            WHERE d.curso.id = :cursoId
            AND e.nombre = :nombreEmpresa
            """)
    List<Demanda> demandasPorEmpresaYCurso(String nombreEmpresa, Long cursoId);


    @Query("""
            SELECT d
            FROM Demanda d
            JOIN d.empresa e
            JOIN e.listaFamilias familias
            WHERE familias.id = :familiaId
            
            """)
    List<Demanda> demandasPorFamiliaProfesional(Long familiaId);
}
