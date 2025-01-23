package com.salesianos.data.util;

import com.salesianos.data.Ejemplo1.model.Coche;
import com.salesianos.data.Ejemplo1.repository.CocheRepository;
import com.salesianos.data.Ejemplo2.model.Admin;
import com.salesianos.data.Ejemplo2.model.Usuario;
import com.salesianos.data.Ejemplo2.repository.AdminRepository;
import com.salesianos.data.Ejemplo2.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final CocheRepository cocheRepository;
    private final UsuarioRepository usuarioRepository;
    private final AdminRepository adminRepository;

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

        Admin admin1 = Admin.builder()
                .nombreCompleto("Juan Pérez")
                .contrasenia("admin123")
                .ultimoCambio(LocalDateTime.now())
                .build();

        Admin admin2 = Admin.builder()
                .nombreCompleto("Laura González")
                .contrasenia("admin789")
                .ultimoCambio(LocalDateTime.now())
                .build();
        adminRepository.saveAll(List.of(admin1,admin2));
        Usuario usuario1 = Usuario.builder()
                .nombreCompleto("Ana López")
                .contrasenia("usuario123")
                .build();

        // Crear otro usuario general (Usuario)
        Usuario usuario2 = Usuario.builder()
                .nombreCompleto("Carlos Martínez")
                .contrasenia("usuario456")
                .build();
        usuarioRepository.saveAll(List.of(usuario1,usuario2));

        System.out.println(usuario1);
        System.out.println(usuario2);
        System.out.println(admin1);
        System.out.println(admin2);
    }

}

