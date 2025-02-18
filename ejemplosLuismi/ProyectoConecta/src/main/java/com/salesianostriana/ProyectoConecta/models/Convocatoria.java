package com.salesianostriana.ProyectoConecta.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@SQLDelete(sql = "UPDATE convocatoria SET borrado = true WHERE id=?")
@FilterDef(name = "convocatoriaBorradaFiltro", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "convocatoriaBorradaFiltro", condition = "borrado = :isBorrado")
public class Convocatoria {

    @Id
    @GeneratedValue
    private Long id;

    private String cursoEscolar;

    private String nombre;

    private boolean borrado = Boolean.FALSE;


    @OneToMany(mappedBy = "convocatoria", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    private List<Demanda> listaDemandas = new ArrayList<>();


}
