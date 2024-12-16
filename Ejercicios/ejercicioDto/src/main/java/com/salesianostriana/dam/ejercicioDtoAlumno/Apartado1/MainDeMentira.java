package com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1;


import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.dto.AlumnoDto;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.models.Alumno;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.models.Curso;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.models.Direccion;

public class MainDeMentira {

    public static void main(String[] args) {

        Curso curso1 = Curso.builder()
                .id(1L)
                .nombre("Programación Avanzada")
                .tipo("Teórico-Práctico")
                .tutor("Carlos Pérez")
                .aula("Aula 101")
                .build();

        Curso curso2 = Curso.builder()
                .id(2L)
                .nombre("Desarrollo Web")
                .tipo("Práctico")
                .tutor("Ana López")
                .aula("Laboratorio 202")
                .build();

        Curso curso3 = Curso.builder()
                .id(3L)
                .nombre("Bases de Datos")
                .tipo("Teórico")
                .tutor("José Martínez")
                .aula("Aula 303")
                .build();

        Direccion direccion1 = Direccion.builder()
                .id(1L)
                .tipoVia("Calle")
                .linea1("Gran Vía, 45")
                .linea2("Piso 3, Puerta B")
                .cp("28013")
                .poblacion(28079L)  // Ejemplo de código de población
                .provincia("Madrid")
                .build();

        Direccion direccion2 = Direccion.builder()
                .id(2L)
                .tipoVia("Avenida")
                .linea1("Avenida de la Constitución, 15")
                .linea2("Bloque 2, 1ºA")
                .cp("41004")
                .poblacion(41091L)  // Ejemplo de código de población
                .provincia("Sevilla")
                .build();

        Direccion direccion3 = Direccion.builder()
                .id(3L)
                .tipoVia("Plaza")
                .linea1("Plaza Mayor, 2")
                .linea2("Edificio Central")
                .cp("47001")
                .poblacion(47186L)  // Ejemplo de código de población
                .provincia("Valladolid")
                .build();

        Alumno alumno1 = Alumno.builder()
                .id(1L)
                .nombre("Juan")
                .apellido1("García")
                .apellido2("López")
                .telefono("612345678")
                .email("juan.garcia@example.com")
                .curso(curso1)
                .direccion(direccion1)
                .build();

        Alumno alumno2 = Alumno.builder()
                .id(2L)
                .nombre("María")
                .apellido1("Martínez")
                .apellido2("Fernández")
                .telefono("622345678")
                .email("maria.martinez@example.com")
                .curso(curso2)
                .direccion(direccion2)
                .build();

        Alumno alumno3 = Alumno.builder()
                .id(3L)
                .nombre("Pedro")
                .apellido1("Sánchez")
                .apellido2("Gómez")
                .telefono("632345678")
                .email("pedro.sanchez@example.com")
                .curso(curso3)
                .direccion(direccion3)
                .build();

        AlumnoDto dto1= AlumnoDto.of(alumno1);
        AlumnoDto dto2= AlumnoDto.of(alumno2);
        AlumnoDto dto3= AlumnoDto.of(alumno3);
    }
}
