package com.salesianostriana.dam.ejercicioDTO1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private long id;
    private String nombre;
    private int numHoras;
    private String contenidos;
    @ManyToMany(mappedBy = "listaAsignatura", fetch = FetchType.EAGER)
    private List<Alumno> listaAlumnos;

    @ManyToOne
    private Curso curso;
}
