package com.salesianostriana.dam.ejercicioDTO1.repository;

import com.salesianostriana.dam.ejercicioDTO1.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
