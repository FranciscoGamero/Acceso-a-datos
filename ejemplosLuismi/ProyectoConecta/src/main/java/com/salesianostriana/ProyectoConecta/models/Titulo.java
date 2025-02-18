package com.salesianostriana.ProyectoConecta.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@SQLDelete(sql = "UPDATE titulo SET borrado = true WHERE id=?")
@FilterDef(name = "borradoTituloFilter", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "borradoTituloFilter", condition = "borrado = :isBorrado")
public class Titulo {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private int duracion;
    private String grado;

    private boolean borrado = Boolean.FALSE;


    @OneToMany(mappedBy = "titulo",
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Curso> cursos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private FamiliaProfesional familiaProfesional;

    //Metodos Helper

    public void addFamiliaProfesional(FamiliaProfesional f){
        this.familiaProfesional = f;
        f.getTitulos().add(this);
    }

    public void removeFamiliaProfesional(FamiliaProfesional f){
        this.familiaProfesional = null;
        f.getTitulos().remove(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Titulo titulo = (Titulo) o;
        return getId() != null && Objects.equals(getId(), titulo.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
