package com.salesianostriana.ProyectoConecta.controller;

import com.salesianostriana.ProyectoConecta.dto.EditFamiliaProfesionalDto;
import com.salesianostriana.ProyectoConecta.dto.FamiliaProfesionalDto;
import com.salesianostriana.ProyectoConecta.dto.ListaFamiliaProfesionalDto;
import com.salesianostriana.ProyectoConecta.models.FamiliaProfesional;
import com.salesianostriana.ProyectoConecta.service.FamiliaProfesionalService;
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
@RequestMapping("/familiaProfesional")
@Tag(name = "Familia Profesional", description = "El controlador de familia profesional, para poder realizar todas las operaciones de gestión")
public class FamiliaProfesionalController {

    private final FamiliaProfesionalService familiaProfesionalService;




    @Operation(summary = "Registra una nueva familia profesional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Familia profesional registrada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FamiliaProfesional.class),
                            examples = {@ExampleObject(
                                    value = """
                                           {
                                                           "nombre": "Administracion y finanzas",
                                                           "listaTitulos": [],
                                                           "listaEmpresa": [
                                                               {
                                                                   "cif": "A12345678",
                                                                   "direccion": "Calle Falsa 123",
                                                                   "coordenadas": "37.3826,-5.9963",
                                                                   "nombre": "EmpresaTech",
                                                                   "listaProfesional": [],
                                                                   "listaDemandas": []
                                                               }
                                                           ]
                                                       }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "400",
                    description = "Solicitud incorrecta o datos inválidos",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<FamiliaProfesionalDto> guardarFamiliaProfesional (@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Familia Profesional a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FamiliaProfesional.class),
                    examples = @ExampleObject(value = """
                                                    {
                                                        "nombre": "Administracion y finanzas",
                                                        "listaTitulos": [],
                                                        "listaEmpresas": [
                                                            {
                                                                "id": 1
                                                            }
                                                        ]
                                                    }
                            """))) @RequestBody FamiliaProfesional nueva){
        return ResponseEntity.status(HttpStatus.CREATED).body(FamiliaProfesionalDto.of(familiaProfesionalService.agregarFamiliaProfesional(nueva)));
    }




    @Operation(summary = "Obtiene la familia profesional por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado la familia profesional",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FamiliaProfesional.class)),
                            examples = {@ExampleObject(
                                    value = """
                            
                                            {
                                                "nombre": "Informática y Comunicaciones",
                                                "listaTitulos": [],
                                                "listaEmpresas": []
                                            }
                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404", description = "No se ha encontrado la familia profesional",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public FamiliaProfesionalDto obtenerFamiliaProfesionalPorID(@PathVariable Long id){
        return FamiliaProfesionalDto.of(familiaProfesionalService.obtenerFamiliaProfesional(id));
    }




    @Operation(summary = "Obtiene todos las familias proefesionales registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las familias profesionales",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FamiliaProfesional.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                  "cantidadFamiliaProfesional": 5,
                                                  "List": [
                                                      {
                                                          "nombre": "Informática y Comunicaciones",
                                                          "listaTitulos": [],
                                                          "listaEmpresas": []
                                                      },
                                                      {
                                                          "nombre": "Administración y Gestión",
                                                          "listaTitulos": [],
                                                          "listaEmpresas": []
                                                      },
                                                      {
                                                          "nombre": "Sanidad",
                                                          "listaTitulos": [],
                                                          "listaEmpresas": []
                                                      },
                                                      {
                                                          "nombre": "Electricidad y Electrónica",
                                                          "listaTitulos": [],
                                                          "listaEmpresas": []
                                                      },
                                                      {
                                                          "nombre": "Hostelería y Turismo",
                                                          "listaTitulos": [],
                                                          "listaEmpresas": []
                                                      }
                                                  ]
                                              }
                                        """
                            )}
                    )}),
    })
    @GetMapping
    public ListaFamiliaProfesionalDto obtenerFamiliasProfesionales(boolean borrado){
        return ListaFamiliaProfesionalDto.of(familiaProfesionalService.obtenerFamiliasProfesionales(borrado));
    }



    @Operation(summary = "Actualiza una familia profesional existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Familia profesional actualizada correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FamiliaProfesional.class),
                            examples = {@ExampleObject(
                                    value = """
                                         {
                                            "nombre": "Informática y Telecomunicaciones",
                                            "listaTitulos": [],
                                            "listaEmpresas": []
                                         }
                                """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningúna familia profesional con ese ID",
                    content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Datos inválidos para la actualización",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public EditFamiliaProfesionalDto actualizarFamiliaProfesional(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Familia Profesional a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FamiliaProfesional.class),
                    examples = @ExampleObject(value = """
                                                        {
                                                           "nombre": "Informática y Telecomunicaciones",
                                                           "listaTitulos": [],
                                                           "listaEmpresas": [
                                                             {
                                                                "id": 51
                                                             }
                                                           ]
                                                        }
                            """))) @RequestBody FamiliaProfesional fp, @PathVariable("id") Long id){
        return EditFamiliaProfesionalDto.of(familiaProfesionalService.editarFamiliaProfesional(fp, id));
    }


    @Operation(summary = "Elimina una familia profesional por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Familia profesional eliminada correctamente",
                    content = @Content),

    })
    @DeleteMapping("/{id}")
    public void eliminarFamiliaProfesional(@PathVariable Long id){
        familiaProfesionalService.borrarFamiliaProfesional(id);
    }

}
