package com.salesianos.data.Ejemplo2.repository;

import com.salesianos.data.Ejemplo2.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
