package com.salesianostriana.ejercicio3.util;

import com.salesianostriana.ejercicio3.model.Bicicleta;
import com.salesianostriana.ejercicio3.model.Estacion;
import com.salesianostriana.ejercicio3.model.Uso;
import com.salesianostriana.ejercicio3.model.Usuario;
import com.salesianostriana.ejercicio3.repository.BicicletaRepository;
import com.salesianostriana.ejercicio3.repository.EstacionRepository;
import com.salesianostriana.ejercicio3.repository.UsoRepository;
import com.salesianostriana.ejercicio3.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MainDeMentira {

    private final UsuarioRepository usuarioRepository;
    private final BicicletaRepository bicicletaRepository;
    private final EstacionRepository estacionRepository;
    private final UsoRepository usoRepository;

    @PostConstruct
    public void init() {
        Bicicleta bicicleta1 = Bicicleta.builder()
                .marca("Giant")
                .modelo("Escape 3")
                .estado(true)
                .build();

        Bicicleta bicicleta2 = Bicicleta.builder()
                .marca("Trek")
                .modelo("FX 2")
                .estado(true)
                .build();

        Bicicleta bicicleta3 = Bicicleta.builder()
                .marca("Giant")
                .modelo("Escape 3")
                .estado(true)
                .build();

        Bicicleta bicicleta4 = Bicicleta.builder()
                .marca("Trek")
                .modelo("FX 2")
                .estado(true)
                .build();

        Bicicleta bicicleta5 = Bicicleta.builder()
                .marca("Giant")
                .modelo("Escape 3")
                .estado(true)
                .build();

        Bicicleta bicicleta6 = Bicicleta.builder()
                .marca("Trek")
                .modelo("FX 2")
                .estado(true)
                .build();

        Bicicleta bicicleta7 = Bicicleta.builder()
                .marca("Giant")
                .modelo("Escape 3")
                .estado(true)
                .build();

        Bicicleta bicicleta8 = Bicicleta.builder()
                .marca("Trek")
                .modelo("FX 2")
                .estado(true)
                .build();
/*
        Estacion estacion1 = Estacion.builder()
                .numero(101L)
                .nombre("Estación Central")
                .coordenadas("37.3833° N, 5.9833° W")
                .capacidad(50L)
                .build();

        Estacion estacion2 = Estacion.builder()
                .numero(102L)
                .nombre("Estación Norte")
                .coordenadas("37.3891° N, 5.9845° W")
                .capacidad(30L)
                .build();

        Estacion estacion3 = Estacion.builder()
                .numero(103L)
                .nombre("Estación Sur")
                .coordenadas("37.3891° N, 5.9845° W")
                .capacidad(30L)
                .build();

        estacion1.addBicicleta(bicicleta1);
        estacion2.addBicicleta(bicicleta2);

        estacionRepository.saveAll(List.of(estacion1, estacion2, estacion3));
        bicicletaRepository.saveAll(List.of(bicicleta1, bicicleta2));

        Uso uso1 = Uso.builder()
                .fechaInicio(LocalDateTime.of(2025, 1, 20, 10, 0))
                .fechaFin(LocalDateTime.of(2025, 1, 20, 11, 0))
                .coste(3.50)
                .bicicleta(bicicleta1)
                .estacion(estacion1)
                .build();

        Uso uso2 = Uso.builder()
                .fechaInicio(LocalDateTime.of(2025, 1, 21, 15, 0))
                .fechaFin(LocalDateTime.of(2025, 1, 21, 16, 30))
                .coste(5.00)
                .bicicleta(bicicleta2)
                .estacion(estacion2)
                .build();

        usoRepository.saveAll(List.of(uso1, uso2));

        bicicleta1.addUso(uso1);
        bicicleta2.addUso(uso2);

        estacion1.addUso(uso1);
        estacion2.addUso(uso2);

        estacionRepository.saveAll(List.of(estacion1, estacion2));
        */
        bicicletaRepository.saveAll(List.of(bicicleta1,bicicleta2,bicicleta3,
                bicicleta4,bicicleta5,bicicleta6,bicicleta7,bicicleta8));

        bicicletaRepository.findByMarca("Giant").forEach(System.out::println);
    }

}
