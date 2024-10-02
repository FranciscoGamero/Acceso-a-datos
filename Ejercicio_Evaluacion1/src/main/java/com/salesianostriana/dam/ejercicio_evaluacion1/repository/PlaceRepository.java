package com.salesianostriana.dam.ejercicio_evaluacion1.repository;


import com.salesianostriana.dam.ejercicio_evaluacion1.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}
