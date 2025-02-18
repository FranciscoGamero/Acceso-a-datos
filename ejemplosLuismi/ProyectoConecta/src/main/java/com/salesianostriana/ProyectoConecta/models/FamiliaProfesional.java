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
@SQLDelete(sql = "UPDATE familia_profesional SET borrado = true WHERE id=?")
@FilterDef(name = "borradofamiliaProfesionalFilter", parameters = @ParamDef(name = "isBorrado", type = Boolean.class))
@Filter(name = "borradofamiliaProfesionalFilter", condition = "borrado = :isBorrado")
public class FamiliaProfesional {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private boolean borrado = Boolean.FALSE;


    @OneToMany(mappedBy = "familiaProfesional")
    @Builder.Default
    private List<Titulo> titulos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "listaFamilias")
    @Builder.Default
    private Set<Empresa> listaEmpresas = new HashSet<>();

    public void addEmpresas(Set<Empresa> empresas) {
        listaEmpresas.addAll(empresas);  // Agrega todas las empresas a la lista de esta familia
        for (Empresa e : empresas) {
            e.getListaFamilias().add(this);  // Actualiza la lista de familias en cada empresa
        }
    }


    public void removeEmpresas(Set<Empresa> empresas){
        listaEmpresas.removeAll(empresas);
        for (Empresa e : empresas) {
            e.getListaFamilias().remove(this);  // Actualiza la lista de familias en cada empresa
        }
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        FamiliaProfesional that = (FamiliaProfesional) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
