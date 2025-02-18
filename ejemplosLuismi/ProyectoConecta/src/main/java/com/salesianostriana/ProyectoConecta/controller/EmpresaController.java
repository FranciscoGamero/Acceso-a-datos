package com.salesianostriana.ProyectoConecta.controller;


import com.salesianostriana.ProyectoConecta.dto.*;
import com.salesianostriana.ProyectoConecta.models.Empresa;
import com.salesianostriana.ProyectoConecta.service.EmpresaService;
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
@RequestMapping("/empresa")
@Tag(name = "Empresa", description = "El controlador de empresa, para poder realizar todas las operaciones de gestión")
public class EmpresaController {

    private final EmpresaService empresaService;









    @Operation(summary = "Obtiene todas los empresas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado empresas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Empresa.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "cantidadEmpresas": 2,
                                                 "listaEmpresas": [
                                                     {
                                                         "cif": "A12345678",
                                                         "direccion": "Calle Falsa 123",
                                                         "coordenadas": "37.3826,-5.9963",
                                                         "nombre": "Empresa Tech",
                                                         "listaProfesional": []
                                                     },
                                                     {
                                                         "cif": "B87654321",
                                                         "direccion": "Avenida Siempreviva 742",
                                                         "coordenadas": "37.3891,-5.9845",
                                                         "nombre": "Empresa Innovación",
                                                         "listaProfesional": []
                                                     }
                                                 ]
                                             }
                                            """
                            )}
                    )}),
    })
    @GetMapping
    public ListaEmpresaDto obtenerEmpresas(boolean borrado) {
        return new ListaEmpresaDto(empresaService.buscarTodos(borrado).size(),
                empresaService.buscarTodos(borrado).stream().map(EmpresaDto::of).toList());
    }
    @Operation(summary = "Obtiene una empresa por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Empresa encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empresa.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cif": "A12345678",
                                                "direccion": "Calle Falsa 123",
                                                "coordenadas": "37.3826,-5.9963",
                                                "nombre": "Empresa Tech",
                                                "listaProfesional": []
                                            }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna empresa con ese ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public EmpresaDto obtenerEmpresaPorId(@PathVariable Long id) {
        return EmpresaDto.of(empresaService.buscarPorId(id));
    }


    @Operation(summary = "Registra una nueva empresa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Empresa registrada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empresa.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "id": 1,
                                                    "cif": "B12345678",
                                                    "direccion": "Calle Ejemplo 123, Sevilla",
                                                    "coordenadas": "37.3891,-5.9845",
                                                    "nombre": "Empresa Ejemplo S.L.",
                                                    "listaTrabajadores": [],
                                                    "listaDemandas": [],
                                                    "listaFamilias": []
                                                }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta o datos inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Empresa> guardarEmpresa(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Empresa a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Empresa.class),
                    examples = @ExampleObject(value = """
                                                    {
                                                      "cif": "B12345678",
                                                      "direccion": "Calle Ejemplo 123, Sevilla",
                                                      "coordenadas": "37.3891,-5.9845",
                                                      "nombre": "Empresa Ejemplo S.L.",
                                                      "listaFamilias":[
                                                        {
                                                            "id": 1
                                                        }
                                                      ]
                                                    }
                            """))) @RequestBody Empresa empresa){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.guardarEmpresa(empresa));
    }

    @Operation(summary = "Actualiza una empresa existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Empresa actualizada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Empresa.class),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "cif": "A12345678",
                                            "direccion": "Calle Falsa 362575",
                                            "coordenadas": "37.3826,-5.9963",
                                            "nombre": "Empresa Tech",
                                            "listaProfesional": [],
                                            "listaDemandas": [
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
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún empresa con ese ID",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para la actualización",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public EmpresaDto actualizarEmpresa(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Empresa a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Empresa.class),
                    examples = @ExampleObject(value = """
                                                        {
                                                            "cif": "A12345678",
                                                            "direccion": "Calle Falsa 362575",
                                                            "coordenadas": "37.3826,-5.9963",
                                                            "nombre": "Empresa Tech",
                                                            "listaProfesional": []
                                                        }
                            """))) @RequestBody Empresa empresa,@PathVariable("id") Long empresaId) {

        return EmpresaDto.of(empresaService.editarEmpresa(empresa, empresaId));
    }
    @Operation(summary = "Elimina una empresa por su Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Empresa eliminada correctamente",
                    content = @Content),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?>eliminarEmpresa(@PathVariable Long id){
        empresaService.borrarEmpresaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
