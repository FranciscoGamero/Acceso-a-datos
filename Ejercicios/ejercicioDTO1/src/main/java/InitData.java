package com.salesianostriana.dam.ejercicioDTO1;

import com.salesianostriana.dam.ejercicioDTO1.model.Alumno;
import com.salesianostriana.dam.ejercicioDTO1.model.Asignatura;
import com.salesianostriana.dam.ejercicioDTO1.model.Curso;
import com.salesianostriana.dam.ejercicioDTO1.model.TipoCurso;
import com.salesianostriana.dam.ejercicioDTO1.repository.AlumnoRepository;
import com.salesianostriana.dam.ejercicioDTO1.repository.AsignaturaRepository;
import com.salesianostriana.dam.ejercicioDTO1.repository.CursoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InitData {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostConstruct
    public void init() {

        // Crear cursos usando el enum TipoCurso
        Curso dam = Curso.builder()
                .nombre("Desarrollo de Aplicaciones Multiplataforma")
                .tipoCurso(TipoCurso.Superior)  // Usando el enum
                .build();
        cursoRepository.save(dam);

        Curso daw = Curso.builder()
                .nombre("Desarrollo de Aplicaciones Web")
                .tipoCurso(TipoCurso.Superior)  // Usando el enum
                .build();
        cursoRepository.save(daw);

        // Crear asignaturas
        Asignatura prog = Asignatura.builder()
                .nombre("Programación")
                .numHoras(120)
                .contenidos("Fundamentos de programación en Java")
                .curso(dam)  // Relación con curso
                .build();
        asignaturaRepository.save(prog);

        Asignatura bd = Asignatura.builder()
                .nombre("Bases de Datos")
                .numHoras(90)
                .contenidos("Modelado de bases de datos y SQL")
                .curso(dam)
                .build();
        asignaturaRepository.save(bd);

        // Crear alumnos
        Alumno juan = Alumno.builder()
                .nombre("Juan")
                .apellidos("Pérez García")
                .direccion("Calle Falsa 123")
                .telefono("123456789")
                .email("juan.perez@gmail.com")
                .fechaNacimiento(LocalDate.of(2000, 5, 15))
                .build();
        alumnoRepository.save(juan);

        Alumno maria = Alumno.builder()
                .nombre("María")
                .apellidos("López Martínez")
                .direccion("Avenida Principal 45")
                .telefono("987654321")
                .email("maria.lopez@gmail.com")
                .fechaNacimiento(LocalDate.of(1999, 8, 23))
                .build();
        System.out.println("maria impresa");
        alumnoRepository.save(maria);

        // Asignar alumnos a asignaturas
        juan.getListaAsignatura().add(prog);
        juan.getListaAsignatura().add(bd);
        maria.getListaAsignatura().add(prog);

        // Guardar cambios
        alumnoRepository.save(juan);
        alumnoRepository.save(maria);

        System.out.println("Datos iniciales cargados.");
    }
}
