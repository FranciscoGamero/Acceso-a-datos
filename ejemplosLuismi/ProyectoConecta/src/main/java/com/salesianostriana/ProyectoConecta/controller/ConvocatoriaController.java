package com.salesianostriana.ProyectoConecta.controller;

import com.salesianostriana.ProyectoConecta.dto.ConvocatoriaDto;
import com.salesianostriana.ProyectoConecta.dto.ListaConvocatoriasDto;
import com.salesianostriana.ProyectoConecta.models.Convocatoria;
import com.salesianostriana.ProyectoConecta.service.ConvocatoriaService;
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
@RequestMapping("/convocatoria")
@RequiredArgsConstructor
@Tag(name = "Convocatoria", description = "El controlador de convocatoria, para poder realizar todas las operaciones de gestión")
public class ConvocatoriaController {

    private final ConvocatoriaService convocatoriaService;

    @Operation(summary = "Obtiene todas los convocatorias registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado convocatorias",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Convocatoria.class)),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "cantidadConvocatorias": 2,
                                            "convocatorias": [
                                                {
                                                    "nombre": "Convocatoria ordinaria",
                                                    "cursoEscolar": "2023/2024"
                                                },
                                                {
                                                    "nombre": "Convocatoria extraordinaria",
                                                    "cursoEscolar": "2023/2024"
                                                }
                                            ]
                                        }
                                            """
                            )}
                    )}),
    })
    @GetMapping
    public ListaConvocatoriasDto obtenerConvocatorias(boolean borrado) {
        return new ListaConvocatoriasDto(convocatoriaService.buscarConvocatorias(borrado).size(),
                convocatoriaService.buscarConvocatorias(borrado).stream().map(ConvocatoriaDto::of).toList());
    }

    @Operation(summary = "Obtiene una convocatoria por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "convocatoria encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Convocatoria.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Convocatoria ordinaria",
                                                "cursoEscolar": "2023/2024"
                                            }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna convocatoria con ese ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ConvocatoriaDto obtenerConvocatoriaPorId(@PathVariable Long id) {
        return ConvocatoriaDto.of(convocatoriaService.buscarPorId(id));
    }




    @Operation(summary = "Obtiene  convocatorias por un curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "convocatorias encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Convocatoria.class),
                            examples = {@ExampleObject(
                                    value =
                                """
                                    {
                                        "cantidadConvocatorias": 2,
                                        "convocatorias": [
                                            {
                                                "nombre": "Convocatoria ordinaria",
                                                "cursoEscolar": "2023/2024",
                                                "listaDemanda": [
                                                    {
                                                        "cantidadAlumnos": 5,
                                                        "requisitos": "Conocimientos básicos en Java",
                                                        "nombreEmpresa": "EmpresaTech",
                                                        "nombreCurso": "Java Básico",
                                                        "cursoEscolar": "2023/2024",
                                                        "nombreConvocatoria": "Convocatoria ordinaria"
                                                    }
                                                ]
                                            },
                                            {
                                                "nombre": "Convocatoria extraordinaria",
                                                "cursoEscolar": "2023/2024",
                                                "listaDemanda": [
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
                                        ]
                                    }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna convocatoria con ese curso",
                    content = @Content)
    })
    @GetMapping("/curso/{cursoId}")
    public ListaConvocatoriasDto obtenerConvocatoriasPorCurso(@PathVariable Long cursoId){
        return ListaConvocatoriasDto.of(convocatoriaService.obtenerConvocatoriasPorCurso(cursoId).stream().map(ConvocatoriaDto::of).toList());
    }

    @Operation(summary = "Crea una convocatoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha registrado la convocatoria",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Convocatoria.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": 53,
                                                    "cursoEscolar": "2024-2025",
                                                    "nombre": "Convocatoria Ordinaria",
                                                    "listaDemandas": []
                                                }
                                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "405", description = "No se ha podido registrar la convocatoria",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Convocatoria> guardarConvocatoria(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Convocatoria a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Convocatoria.class),
                    examples = @ExampleObject(value = """
                                    {
                                      "cursoEscolar": "2024-2025",
                                      "nombre": "Convocatoria Extraordinaria (Septiembre)"
                                    }
                            """))) @RequestBody Convocatoria convocatoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(convocatoriaService.guardarConvocatoria(convocatoria));
    }

    @Operation(summary = "Actualiza una convocatoria existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Convocatoria actualizada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Convocatoria.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombre": "Convocatoria Extraordinaria (Septiembre)",
                                                "cursoEscolar": "2024-2025"
                                            }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna convocatoria con ese ID",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para la actualización",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ConvocatoriaDto actualizarConvocatoria(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Convocatoria a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Convocatoria.class),
                    examples = @ExampleObject(value = """
                                    {
                                      "cursoEscolar": "2024-2025",
                                      "nombre": "Convocatoria Extraordinaria (Septiembre)"
                                    }
                            """))) @RequestBody Convocatoria convocatoria, @PathVariable("id") Long convocatoriaId){

        return ConvocatoriaDto.of(convocatoriaService.editarConvocatoria(convocatoria, convocatoriaId));
    }

    @Operation(summary = "Elimina una convocatoria por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Convocatoria eliminada correctamente",
                    content = @Content),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?>eliminarConvocatoria(@PathVariable Long id){
        convocatoriaService.borrarConvocatoriaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
