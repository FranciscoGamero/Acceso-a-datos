package com.salesianostriana.dam.ejercicioDTO1.model;

import jakarta.persistence.*;

@Entity
public class MatriculaNotas{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private long id;
    private MatriculaNotasPK matriculaNotas;
}
