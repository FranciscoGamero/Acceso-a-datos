package com.salesianostriana.ProyectoConecta.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@SQLDelete(sql = "UPDATE demanda SET borrado = true WHERE id=?")
@FilterDef(name = "demandaBorradaFiltro", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "demandaBorradaFiltro", condition = "borrado = :isBorrado")
public class Demanda {

    @Id
    @GeneratedValue
    private Long id;

    private int cantidadAlumnos;

    private String requisitos;

    private boolean borrado = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id",
            foreignKey = @ForeignKey(name = "fk_convocatoria_empresa"))
    private Empresa empresa;


    @ManyToOne(fetch = FetchType.EAGER)
    private Curso curso;
  
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "convocatoria_id",
            foreignKey = @ForeignKey(name = "fk_convocatoria_demanda"))
    private Convocatoria convocatoria;

    public void addEmpresa(Empresa empresa){
        this.empresa = empresa;
        empresa.getListaDemandas().add(this);
    }
    public void removeEmpresa(Empresa empresa){
        empresa.getListaDemandas().remove(this);
        this.empresa = null;
    }
      public void addCurso(Curso curso){
        this.curso = curso;
        curso.getListaDemandas().add(this);
    }

    public void removeCurso(Curso curso){
        this.curso = null;
        curso.getListaDemandas().remove(this);
    }

    public void addConvocatoria(Convocatoria convocatoria){
        this.convocatoria = convocatoria;
        convocatoria.getListaDemandas().add(this);
    }
    public void removeConvocatoria(Convocatoria convocatoria){
        convocatoria.getListaDemandas().remove(this);
        this.convocatoria = null;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Demanda demanda = (Demanda) o;
        return getId() != null && Objects.equals(getId(), demanda.getId());
    }
  
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();


    }
}
