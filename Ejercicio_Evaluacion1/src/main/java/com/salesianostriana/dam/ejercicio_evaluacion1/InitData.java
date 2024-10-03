package com.salesianostriana.dam.ejercicio_evaluacion1;


import com.salesianostriana.dam.ejercicio_evaluacion1.model.Place;
import com.salesianostriana.dam.ejercicio_evaluacion1.model.Tag;
import com.salesianostriana.dam.ejercicio_evaluacion1.repository.PlaceRepository;
import com.salesianostriana.dam.ejercicio_evaluacion1.repository.TagRepository;
import com.salesianostriana.dam.ejercicio_evaluacion1.service.PlaceService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InitData {

    @Autowired
    private PlaceRepository lugarRepository;
    @Autowired
    private TagRepository tagRespository;

    public void init() {
        tagRespository.save(Tag.builder()
                .id(1)
                .nombre("Tapas")
                .build());

        tagRespository.save(Tag.builder()
                .id(2)
                .nombre("Vino")
                .build());
        tagRespository.save(Tag.builder()
                .id(3)
                .nombre("Cerveza")
                .build());
        tagRespository.save(Tag.builder()
                .id(4)
                .nombre("Deporte")
                .build());

        lugarRepository.save((Place.builder()
                .id(1)
                .nombre("La Taberna del Sabor")
                .direccion("Calle de la Gastronomía, 12")
                .latitud("40.416775")
                .longitud("-3.703790")
                .descripcion("Un lugar acogedor para disfrutar de tapas tradicionales y vinos selectos.")
                .imagen("url_imagen_taberna")
                .build()));
        lugarRepository.save((Place.builder()
                .id(2)
                .nombre("El Bar Deportivo")
                .direccion("Avenida del Deporte, 5")
                .latitud("40.423776")
                .longitud("-3.703888")
                .descripcion("El lugar ideal para ver partidos en vivo con amigos, acompañado de cervezas y snacks.")
                .imagen("url_imagen_bar_deportivo")
                .build()));

        lugarRepository.findById(1L).get().addTag(tagRespository.findById(1L).get());
        lugarRepository.findById(1L).get().addTag(tagRespository.findById(2L).get());
        lugarRepository.findById(2L).get().addTag(tagRespository.findById(1L).get());
        lugarRepository.findById(2L).get().addTag(tagRespository.findById(2L).get());
    }
}
