package com.salesianostriana.pruebaSeguridad.service;


import com.salesianostriana.pruebaSeguridad.model.Task;
import com.salesianostriana.pruebaSeguridad.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


    public List<Task> obtnerTodosTask(){
        return taskRepository.findAll();
    }


    public Optional<Task> obtenerTaskPorId(Long id){
        return taskRepository.findById(id);
    }

    public Task guardarTask(Task task){
        return taskRepository.save(task);
    }

}