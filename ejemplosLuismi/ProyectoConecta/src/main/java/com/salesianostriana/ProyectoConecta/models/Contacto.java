package com.salesianostriana.ProyectoConecta.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@SQLDelete(sql = "UPDATE contacto SET borrado = true WHERE id=?")
@FilterDef(name = "contactoBorradoFiltro", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "contactoBorradoFiltro", condition = "borrado = :isBorrado")
public class Contacto {

    @EmbeddedId
    private ContactoPK contactoPK = new ContactoPK();

    private LocalDateTime fecha;
    private String canal;


    @Column(length = 500)
    private String resumen;

    private boolean borrado = Boolean.FALSE;


    @ManyToOne
    @MapsId("profesor_id")
    @JoinColumn(name = "profesor_id")
    private Profesor  profesor;

    @ManyToOne
    @MapsId("trabajador_id")
    @JoinColumn(name = "trabajador_id")
    private Trabajador  trabajador;

    //Metodo helper

    public void  addProfesor (Profesor profesor){
        this.profesor = profesor;
        profesor.getListaContactos().add(this);
    }

    public void removeProfesor(Profesor profesor){
        this.profesor = null;
        profesor.getListaContactos().remove(this);
    }




    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Contacto contacto = (Contacto) o;
        return getContactoPK() != null && Objects.equals(getContactoPK(), contacto.getContactoPK());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(contactoPK);
    }
}
