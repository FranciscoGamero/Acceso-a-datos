package com.salesianostriana.dam.ejercicioDTO1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private TipoCurso tipoCurso;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy="curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asignatura> listaAsignaturas = new ArrayList<>();

    public void addAsignatura(Asignatura asig) {
        asig.setCurso(this);
        this.listaAsignaturas.add(asig);
    }


    public void removeAsignatura(Asignatura asig) {
        this.listaAsignaturas.remove(asig);
        asig.setCurso(null);
    }
}
