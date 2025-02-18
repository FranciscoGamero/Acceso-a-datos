package com.salesianostriana.ProyectoConecta.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@SQLDelete(sql = "UPDATE empresa SET borrado = true WHERE id=?")
@FilterDef(name = "empresaBorradaFiltro", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "empresaBorradaFiltro", condition = "borrado = :isBorrado")
public class Empresa {

    @Id
    @GeneratedValue
    private Long id;

    private String cif;
    private String direccion;
    private String coordenadas;
    private String nombre;

    private boolean borrado = Boolean.FALSE;

    @Builder.Default
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private List<Trabajador> listaTrabajadores  = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private List<Demanda> listaDemandas  = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<FamiliaProfesional> listaFamilias = new HashSet<>();

    //Metodos Helper




}
