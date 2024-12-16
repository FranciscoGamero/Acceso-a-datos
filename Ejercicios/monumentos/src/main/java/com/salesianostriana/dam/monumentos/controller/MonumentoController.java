package com.salesianostriana.dam.monumentos.controller;

import com.salesianostriana.dam.monumentos.error.MonumentoNotFoundException;
import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.service.MonumentoService;
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
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/monumento")
@Tag(name = "Monumento", description = "El controlador de monumento, para poder realizar todas las operaciones de gestión")
public class MonumentoController {

    private final MonumentoService monumentoService;

    @Operation(summary = "Obtiene todos los monumentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado monumentos",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Monumento.class)),
                            examples = {@ExampleObject(
                                    value = """
[
    {
        "id": 1,
        "codPais": "IT",
        "nombrePais": "Italia",
        "nombreCiudad": "Roma",
        "latitud": "41.89",
        "longitud": "12.49",
        "nombreMonumento": "Coliseo Romano",
        "descripcionMonumento": "El Coliseo o Anfiteatro Flavio ​ es un anfiteatro de la época del Imperio romano",
        "imagenMonumento": "https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg"
    },
    {
        "id": 2,
        "codPais": "ES",
        "nombrePais": "España",
        "nombreCiudad": "Sevilla",
        "latitud": "37.44",
        "longitud": "-6.04",
        "nombreMonumento": "Itálica",
        "descripcionMonumento": "Extensa y antigua ciudad romana bien conservada que cuenta con las ruinas de un templo",
        "imagenMonumento": "https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg"
    }
]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún monumento",
                    content = @Content),
    })
    @GetMapping
    public List<Monumento> listaMonumentoFiltrado(
            @RequestParam(required = false, value = "sort", defaultValue = "no") String sortDirection) {
        return monumentoService.ordenarPorNombre(sortDirection);
    }

    @Operation(summary = "Obtiene un monumento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Monumento encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Monumento.class),
                            examples = {@ExampleObject(
                                    value = """
                                {
                                    "id": 1,
                                    "codPais": "IT",
                                    "nombrePais": "Italia",
                                    "nombreCiudad": "Roma",
                                    "latitud": "41.89",
                                    "longitud": "12.49",
                                    "nombreMonumento": "Coliseo Romano",
                                    "descripcionMonumento": "El Coliseo o Anfiteatro Flavio ​ es un anfiteatro de la época del Imperio romano",
                                    "imagenMonumento": "https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg"
                                }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún monumento con ese ID",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Monumento obtenerMonumento(@PathVariable Long id) {
        return monumentoService.getMonumentoPorId(id);
    }

    @Operation(summary = "Añade un nuevo monumento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Monumento añadido correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Monumento.class),
                            examples = {@ExampleObject(
                                    value = """
                                {
                                    "id": 3,
                                    "codPais": "FR",
                                    "nombrePais": "Francia",
                                    "nombreCiudad": "París",
                                    "latitud": "48.85",
                                    "longitud": "2.35",
                                    "nombreMonumento": "Torre Eiffel",
                                    "descripcionMonumento": "La Torre Eiffel es una estructura de hierro forjado ubicada en el Champ de Mars, a orillas del río Sena",
                                    "imagenMonumento": "https://www.example.com/torre_eiffel.jpg"
                                }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta o datos inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Monumento> guardarMonumento(@RequestBody Monumento monumento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(monumentoService.crearMonumento(monumento));
    }

    @Operation(summary = "Actualiza un monumento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Monumento actualizado correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Monumento.class),
                            examples = {@ExampleObject(
                                    value = """
                                {
                                    "id": 1,
                                    "codPais": "IT",
                                    "nombrePais": "Italia",
                                    "nombreCiudad": "Roma",
                                    "latitud": "41.89",
                                    "longitud": "12.49",
                                    "nombreMonumento": "Coliseo Romano",
                                    "descripcionMonumento": "Actualización de la descripción del Coliseo Romano",
                                    "imagenMonumento": "https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg"
                                }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún monumento con ese ID",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para la actualización",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Monumento actualizarMonumento(
            @RequestBody Monumento product,
            @PathVariable("id") Long productId) {

        return monumentoService.editarMonumento(productId, product);

    }

    @Operation(summary = "Elimina un monumento por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Monumento eliminado correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningún monumento con ese ID",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMonumento(@PathVariable Long id) {
        monumentoService.eliminarMonumento(id);
        return ResponseEntity.noContent().build();
    }
}
