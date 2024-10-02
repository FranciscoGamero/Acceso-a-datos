package com.salesianostriana.dam.monumentos.repository;


import com.salesianostriana.dam.monumentos.model.Monumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositoryMonumento {
    private List<Monumento> listaMonumentos;
    public void repositorioMonumentos() {
        save((Monumento.builder()
                .id(1)
                .codPais("IT")
                .nombrePais("Italia")
                .nombreCiudad("Roma")
                .latitud("41.89")
                .longitud("12.49")
                .descripcionMonumento("El Coliseo o Anfiteatro Flavio \u200B es un anfiteatro de la época del Imperio romano, construido en el siglo I. Está ubicado en el este del Foro Romano, y fue el más grande de los que se construyeron en el Imperio romano.")
                .imagenMonumento("https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg")
                .build()));
        save((Monumento.builder()
                .id(1)
                .codPais("ITA")
                .nombrePais("Italia")
                .nombreCiudad("Roma")
                .latitud("41.89")
                .longitud("12.49")
                .descripcionMonumento("El Coliseo o Anfiteatro Flavio \u200B es un anfiteatro de la época del Imperio romano, construido en el siglo I. Está ubicado en el este del Foro Romano, y fue el más grande de los que se construyeron en el Imperio romano.")
                .imagenMonumento("https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg")
                .build()));
    }
    public List<Monumento> findAll() {
        return Collections.unmodifiableList(listaMonumentos);
    }
    public Optional<Monumento> findById(int id) {
        return listaMonumentos
                .stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }
    public void save(Monumento monumento) {
        listaMonumentos.add(monumento);
    }
    public void delete(Monumento monumento) {
        listaMonumentos.remove(monumento);
    }
}
