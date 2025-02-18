package com.salesianostriana.ProyectoConecta.controller;


import com.salesianostriana.ProyectoConecta.dto.ListaProfesorDto;
import com.salesianostriana.ProyectoConecta.dto.ProfesorDto;
import com.salesianostriana.ProyectoConecta.models.Empresa;
import com.salesianostriana.ProyectoConecta.models.Profesor;
import com.salesianostriana.ProyectoConecta.service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profesor")
@Tag(name = "Profesor", description = "El controlador de profesor, para poder realizar todas las operaciones de gestión")
public class ProfesorController {

    private final ProfesorService profesorService;


    @Operation(summary = "Crea un Profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha registrado el profesor",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Profesor.class)),
                            examples = {@ExampleObject(
                                    value = """
                                {
                                   "nombre": "María",
                                   "apellidos": "González",
                                   "email": "maria.gonzalez@example.com",
                                   "telefono": "654321987"
                                 }

                            
                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "405", description = "No se ha podido registrar el profesor",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Profesor> guardarProfesor(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Profesor a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class),
                    examples = @ExampleObject(value = """
                                                    {
                                                       "nombre": "Nuria",
                                                       "apellidos": "Palomares",
                                                       "email": "nuria.palomares@ejemplo.com",
                                                       "telefono": "123456789",
                                                       "puesto": "Desarrollador",
                                                       "area": "IT"
                                                     }
                            """))) @RequestBody Profesor profesor){
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.guardarProfesor(profesor));
    }



    @Operation(summary = "Obtiene el profesor por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado el profesor",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Profesor.class)),
                            examples = {@ExampleObject(
                                    value = """
                            
                             {      "id": 1,
                                   "nombre": "María",
                                   "apellidos": "González",
                                   "email": "maria.gonzalez@example.com",
                                   "telefono": "654321987"
                                 }
                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404", description = "No se ha el encontrado el profesor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ProfesorDto ObtenerProfesor (@PathVariable Long id){
        return ProfesorDto.of(profesorService.buscarPorId(id));
    }


    @Operation(summary = "Obtiene todos los profesores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado profesores",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Profesor.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadProfesores": 2,
                                                "List": [
                                                    {
                                                        "nombre": "Fran",
                                                        "apellidos": "Alcantarilla Calado",
                                                        "email": "a@a",
                                                        "telefono": "985747454",
                                                        "username": "FranAlc"
                                                    },
                                                    {
                                                        "nombre": "Candi",
                                                        "apellidos": "Alcantarilla Calado",
                                                        "email": "c@a",
                                                        "telefono": "985747454",
                                                        "username": "CandiAlc"
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })
    @GetMapping
    public ListaProfesorDto obtenerProfesores(boolean borrado){
        return ListaProfesorDto.of(profesorService.buscarTodos(borrado));
    }

    @Operation(summary = "Edita un profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado un profesor",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Profesor.class)),
                            examples = {@ExampleObject(
                                    value = """
                                {  "id": 1
                                   "nombre": "Rocio",
                                   "apellidos": "González",
                                   "email": "maria.gonzalez@example.com",
                                   "telefono": "654321987"
                                 }

                            
                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404", description = "No se ha podido editar el profesor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ProfesorDto actualizarProfesor(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Profesor a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class),
                    examples = @ExampleObject(value = """
                                                    {
                                                        "id": 1,
                                                        "nombre": "Juan",
                                                        "apellidos": "Pérez",
                                                        "email": "juan.perez@gmailcambiado.com",
                                                        "telefono": "123456789",
                                                        "puesto": "Desarrollador",
                                                        "area": "IT",
                                                        "empresa": null
                                                    }
                            """))) @RequestBody Profesor profesor, @PathVariable Long id){
        return ProfesorDto.of(profesorService.editarProfesor(profesor, id));
    }


    @Operation(summary = "Elimina un Profesor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el profesor",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public void eliminarProfesor(@PathVariable Long id){
        profesorService.borrarProfesor(id);
    }

}
