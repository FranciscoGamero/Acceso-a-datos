package com.salesianos.data.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class CarnetDeConducir {
    @Id
    @GeneratedValue
    private Long id;
    private String permisoCarnet;
    private Long cantidadPuntos;
}
