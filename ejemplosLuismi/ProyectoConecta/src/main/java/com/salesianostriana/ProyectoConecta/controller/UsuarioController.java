package com.salesianostriana.ProyectoConecta.controller;

import com.salesianostriana.ProyectoConecta.dto.EditUsuarioDto;
import com.salesianostriana.ProyectoConecta.dto.ListaUsuariosDto;
import com.salesianostriana.ProyectoConecta.dto.UsuarioDto;
import com.salesianostriana.ProyectoConecta.models.Titulo;
import com.salesianostriana.ProyectoConecta.models.Usuario;
import com.salesianostriana.ProyectoConecta.service.UsuarioService;
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
@RequestMapping("/usuario")
@RequiredArgsConstructor

@Tag(name = "Usuario", description = "El controlador de usuario, para poder realizar todas las operaciones de gestión")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @Operation(summary = "Obtiene el usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado el usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)),
                            examples = {@ExampleObject(
                                    value = """
                            
                             {
                                "username": "juanp",
                                "role": "ROLE_PROFESOR"
                             }
                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404", description = "No se ha el encontrado el usuario",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public UsuarioDto obtenerUsuario(@PathVariable Long id){
        return UsuarioDto.of(usuarioService.obternerUsuarioPorId(id));
    }



    @Operation(summary = "Obtiene todos los usuarios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado usuarios",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "cantidadUsuarios": 2,
                                                "List": [
                                                    {
                                                        "username": "juanp",
                                                        "role": "ROLE_PROFESOR"
                                                    },
                                                    {
                                                        "username": "anag",
                                                        "role": "ROLE_PROFESOR"
                                                    }
                                                ]
                                            }
                                            """
                            )}
                    )}),
    })
    @GetMapping
    public ListaUsuariosDto obtenerUsuarios(boolean borrado){
        return ListaUsuariosDto.of(usuarioService.obtenerUsuarios(borrado));
    }


    @Operation(summary = "Edita un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado un usuario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Usuario.class)),
                            examples = {@ExampleObject(
                                    value = """
                                {  
                                
                                "username": paco
                            
                                }

                            
                            """
                            )}
                    )}),

            @ApiResponse(responseCode = "404", description = "No se ha podido editar el usuario",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public EditUsuarioDto actualizarUsuario(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Usuario a editar", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class),
                    examples = @ExampleObject(value = """
                                                {
                                                "username": "paco"
                                                }
                            """))) @RequestBody Usuario usuario, @PathVariable("id") Long id){
        return EditUsuarioDto.of(usuarioService.editarUsuario(usuario, id));
    }

    @Operation(summary = "Elimina un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado el usuario",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        usuarioService.borrarUsuario(id);
    }

}
