package com.salesianos.dataPrueba;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "productos")
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Poner el auto o no poner nada es lo mismo
    private Long id;

    private String nombre;
    private double precio;

}
