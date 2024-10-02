package com.salesianostriana.dam.monumentos.controller;
import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.repository.MonumentoRepository;
import com.salesianostriana.dam.monumentos.repository.RepositoryMonumento;
import com.salesianostriana.dam.monumentos.service.MonumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RestController = @Controller + @ResponseBody en cada método
@RequiredArgsConstructor
@RequestMapping("/api/monumentos")
public class MonumentoController {

    @Autowired
    private MonumentoService monumentoService;

    @GetMapping
    public ResponseEntity<List<Monumento>> listaMonumentos() { //Se usa el responseEntity para poder gestionar los resultados
                                                               //ResponseEntity.notFound() O ResponseEntity.ok()
        List<Monumento> encontrados = monumentoService.ObtenerTodos();
        if (encontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrados);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Monumento> obtenerMonumento(@PathVariable Long id) {
        return ResponseEntity.of(monumentoService.obtenerPorId(id));
    }
    @PostMapping
    public ResponseEntity<Monumento> crearMonumento(@RequestBody Monumento monumento) {
        Monumento monumentoNuevo = monumentoService.guardar(monumento);
        return ResponseEntity.status(201).body(monumentoNuevo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Monumento> actualizarMonumento(@PathVariable Long id, @RequestBody Monumento monumento) {
        return ResponseEntity.of(monumentoService.obtenerPorId(id)
                .map(noActualizado -> {
            noActualizado.setNombreMonumento(monumento.getNombreMonumento());
            noActualizado.setCodPais(monumento.getCodPais());
            noActualizado.setNombrePais(monumento.getNombrePais());
            noActualizado.setNombreCiudad(monumento.getNombreCiudad());
            noActualizado.setNombreMonumento(monumento.getNombreMonumento());
            noActualizado.setDescripcionMonumento(monumento.getDescripcionMonumento());
            noActualizado.setLatitud(monumento.getLatitud());
            noActualizado.setLongitud(monumento.getLongitud());
            return monumentoService.guardar(noActualizado);
                }));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Monumento> eliminarMonumento(@PathVariable Long id) {
       if(monumentoService.obtenerPorId(id)!=null){
           monumentoService.eliminarMonumento(id);
       }
       return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/alumno/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (monumentoService.obtenerPorId(id)!=null)
            monumentoService.eliminarMonumento(id);

        return ResponseEntity.noContent().build();

    }

}
