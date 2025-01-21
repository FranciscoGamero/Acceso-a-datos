package com.salesianos.data.repos;

import com.salesianos.data.model.CarnetDeConducir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarnetRepository extends JpaRepository<CarnetDeConducir, Long> {
}
