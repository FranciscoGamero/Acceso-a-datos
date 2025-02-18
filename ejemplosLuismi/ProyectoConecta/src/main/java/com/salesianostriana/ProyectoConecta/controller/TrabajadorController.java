package com.salesianostriana.ProyectoConecta.controller;

import com.salesianostriana.ProyectoConecta.dto.ListaTrabajadorDto;
import com.salesianostriana.ProyectoConecta.dto.TrabajadorDto;
import com.salesianostriana.ProyectoConecta.models.Trabajador;
import com.salesianostriana.ProyectoConecta.service.TrabajadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/trabajador")
@RequiredArgsConstructor
@Tag(name = "Trabajador", description = "El controlador de trabajador, para poder realizar todas las operaciones de gestión")

public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    @Operation(summary = "Obtiene todos los Trabajadores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado Trabajadores",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Trabajador.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadTrabajadores": 2,
                                                "listaTrabajadores": [
                                                    {
                                                        "id": 1,
                                                        "nombre": "Juan",
                                                        "apellidos": "Pérez",
                                                        "email": "juan.perez@techcorp.com",
                                                        "telefono": "123456789",
                                                        "puesto": "Desarrollador",
                                                        "area": "IT",
                                                        "empresa": null
                                                    },
                                                    {
                                                        "id": 51,
                                                        "nombre": "Ana",
                                                        "apellidos": "García",
                                                        "email": "ana.garcia@bioclick.com",
                                                        "telefono": "987654321",
                                                        "puesto": "Manager",
                                                        "area": "Recursos Humanos",
                                                        "empresa": null
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })
    @GetMapping
    public ListaTrabajadorDto obtenerTrabajadores(boolean borrado) {
        return new ListaTrabajadorDto(trabajadorService.buscarTodos(borrado).size(),
                trabajadorService.buscarTodos(borrado).stream().map(TrabajadorDto::of).toList());
    }

    @Operation(summary = "Obtiene un Trabajador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Trabajador encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trabajador.class),
                            examples = {@ExampleObject(
                                    value = """
                                    {
                                        "id": 1,
                                        "nombre": "Juan",
                                        "apellidos": "Pérez",
                                        "email": "juan.perez@techcorp.com",
                                        "telefono": "123456789",
                                        "puesto": "Desarrollador",
                                        "area": "IT",
                                        "empresa": null
                                    }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún Trabajador con ese ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public TrabajadorDto obtenerTrabajador(@PathVariable Long id) {
        return TrabajadorDto.of(trabajadorService.buscarPorId(id));
    }

    @Operation(summary = "Registra un nuevo trabajador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Trabajador registrado correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trabajador.class),
                            examples = {@ExampleObject(
                                    value = """
                                    {
                                        "id": 2,
                                        "nombre": "Nuria",
                                        "apellidos": "Palomares",
                                        "email": "nuria.palomares@ejemplo.com",
                                        "telefono": "123456789",
                                        "puesto": "Desarrollador",
                                        "area": "IT",
                                        "empresa": null
                                    }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta o datos inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Trabajador> guardarTrabajador(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Trabajador a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Trabajador.class),
                    examples = @ExampleObject(value = """
                            {
                              "nombre": "Nuria",
                              "apellidos": "Palomares",
                              "email": "nuria.palomares@ejemplo.com",
                              "telefono": "123456789",
                              "puesto": "Desarrollador",
                              "area": "IT"
                            }
                            
                            """)))
    @RequestBody Trabajador nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(trabajadorService.guardarTrabajador(nuevo));
    }

    @Operation(summary = "Actualiza un trabajador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Trabajador actualizado correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trabajador.class),
                            examples = {@ExampleObject(
                                    value = """
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
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún trabajador con ese ID",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para la actualización",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Trabajador actualizarTrabajador(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Trabajador a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Trabajador.class),
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
                            """)))
                                               @RequestBody Trabajador trabajador, @PathVariable("id") Long trabajadorId) {

        return trabajadorService.editarTrabajador(trabajador, trabajadorId);
    }

    @Operation(summary = "Elimina un Trabajador por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Trabajador eliminado correctamente",
                    content = @Content),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?>eliminarTrabajador(@PathVariable Long id){
        trabajadorService.borrarTrabajadorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
