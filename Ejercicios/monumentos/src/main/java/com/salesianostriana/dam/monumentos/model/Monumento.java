package com.salesianostriana.dam.monumentos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Monumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private long id;

    @Column(nullable = false, length = 2)
    private String codPais;
    @Column(nullable = false)
    private String nombrePais;
    @Column(nullable = false)
    private String nombreCiudad;
    @Column(nullable = false)
    private String latitud;
    @Column(nullable = false)
    private String longitud;
    @Column(nullable = false)
    private String nombreMonumento;
    @Column(nullable = false, length = 2000)
    private String descripcionMonumento;
    @Column(nullable = false)
    private String imagenMonumento;

}
