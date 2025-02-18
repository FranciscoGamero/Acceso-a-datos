package com.salesianostriana.ProyectoConecta.controller;

import com.salesianostriana.ProyectoConecta.dto.*;
import com.salesianostriana.ProyectoConecta.models.Demanda;
import com.salesianostriana.ProyectoConecta.service.DemandaService;
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
@RequestMapping("/demanda")
@Tag(name = "Demanda", description = "El controlador de demanda, para poder realizar todas las operaciones de gestión")
public class DemandaController {

    private final DemandaService demandaService;



    @Operation(summary = "Obtiene todas los demandas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado demandas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Demanda.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadDemandas": 2,
                                                "listaDemandas": [
                                                    {
                                                        "cantidadAlumnos": 25,
                                                        "requisitos": "Conocimiento en Java y Spring Boot",
                                                        "nombreEmpresa": "Empresa Tech",
                                                        "nombreCurso": "Java Básico",
                                                        "cursoEscolar": "2023/2024",
                                                        "nombreConvocatoria": "Convocatoria ordinaria"
                                                    },
                                                    {
                                                        "cantidadAlumnos": 25,
                                                        "requisitos": "Conocimiento en Java y Spring Boot",
                                                        "nombreEmpresa": "Empresa Tech",
                                                        "nombreCurso": "Java Básico",
                                                        "cursoEscolar": "2023/2024",
                                                        "nombreConvocatoria": "Convocatoria ordinaria"
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })
    @GetMapping
    public ListaDemandaDto obtenerDemandas(boolean borrado) {
        return new ListaDemandaDto(demandaService.buscarTodos(borrado).size(),demandaService.buscarTodos(borrado).stream().map(DemandaDto::of).toList());
    }

    @Operation(summary = "Obtiene una demanda por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Demanda encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Demanda.class),
                            examples = {@ExampleObject(
                                    value = """
                                    {
                                        "cantidadAlumnos": 25,
                                        "requisitos": "Conocimiento en Java y Spring Boot",
                                        "nombreEmpresa": "Empresa Tech",
                                        "nombreCurso": "Java Básico",
                                        "cursoEscolar": "2023/2024",
                                        "nombreConvocatoria": "Convocatoria ordinaria"
                                    }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna demanda con ese ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public DemandaDto obtenerDemandaPorId(@PathVariable Long id) {
        return DemandaDto.of(demandaService.buscarPorId(id));
    }

    @Operation(summary = "Obtiene una demanda por el nombre de la empresa y el id del curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado demandas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Demanda.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadDemandas": 2,
                                                "listaDemandas": [
                                                    {
                                                        "cantidadAlumnos": 25,
                                                        "requisitos": "Conocimiento en Java y Spring Boot",
                                                        "nombreEmpresa": "Empresa Tech",
                                                        "nombreCurso": "Java Básico",
                                                        "cursoEscolar": "2023/2024",
                                                        "nombreConvocatoria": "Convocatoria ordinaria"
                                                    },
                                                    {
                                                        "cantidadAlumnos": 25,
                                                        "requisitos": "Conocimiento en Java y Spring Boot",
                                                        "nombreEmpresa": "Empresa Tech",
                                                        "nombreCurso": "Java Básico",
                                                        "cursoEscolar": "2023/2024",
                                                        "nombreConvocatoria": "Convocatoria ordinaria"
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })
    @GetMapping("/{cursoId}/empresa/{nombreEmpresa}")
    public ListaDemandaDto obtenerDemandasPorNombreEmpresaYCurso(@PathVariable String nombreEmpresa, @PathVariable Long cursoId){
        return ListaDemandaDto
                .of(demandaService.buscarPorNombreEmpresaYCurso(nombreEmpresa, cursoId)
                        .stream()
                        .map(DemandaDto::of)
                        .toList());
    }



    @Operation(summary = "Obtiene una lista de  demandas por el id de la familia profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado demandas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Demanda.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadDemandas": 3,
                                                "listaDemandas": [
                                                    {
                                                        "cantidadAlumnos": 5,
                                                        "requisitos": "Conocimientos básicos en Java",
                                                        "nombreEmpresa": "EmpresaTech",
                                                        "nombreCurso": "Java Básico",
                                                        "cursoEscolar": "2023/2024",
                                                        "nombreConvocatoria": "Convocatoria ordinaria"
                                                    },
                                                    {
                                                        "cantidadAlumnos": 3,
                                                        "requisitos": "Experiencia previa con Spring Boot",
                                                        "nombreEmpresa": "EmpresaInnovacion",
                                                        "nombreCurso": "Spring Boot Avanzado",
                                                        "cursoEscolar": "2023/2024",
                                                        "nombreConvocatoria": "Convocatoria extraordinaria"
                                                    },
                                                    {
                                                        "cantidadAlumnos": 8,
                                                        "requisitos": "Capacidad para trabajar en equipo",
                                                        "nombreEmpresa": "EmpresaTech",
                                                        "nombreCurso": "Java Básico",
                                                        "cursoEscolar": "2023/2024",
                                                        "nombreConvocatoria": "Convocatoria extraordinaria"
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })
    @GetMapping("/familiaProfesional/{familiaId}")
    public ListaDemandaDto obtenerDemandasPorFamiliaProfesional(@PathVariable Long familiaId){
        return ListaDemandaDto.of(demandaService.buscarDemandasPorFamiliaProfesional(familiaId).stream().map(DemandaDto::of).toList());
    }

    @Operation(summary = "Registra una nueva demanda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Demanda registrada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Demanda.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "cantidadAlumnos": 25,
                                                    "requisitos": "Conocimiento en Java y Spring Boot",
                                                    "nombreEmpresa": "Empresa Tech",
                                                    "nombreCurso": "Java Básico",
                                                    "cursoEscolar": "2023/2024",
                                                    "nombreConvocatoria": "Convocatoria ordinaria"
                                                }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta o datos inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<DemandaDto> guardarDemanda(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Demanda a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Demanda.class),
                    examples = @ExampleObject(value = """
                                                        {
                                                          "cantidadAlumnos": 25,
                                                          "requisitos": "Conocimiento en Java y Spring Boot",
                                                          "convocatoria": {
                                                            "id": 1
                                                          },
                                                          "curso": {
                                                            "id": 1
                                                          },
                                                          "empresa": {
                                                            "id": 51
                                                          }
                                                        }
                            """))) @RequestBody Demanda nueva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(DemandaDto.of(demandaService.guardarDemanda(nueva)));
    }

    @Operation(summary = "Actualiza una demanda existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Demanda actualizada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Demanda.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "cantidadAlumnos": 8,
                                            "requisitos": "Conocimiento en Spring, Angular y Flutter",
                                            "nombreEmpresa": "Empresa Tech",
                                            "nombreCurso": "Java Básico",
                                            "cursoEscolar": "2023/2024",
                                            "nombreConvocatoria": "Convocatoria ordinaria"
                                        }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna demanda con ese ID",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para la actualización",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public DemandaDto actualizarDemanda(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Demanda a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Demanda.class),
                    examples = @ExampleObject(value = """
                                                        {
                                                          "cantidadAlumnos": 8,
                                                          "requisitos": "Conocimiento en Spring, Angular y Flutter",
                                                          "convocatoria": {
                                                            "id": 1
                                                          },
                                                          "curso": {
                                                            "id": 1
                                                          },
                                                          "empresa": {
                                                            "id": 1
                                                          }
                                                        }
                            """))) @RequestBody Demanda demanda,@PathVariable("id") Long demandaId) {

        return DemandaDto.of(demandaService.editarDemanda(demanda, demandaId));
    }

    @Operation(summary = "Elimina una demanda por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Demanda eliminada correctamente",
                    content = @Content),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?>eliminarDemanda(@PathVariable Long id){
        demandaService.borrarDemandaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
