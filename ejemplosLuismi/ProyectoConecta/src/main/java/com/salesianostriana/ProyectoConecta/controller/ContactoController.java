package com.salesianostriana.ProyectoConecta.controller;

import com.salesianostriana.ProyectoConecta.dto.ContactoPackage.ContactoDto;
import com.salesianostriana.ProyectoConecta.dto.ContactoPackage.ListaContactosDto;

import com.salesianostriana.ProyectoConecta.models.Contacto;


import com.salesianostriana.ProyectoConecta.service.ContactoService;
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
@RequestMapping("/contacto")
@Tag(name = "Contacto", description = "El controlador de contacto, para poder realizar todas las operaciones de gestión")
public class ContactoController {

    private final ContactoService contactoService;


    @Operation(summary = "Obtiene todos los contactos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado contactos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Contacto.class)),
                            examples = {@ExampleObject(
                                    value = """
                                        {
                                            "cantidadContactos": 3,
                                            "listaContactos": [
                                                {
                                                    "nombreProfesor": "Juan",
                                                    "nombreTrabajador": "Juan",
                                                    "resumen": "Reunión inicial para definir los objetivos del curso y establecer los primeros pasos de colaboración.",
                                                    "canal": "Correo electrónico",
                                                    "fecha": "2025-01-29T10:00:00"
                                                },
                                                {
                                                    "nombreProfesor": "Juan",
                                                    "nombreTrabajador": "Juan",
                                                    "resumen": "Reunión inicial para definir los objetivos del curso y establecer los primeros pasos de colaboración.",
                                                    "canal": "Correo electrónico",
                                                    "fecha": "2025-01-29T10:00:00"
                                                },
                                                {
                                                    "nombreProfesor": "Juan",
                                                    "nombreTrabajador": "Juan",
                                                    "resumen": "Reunión inicial para definir los objetivos del curso y establecer los primeros pasos de colaboración.",
                                                    "canal": "Correo electrónico",
                                                    "fecha": "2025-01-29T10:00:00"
                                                }
                                            ]
                                        }
                                        """
                            )}
                    )}),
    })
    @GetMapping
    public ListaContactosDto obtenerContactos(boolean borrado) {
        return new ListaContactosDto(contactoService.mostrarTodos(borrado).size(),
                contactoService.mostrarTodos(borrado).stream().map(ContactoDto::of).toList());
    }



    @Operation(summary = "Registra un nuevo contacto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Contacto registrado correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contacto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "nombreProfesor": "Juan",
                                                "nombreTrabajador": "Juan",
                                                "resumen": "Reunión inicial para definir los objetivos del curso y establecer los primeros pasos de colaboración.",
                                                "canal": "Correo electrónico",
                                                "fecha": "2025-01-29T10:00:00"
                                            }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta o datos inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ContactoDto> guardarContacto(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Contacto a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Contacto.class),
                    examples = @ExampleObject(value = """
                                                        {
                                                          "contactoPK": {
                                                            "trabajador_id": 1,
                                                            "profesor_id": 1
                                                          },
                                                          "fecha": "2025-01-29T10:00:00",
                                                          "canal": "Correo electrónico",
                                                          "resumen": "Reunión inicial para definir los objetivos del curso y establecer los primeros pasos de colaboración."
                                                        }
                            """))) @RequestBody Contacto nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ContactoDto.of(contactoService.guardarContacto(nuevo)));
    }

    @Operation(summary = "Obtiene un Contacto por su Id autogenerado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Contacto encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contacto.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "nombreProfesor": "Juan",
                                                    "nombreTrabajador": "Juan",
                                                    "resumen": "Reunión inicial para definir los objetivos del curso y establecer los primeros pasos de colaboración.",
                                                    "canal": "Correo electrónico",
                                                    "fecha": "2025-01-29T10:00:00"
                                                }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún Contacto con ese ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ContactoDto obtenerContactoPorId(@PathVariable Long id) {
        return ContactoDto.of(contactoService.findByIdAutogenerado(id));
    }
    @Operation(summary = "Obtiene todos los contactos que tengan el nombre de la empresa que se le pasa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado contactos",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contacto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadContactos": 1,
                                                "listaContactos": [
                                                    {
                                                        "nombreProfesor": "Juan",
                                                        "nombreTrabajador": "Ana",
                                                        "resumen": "Primera reunión",
                                                        "canal": "email",
                                                        "fecha": "2025-01-01T10:00:00"
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })

    @GetMapping("/empresa/{nombreEmpresa}")
    public ListaContactosDto obtenerContactosPorNombreEmpresa(@PathVariable String nombreEmpresa){
        return ListaContactosDto.of(contactoService.buscarContactosPorNombreEmpresa(nombreEmpresa).stream().map(ContactoDto::of).toList());
    }


    @Operation(summary = "Obtiene todos los contactos que tengan el nombre de la familia profesional que se le pasa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado contactos",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contacto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadContactos": 1,
                                                "listaContactos": [
                                                    {
                                                        "nombreProfesor": "Juan",
                                                        "nombreTrabajador": "Ana",
                                                        "resumen": "Primera reunión",
                                                        "canal": "email",
                                                        "fecha": "2025-01-01T10:00:00"
                                                    }
                                                ]
                                            }
                                """
                            )}
                    )}),
    })
    @GetMapping("/familiaProfesional/{nombreFamilia}")
    public ListaContactosDto obtenerContactosPorNombreFamiliaProfesional(@PathVariable String nombreFamilia){
        return ListaContactosDto.of(contactoService.buscarContactosPorNombreFamiliaProfesional(nombreFamilia).stream().map(ContactoDto::of).toList());
    }




    @Operation(summary = "Obtiene todos los contactos que tengan el nombre del curso que se le pasa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado contactos",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contacto.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                              "cantidadContactos": 2,
                                               "listaContactos": [
                                                  {
                                                            "nombreProfesor": "Juan",
                                                            "nombreTrabajador": "Ana",
                                                            "resumen": "Primera reunión",
                                                            "canal": "email",
                                                            "fecha": "2025-01-01T10:00:00"
                                                        },
                                                        {
                                                            "nombreProfesor": "Juan",
                                                            "nombreTrabajador": "Juan",
                                                            "resumen": "Consulta técnica",
                                                            "canal": "teléfono",
                                                            "fecha": "2025-01-02T11:30:00"
                                                        }
                                                    ]
                                            }
                                """
                            )}
                    )}),
    })
    @GetMapping("/curso/{nombreCurso}")
    public ListaContactosDto obtenerContactosPorCurso(@PathVariable String nombreCurso){
        return ListaContactosDto.of(contactoService.buscarContactoPorCurso(nombreCurso).stream().map(ContactoDto::of).toList());
    }


    @Operation(summary = "Actualiza un contacto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Contacto actualizado correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contacto.class),
                            examples = {@ExampleObject(
                                    value = """
                                               {
                                                "nombreProfesor": "Juan",
                                                "nombreTrabajador": "Juan",
                                                "resumen": "Reunión inicial para definir los objetivos del curso y establecer los primeros pasos de colaboración.",
                                                "canal": "Llamada de telefono",
                                                "fecha": "2025-01-29T10:00:00"
                                            }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún contacto con ese ID",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para la actualización",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ContactoDto actualizarContacto(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Contacto a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Contacto.class),
                    examples = @ExampleObject(value = """
                                                        {
                                                          "contactoPK": {
                                                            "trabajador_id": 1,
                                                            "profesor_id": 1
                                                          },
                                                          "fecha": "2025-01-29T10:00:00",
                                                          "canal": "Correo electrónico",
                                                          "resumen": "Reunión inicial para definir los objetivos del curso y establecer los primeros pasos de colaboración."
                                                        }
                            """))) @RequestBody Contacto contacto, @PathVariable("id") Long contactoId){

        return ContactoDto.of(contactoService.editarContacto(contacto, contactoId));
    }
    @Operation(summary = "Elimina un Contacto por su Id autogenerado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Contacto eliminado correctamente",
                    content = @Content),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?>eliminarContacto(@PathVariable Long id){
        contactoService.borrarContactoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
