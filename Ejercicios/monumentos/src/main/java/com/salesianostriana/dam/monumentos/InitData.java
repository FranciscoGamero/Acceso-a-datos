package com.salesianostriana.dam.monumentos;

import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.repository.MonumentoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InitData {

    @Autowired
    private MonumentoRepository monumentoRepository;

    @PostConstruct
    public void init() {
        monumentoRepository.save((Monumento.builder()
                .id(1)
                .codPais("IT")
                .nombrePais("Italia")
                .nombreCiudad("Roma")
                .latitud("41.89")
                .longitud("12.49")
                .descripcionMonumento("El Coliseo o Anfiteatro Flavio \u200B es un anfiteatro de la época del Imperio romano, construido en el siglo I. Está ubicado en el este del Foro Romano, y fue el más grande de los que se construyeron en el Imperio romano.")
                .nombreMonumento("Coliseo Romano")
                .imagenMonumento("https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg")
                .build()));
        monumentoRepository.save((Monumento.builder()
                .id(2)
                .codPais("ES")
                .nombrePais("España")
                .nombreCiudad("Sevilla")
                .latitud("37.44")
                .longitud("-6.04")
                .descripcionMonumento("Extensa y antigua ciudad romana bien conservada que cuenta con las ruinas de un templo y un enorme anfiteatro..")
                .nombreMonumento("Itálica")
                .imagenMonumento("https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg")
                .build()));
    }
}
