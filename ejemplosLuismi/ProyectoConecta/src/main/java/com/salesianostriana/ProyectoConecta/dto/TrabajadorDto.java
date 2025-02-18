package com.salesianostriana.ProyectoConecta.dto;

import com.salesianostriana.ProyectoConecta.models.Empresa;
import com.salesianostriana.ProyectoConecta.models.Trabajador;

public record TrabajadorDto(
        String nombre,
        String apellidos,
        String email,
        String telefono,
        String puesto,
        String area,
        EmpresaDto empresa
) {


    public static TrabajadorDto of (Trabajador t){
        return new TrabajadorDto(
                t.getNombre(),
                t.getApellidos(),
                t.getEmail(),
                t.getTelefono(),
                t.getPuesto(),
                t.getArea(),
                EmpresaDto.of(t.getEmpresa())
        );
    }
}
