package com.salesianos.data.util;

import com.salesianos.data.Ejemplo1.model.Coche;
import com.salesianos.data.Ejemplo1.repository.CocheRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final CocheRepository cocheRepository;

    @PostConstruct
    public void run() {
        Coche c1 = Coche.builder()
                .capacidadDeposito(27)
                .ruedas(4)
                .cv(100)
                .puertas(5)
                .espacioMaletero(2)
                .build();
        Coche c2 = Coche.builder()
                .capacidadDeposito(15)
                .ruedas(4)
                .cv(50)
                .puertas(3)
                .espacioMaletero(1.1)
                .build();
        cocheRepository.saveAll(List.of(c1,c2));
        System.out.println(c1);
        System.out.println(c2);
    }

}

