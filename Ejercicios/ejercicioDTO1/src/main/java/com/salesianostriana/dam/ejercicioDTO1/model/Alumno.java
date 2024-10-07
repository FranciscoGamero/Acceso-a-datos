package com.salesianostriana.dam.ejercicioDTO1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true)
    private long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("listaAlumnos")
    @JoinTable(joinColumns = @JoinColumn(name ="asignatura_id"),
            foreignKey = @ForeignKey(name = "FK_ALUMNO_ASIGNATURA"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_od"),
            name = "alumno_asignatura")
    private List<Asignatura> listaAsignatura = new ArrayList<>();
}
