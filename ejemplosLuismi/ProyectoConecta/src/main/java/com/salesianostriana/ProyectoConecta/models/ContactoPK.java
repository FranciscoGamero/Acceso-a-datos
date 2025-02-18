package com.salesianostriana.ProyectoConecta.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ContactoPK implements Serializable {


    @GeneratedValue
    private Long id_autogenerado;
    private Long trabajador_id;
    private Long profesor_id;

}
