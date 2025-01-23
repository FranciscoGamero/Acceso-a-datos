package com.salesianos.data.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("A")
public class Admin extends Usuario{

    private LocalDate fechaCreaciom;
}
