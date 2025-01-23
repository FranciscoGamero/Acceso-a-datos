package com.salesianos.data.Ejemplo2.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
public class Usuario {
    @Id
    @GeneratedValue
    private long id;

    private String nombreCompleto;
    private String contrasenia;
}
