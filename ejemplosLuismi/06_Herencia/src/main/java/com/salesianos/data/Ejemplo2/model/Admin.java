package com.salesianos.data.Ejemplo2.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("A")
@SuperBuilder
public class Admin extends Usuario{

    private LocalDateTime ultimoCambio;
}
