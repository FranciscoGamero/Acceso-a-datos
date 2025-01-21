package com.salesianosTriana.EjercicioEnClase22.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Profesor {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "profesor",
            fetch = FetchType.EAGER)
    @Builder.Default
    @ToString.Exclude
    private List<CursoOnline> listaCursoOnlines = new ArrayList<>();

    private String nombre;
    private String email;
    private double puntuacion;

    public void addCurso(CursoOnline p){
        p.setProfesor(this);
        listaCursoOnlines.add(p);
    }
    public void removeCurso(CursoOnline p){
        listaCursoOnlines.remove(p);
        p.setProfesor(null);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Profesor profesor = (Profesor) o;
        return getId() != null && Objects.equals(getId(), profesor.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
