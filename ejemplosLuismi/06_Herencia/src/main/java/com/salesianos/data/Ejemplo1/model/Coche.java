package com.salesianos.data.Ejemplo1.model;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@ToString(callSuper = true)
public class Coche extends Vehiculo{

    private int puertas;
    private double espacioMaletero;
}
