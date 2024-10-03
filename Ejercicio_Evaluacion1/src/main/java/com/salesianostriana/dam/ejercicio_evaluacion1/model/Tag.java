package com.salesianostriana.dam.ejercicio_evaluacion1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String nombre;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private List<Place> listaPlaces;
}
