package com.salesianostriana.ProyectoConecta.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;


@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@SuperBuilder
@SQLDelete(sql = "UPDATE profesor SET borrado = true WHERE id=?")
@FilterDef(name = "borradoProfesorFilter", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "borradoProfesorFilter", condition = "borrado = :isBorrado")
public class Profesor extends Persona{


    @OneToOne
    private Usuario usuario;

    private boolean borrado = Boolean.FALSE;

    @ManyToMany(mappedBy = "profesores",
    fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Curso> cursos = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profesor")
    @Builder.Default
    private List<Contacto> listaContactos =  new ArrayList<>();


}
