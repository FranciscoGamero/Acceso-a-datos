package com.salesianosTriana.EjercicioEnClase22.util;

import com.salesianosTriana.EjercicioEnClase22.model.CursoOnline;
import com.salesianosTriana.EjercicioEnClase22.model.Profesor;
import com.salesianosTriana.EjercicioEnClase22.model.Video;
import com.salesianosTriana.EjercicioEnClase22.repository.CursoOnlineRepository;
import com.salesianosTriana.EjercicioEnClase22.repository.ProfesorRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MainDeMentira {
    private final ProfesorRepository profesorRepository;
    private final CursoOnlineRepository cursoOnlineRepository;

    @PostConstruct
    public void run(){
        Profesor profesor1 = Profesor.builder()
                .nombre("Juan Pérez")
                .email("juan.perez@example.com")
                .puntuacion(4.5)
                .build();

        Profesor profesor2 = Profesor.builder()
                .nombre("Ana García")
                .email("ana.garcia@example.com")
                .puntuacion(4.9)
                .build();

        Profesor profesor3 = Profesor.builder()
                .nombre("Carlos López")
                .email("carlos.lopez@example.com")
                .puntuacion(4.2)
                .build();

        profesorRepository.saveAll(List.of(profesor1,profesor2,profesor3));

        CursoOnline curso1 = CursoOnline.builder()
                .nombre("Desarrollo Web con Spring")
                .Pvp(199.99)
                .profesor(profesor1) // Asociando el curso al profesor 1
                .build();

        curso1.addVideo(Video.builder()
                .curso(curso1)
                .orden("1")
                .titulo("Introducción a Spring")
                .descripcion("Introducción al framework Spring y su configuración inicial")
                .url("https://example.com/introduccion-spring")
                .build());

        curso1.addVideo(Video.builder()
                .curso(curso1)
                .orden("2")
                .titulo("Controladores en Spring")
                .descripcion("Cómo manejar controladores en Spring y crear rutas REST")
                .url("https://example.com/controladores-spring")
                .build());

        CursoOnline curso2 = CursoOnline.builder()
                .nombre("Programación en Python")
                .Pvp(149.99)
                .profesor(profesor2)
                .build();

        curso2.addVideo(Video.builder()
                .curso(curso2)
                .orden("1")
                .titulo("Instalación de Python")
                .descripcion("Cómo instalar Python en tu sistema operativo")
                .url("https://example.com/instalacion-python")
                .build());

        CursoOnline curso3 = CursoOnline.builder()
                .nombre("Inteligencia Artificial con Python")
                .Pvp(299.99)
                .profesor(profesor1)
                .build();

        curso3.addVideo(Video.builder()
                .curso(curso2)
                .orden("2")
                .titulo("Estructuras de datos en Python")
                .descripcion("Aprende las estructuras de datos básicas en Python")
                .url("https://example.com/estructuras-python")
                .build());

        cursoOnlineRepository.saveAll(List.of(curso1,curso2,curso3));

        profesor1.addCurso(curso1);
        profesor2.addCurso(curso2);
        profesor2.addCurso(curso3);
        profesorRepository.saveAll(List.of(profesor1,profesor2));

        System.out.println("Curso1: "+curso1);
        System.out.println("Curso2: "+curso2);
        System.out.println("Curso3: "+curso3);

        cursoOnlineRepository.delete(curso1);
    }
}

