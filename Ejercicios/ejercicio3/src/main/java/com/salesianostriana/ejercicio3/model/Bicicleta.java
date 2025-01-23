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
public class Bicicleta {

  @Id
  @GeneratedValue
  private Long id;

  private String marca;
  private String modelo;
  private boolean estado;

  @OneToMany(mappedBy = "bicicleta",
  fetch = FetchType.EAGER,
  cascade = CascadeType.ALL,
  orphanRemoval = true)
  @Builder.Default
  private List<Uso> listaUsos= new ArrayList<>();

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Estacion estacion;

  public void addUso(Uso u){
    listaUsos.add(u);
    u.setBicicleta(this);
  }
  public void removeUso(Uso u){
    listaUsos.remove(u);
    u.setBicicleta(null);
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Bicicleta bicicleta = (Bicicleta) o;
    return getId() != null && Objects.equals(getId(), bicicleta.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}