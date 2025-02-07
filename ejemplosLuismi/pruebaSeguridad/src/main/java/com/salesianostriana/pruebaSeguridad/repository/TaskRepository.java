package com.salesianostriana.pruebaSeguridad.repository;

import com.salesianostriana.pruebaSeguridad.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
