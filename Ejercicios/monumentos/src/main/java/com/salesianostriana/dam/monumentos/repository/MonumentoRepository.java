package com.salesianostriana.dam.monumentos.repository;

import com.salesianostriana.dam.monumentos.model.Monumento;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MonumentoRepository {

    private final HashMap<Long, Monumento> monumentoRepository = new HashMap<>();

    @PostConstruct
    public void init() {
        add((Monumento.builder()
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
        add((Monumento.builder()
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

    public Monumento add(Monumento monumento) {
        monumentoRepository.put(monumento.getId(), monumento);
        return monumento;
    }

    public List<Monumento> getAll() {
        return List.copyOf(monumentoRepository.values());
    }

    public Optional<Monumento> get(long id) {
        return Optional.ofNullable(monumentoRepository.get(id));
    }
    public Optional<Monumento> edit(Long id, Monumento nuevoValor) {
        return Optional.ofNullable(monumentoRepository.computeIfPresent(id, (k, v) -> {
            v.setNombreMonumento(nuevoValor.getNombreMonumento());
            v.setImagenMonumento(nuevoValor.getImagenMonumento());
            v.setDescripcionMonumento(nuevoValor.getDescripcionMonumento());
            v.setLatitud(nuevoValor.getLatitud());
            v.setLongitud(nuevoValor.getLongitud());
            v.setCodPais(nuevoValor.getCodPais());
            v.setNombrePais(nuevoValor.getNombrePais());
            v.setNombreCiudad(nuevoValor.getNombreCiudad());
            return v;
        }));
    }
    public void delete(Long id) {
        monumentoRepository.remove(id);
    }
    public List<Monumento> query(String sortDirection){
        List<Monumento> data = new ArrayList<>(monumentoRepository.values());

        if(sortDirection.equalsIgnoreCase("asc"))
            data.sort(Comparator.comparing(Monumento::getNombreMonumento));
        else if (sortDirection.equalsIgnoreCase("desc"))
            data.sort(Comparator.comparing(Monumento::getNombreMonumento).reversed());
        return Collections.unmodifiableList(data);
    }
}
