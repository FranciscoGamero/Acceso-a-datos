package com.salesianostriana.ProyectoConecta.repository;

import com.salesianostriana.ProyectoConecta.models.Convocatoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConvocatoriaRepository extends JpaRepository<Convocatoria,Long> {



    @Query("""
            SELECT co
            FROM Convocatoria co
            JOIN co.listaDemandas demandas
            JOIN demandas.curso c
            WHERE c.id = :CursoId
            
            """)
    List<Convocatoria> obtenerConvocatoriasPorCurso(Long CursoId);
}
