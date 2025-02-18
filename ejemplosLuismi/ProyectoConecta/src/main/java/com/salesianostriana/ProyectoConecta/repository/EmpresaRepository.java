package com.salesianostriana.ProyectoConecta.repository;

import com.salesianostriana.ProyectoConecta.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
