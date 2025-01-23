package com.salesianostriana.ejercicio3.model;

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
public class Estacion {
    @Id
    @GeneratedValue
    private Long id;
    private Long numero;
    private String nombre;
    private String coordenadas;
    private Long capacidad;

    @OneToMany(mappedBy = "estacion",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Uso> listaUsos= new ArrayList<>();

    @OneToMany(mappedBy = "estacion",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<Bicicleta> listaBicicletas = new ArrayList<>();

    public void addUso(Uso u){
        listaUsos.add(u);
        u.setEstacion(this);
    }
    public void removeUso(Uso u){
        listaUsos.remove(u);
        u.setEstacion(null);
    }
    public void addBicicleta(Bicicleta b){
        listaBicicletas.add(b);
        b.setEstacion(this);
    }
    public void removeBicicleta(Bicicleta b){
        listaBicicletas.remove(b);
        b.setEstacion(null);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Estacion estacion = (Estacion) o;
        return getId() != null && Objects.equals(getId(), estacion.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
