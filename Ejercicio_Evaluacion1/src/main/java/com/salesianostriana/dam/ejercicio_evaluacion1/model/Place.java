package com.salesianostriana.dam.ejercicio_evaluacion1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String direccion;
    private String latitud;
    private String longitud;
    private String descripcion;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("tag")
    @JoinTable(joinColumns = @JoinColumn(name ="place_id"),
    foreignKey = @ForeignKey(name = "fk_place_tag"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"),
    name = "place_tag")
    private List<Tag> tags = new ArrayList<>();
    private String imagen;

    public void addTag(Tag tag){
        if (this.getTags()==null){
            this.setTags(new ArrayList<Tag>());
        }
        this.getTags().add(tag);
        if (tag.getListaPlaces() == null){
            tag.setListaPlaces(new ArrayList<Place>());
            tag.getListaPlaces().add(this);
        }
    }
    public void eliminarTag(Tag tag){
        tag.getListaPlaces().remove(this);
        this.getTags().remove((tag));
    }
}
