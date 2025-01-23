package com.salesianos.data.Ejemplo1.model;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehiculo {

    @Id
    @GeneratedValue
    private Long id;

    private int ruedas;
    private double capacidadDeposito;
    private int cv;
}
