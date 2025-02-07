package com.salesianostriana.pruebaSeguridad.controller;


import com.salesianostriana.pruebaSeguridad.model.Task;
import com.salesianostriana.pruebaSeguridad.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> obtenerTodos(){
        return taskService.obtnerTodosTask();
    }

    @GetMapping("/{id}")
    public Optional<Task> obtenerPorId(@PathVariable Long id){
        return taskService.obtenerTaskPorId(id);
    }

    @PostMapping
    public Task guardarTask(@RequestBody Task task){
        return taskService.guardarTask(task);
    }

}
