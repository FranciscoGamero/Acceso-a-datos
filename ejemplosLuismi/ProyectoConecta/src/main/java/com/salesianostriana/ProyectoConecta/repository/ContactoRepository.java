package com.salesianostriana.ProyectoConecta.repository;

import com.salesianostriana.ProyectoConecta.models.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    @Query("""
        select c 
        from Contacto c
        where c.contactoPK.id_autogenerado = :id
       """)
    Optional<Contacto> findByIdAutogenerado(@Param("id") Long id);

    @Modifying
    @Query("""
        delete from Contacto c
        where c.contactoPK.id_autogenerado = :id
       """)
    void deleteByIdAutogenerado(@Param("id") Long id);

    @Query("""
            SELECT c 
            FROM Contacto c 
            WHERE c.trabajador.empresa.nombre = :nombreEmpresa
            """)
    List<Contacto> contactosPorEmpresa(String nombreEmpresa);

    @Query("""
            SELECT c 
            FROM Contacto c 
            JOIN c.trabajador t
            JOIN t.empresa e
            JOIN e.listaFamilias list
            WHERE list.nombre = :nombreFamiliaProfesional
            """)
    List<Contacto> contactosPorFamiliaProfesional(String nombreFamiliaProfesional);


    @Query("""
            SELECT c
            FROM Contacto c
            JOIN c.profesor p
            JOIN p.cursos curso
            WHERE curso.nombre = :nombreCurso 
            
            
            """)
    List<Contacto> contactosPorCurso(String nombreCurso);


}
