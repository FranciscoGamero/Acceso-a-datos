package com.salesianostriana.ProyectoConecta.controller;



import com.salesianostriana.ProyectoConecta.dto.EditTituloDto;
import com.salesianostriana.ProyectoConecta.dto.ListaTitulosDto;
import com.salesianostriana.ProyectoConecta.dto.TituloDto;
import com.salesianostriana.ProyectoConecta.models.Titulo;
import com.salesianostriana.ProyectoConecta.service.TituloService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/titulo")
@Tag(name = "Titulo", description = "El controlador de titulo, para poder realizar todas las operaciones de gestión")
public class TituloController {

    private final TituloService tituloService;


    @Operation(summary = "Registra un nuevo titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Titulo registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Titulo.class),
                            examples = {@ExampleObject(
                                    value = """
                                                {
                                                    "nombre": "Ingeniería en Telecomunicaciones",
                                                    "duracion": 5,
                                                    "grado": "Licenciatura"
                                                }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta o datos inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<TituloDto> guardarTitulo(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Titulo a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Titulo.class),
                    examples = @ExampleObject(value = """
                                                    {
                                                        "nombre": "Ingeniería en Telecomunicaciones",
                                                        "duracion": 5,
                                                        "grado": "Licenciatura",
                                                        "familiaProfesional": {
                                                        "id": 1
                                                      }
                                                    }
                            """))) @RequestBody Titulo nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(TituloDto.of(tituloService.guardarTitulo(nuevo)));
    }



    @Operation(summary = "Obtiene el titulo por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado el titulo",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Titulo.class)),
                            examples = {@ExampleObject(
                                    value = """
                            
                             {
                               "nombre": "Ingeniería en Sistemas",
                               "duracion": 5,
                               "grado": "Licenciatura"
                             }
                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404", description = "No se ha el encontrado el titulo",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public TituloDto obtenerTitulo(@PathVariable Long id) {
        return TituloDto.of(tituloService.obtenerTituloPorId(id));
    }




    @Operation(summary = "Obtiene todos los titulos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado titulos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Titulo.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadTitulos": 5,
                                                "List": [
                                                    {
                                                        "nombre": "Ingeniería en Sistemas",
                                                        "duracion": 5,
                                                        "grado": "Licenciatura"
                                                    },
                                                    {
                                                        "nombre": "Desarrollo de Software",
                                                        "duracion": 3,
                                                        "grado": "Tecnicatura"
                                                    },
                                                    {
                                                        "nombre": "Ciencias de la Computación",
                                                        "duracion": 4,
                                                        "grado": "Grado"
                                                    },
                                                    {
                                                        "nombre": "Administración de Empresas",
                                                        "duracion": 4,
                                                        "grado": "Licenciatura"
                                                    },
                                                    {
                                                        "nombre": "Marketing Digital",
                                                        "duracion": 2,
                                                        "grado": "Tecnicatura"
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })
    @GetMapping
    public ListaTitulosDto obtenerTitulos(boolean borrado){
        return ListaTitulosDto.of(tituloService.obtenerTitulos(borrado).stream().map(TituloDto::of).toList());
    }




    @Operation(summary = "Edita un titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado un titulo",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Titulo.class)),
                            examples = {@ExampleObject(
                                    value = """
                                {
                                   "nombre": "Ingeniería en Microinformatica",
                                   "duracion": 5,
                                   "grado": "Licenciatura"
                                }

                            
                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404", description = "No se ha podido editar el titulo",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public EditTituloDto actualizarTitulo(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Titulo a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Titulo.class),
                    examples = @ExampleObject(value = """
                                                    {
                                                         "nombre": "Ingeniería en Microinformatica",
                                                         "duracion": 5,
                                                         "grado": "Licenciatura",
                                                        "familiaProfesional": {
                                                        "id": 1
                                                      }
                                                    }
                            """))) @RequestBody Titulo titulo, @PathVariable("id") Long id){
        return EditTituloDto.of(tituloService.editarTitulo(titulo, id));
    }



    @Operation(summary = "Elimina un titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el titulo",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public void eliminarTitulo(@PathVariable Long id){
        tituloService.borrarTitulo(id);
    }

}
