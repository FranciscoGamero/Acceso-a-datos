package com.salesianosTriana.EjercicioEnClase22.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@IdClass(CursoVideoFk.class)
@ToString
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    @Id
    @ManyToOne
    private CursoOnline curso;

    private String orden;
    private String titulo;
    private String descripcion;
    private String url;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Video video = (Video) o;
        return getId() != null && Objects.equals(getId(), video.getId())
                && getCurso() != null && Objects.equals(getCurso(), video.getCurso());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, curso);
    }
}
