package com.salesianos.data.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private String nombreCategoria;

    @Builder.Default //Para que la lista no sea null
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER) //Nombre del atributo con @ManyToOne
    @ToString.Exclude
    @JsonManagedReference //Hace que a partir de aqui no vuelva el bucle al mostrar los productos
    private List<Producto> listaProductos = new ArrayList<>();


    public void addProducto (Producto p){
        p.setCategoria(this);
        this.listaProductos.add(p);
    }
    public void removeProducto (Producto p){
        p.setCategoria(null);
        this.listaProductos.remove(p);
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
