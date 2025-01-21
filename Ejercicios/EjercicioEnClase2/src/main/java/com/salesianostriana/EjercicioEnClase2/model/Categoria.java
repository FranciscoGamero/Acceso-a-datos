package com.salesianostriana.EjercicioEnClase2.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Categoria {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "categoria",
            fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Producto> listaProductos = new HashSet<>();

    @OneToMany
    @Builder.Default
    @ToString.Exclude
    private Set<Categoria> subCategorias = new HashSet<>();

    private String nombre;

    public void addProducto(Producto p){
        p.setCategoria(this);
        listaProductos.add(p);
    }
    public void removeProducto(Producto p){
        listaProductos.remove(p);
        p.setCategoria(null);
    }
    public void addCategoria(Categoria c){
        subCategorias.add(c);
    }
    public void removeCategoria(Categoria c){
        subCategorias.remove(c);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Categoria categoria = (Categoria) o;
        return getId() != null && Objects.equals(getId(), categoria.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
