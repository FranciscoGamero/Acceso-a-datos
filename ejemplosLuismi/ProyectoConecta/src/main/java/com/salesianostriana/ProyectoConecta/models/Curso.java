package com.salesianostriana.ProyectoConecta.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@SQLDelete(sql = "UPDATE curso SET borrado = true WHERE id=?")
@FilterDef(name = "borradoCursoFilter", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "borradoCursoFilter", condition = "borrado = :isBorrado")
public class Curso {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private int horasEmpresa;


    private boolean borrado = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Profesor> profesores = new HashSet<>();

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Demanda> listaDemandas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Titulo titulo;

    //Metodos Helper

    public void addProfesor(Set<Profesor> listaProfesor){
        profesores.addAll(listaProfesor);
        listaProfesor.stream().map(profesor -> profesor.getCursos().add(this)).close();

    }

    public void removeProfesor(Set<Profesor> listaProfesor){
        profesores.removeAll(listaProfesor);
        listaProfesor.forEach(profesor -> profesor.getCursos().remove(this));
    }

    public void addTitulo(Titulo t){
        this.titulo = t;
        t.getCursos().add(this);
    }

    public void removeTitulo(Titulo t){
        this.titulo = null;
        t.getCursos().remove(this);
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Curso curso = (Curso) o;
        return getId() != null && Objects.equals(getId(), curso.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
