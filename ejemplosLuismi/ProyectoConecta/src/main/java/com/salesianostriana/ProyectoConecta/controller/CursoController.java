package com.salesianostriana.ProyectoConecta.controller;


import com.salesianostriana.ProyectoConecta.dto.CursoDto;
import com.salesianostriana.ProyectoConecta.dto.EditCursoDto;
import com.salesianostriana.ProyectoConecta.dto.EditUsuarioDto;
import com.salesianostriana.ProyectoConecta.dto.ListaCursoDto;

import com.salesianostriana.ProyectoConecta.models.Convocatoria;
import com.salesianostriana.ProyectoConecta.models.Curso;

import com.salesianostriana.ProyectoConecta.models.Trabajador;
import com.salesianostriana.ProyectoConecta.service.CursoService;

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
@RequestMapping("/curso")
@RequiredArgsConstructor
@Tag(name = "Curso", description = "El controlador de curso, para poder realizar todas las operaciones de gestión")
public class CursoController {

    private final CursoService cursoService;


    @Operation(summary = "Crea un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha registrado el curso",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Curso.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                   "nombre": "Sakamoto Days",
                                                   "horasEmpresa": 40
                                                 }
                                            
                                            
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "405", description = "No se ha podido registrar el curso",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CursoDto> guardarCurso(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Curso a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Curso.class),
                    examples = @ExampleObject(value = """
                                                    {
                                                      "nombre": "Curso de Java Intermedio",
                                                      "horasEmpresa": 40,
                                                      "titulo": {
                                                        "id": 1
                                                      },
                                                      "profesores":[
                                                        {
                                                            "id": 1
                                                        }
                                                      ]
                                                    }
                            """))) @RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(CursoDto.of(cursoService.guardarCurso(curso)));
    }


    @Operation(summary = "Obtiene el curso por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado el curso",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Curso.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            
                                             {       "nombre": "Java Básico",
                                                      "horasEmpresa": 40
                                                 }
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404", description = "No se ha el encontrado el curso",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public CursoDto obtenerCurso(@PathVariable Long id) {
        return CursoDto.of(cursoService.obtenerCursoPorId(id));
    }


    @Operation(summary = "Obtiene todos los cursos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado cursos",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Curso.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadCurso": 3,
                                                "List": [
                                                    {
                                                        "nombre": "Java Básico",
                                                        "horasEmpresa": 40
                                                    },
                                                    {
                                                        "nombre": "Spring Boot Avanzado",
                                                        "horasEmpresa": 60
                                                    },
                                                    {
                                                        "nombre": "Microservicios con Spring",
                                                        "horasEmpresa": 50
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })
    @GetMapping
    public ListaCursoDto obtenerCursos(boolean borrado) {
        return ListaCursoDto.of(cursoService.obtenerCursos(borrado));
    }


    @Operation(summary = "Actualiza un curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Curso actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class),
                            examples = {@ExampleObject(
                                    value = """
                                              {
                                               "nombre": "Java Avanzado",
                                               "horasEmpresa": 80
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún curso con ese ID",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para la actualización",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public EditCursoDto actualizarCurso(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Curso a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Curso.class),
                    examples = @ExampleObject(value = """
                                                    {
                                                      "nombre": "Java Avanzado",
                                                      "horasEmpresa": 80
                                                    }
                            """))) @RequestBody Curso curso, @PathVariable("id") Long id) {
        return EditCursoDto.of(cursoService.editarCurso(curso, id));
    }


    @Operation(summary = "Elimina un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el curso",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public void eliminarCurso(@PathVariable Long id){
        cursoService.borrarCurso(id);
    }
}
