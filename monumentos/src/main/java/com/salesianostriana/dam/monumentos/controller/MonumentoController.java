package com.salesianostriana.dam.monumentos.controller;
import com.salesianostriana.dam.monumentos.model.Monumento;
import com.salesianostriana.dam.monumentos.repository.MonumentoRepository;
import com.salesianostriana.dam.monumentos.repository.RepositoryMonumento;
import com.salesianostriana.dam.monumentos.service.MonumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RestController = @Controller + @ResponseBody en cada método
@RequiredArgsConstructor
@RequestMapping("/api/monumentos")
public class MonumentoController {

    private final MonumentoRepository monumentoRepository;
    private final MonumentoService monumentoService;

    @GetMapping
    public ResponseEntity<List<Monumento>> listaMonumentos() { //Se usa el responseEntity para poder gestionar los resultados
                                                               //ResponseEntity.notFound() O ResponseEntity.ok()
        monumentoRepository.save((Monumento.builder()
                .id(1)
                .codPais("IT")
                .nombrePais("Italia")
                .nombreCiudad("Roma")
                .latitud("41.89")
                .longitud("12.49")
                .descripcionMonumento("El Coliseo o Anfiteatro Flavio \u200B es un anfiteatro de la época del Imperio romano, construido en el siglo I. Está ubicado en el este del Foro Romano, y fue el más grande de los que se construyeron en el Imperio romano.")
                .nombreMonumento("Coliseo Romano")
                .imagenMonumento("https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg")
                .build()));
        monumentoRepository.save((Monumento.builder()
                .id(2)
                .codPais("ITA")
                .nombrePais("Italia")
                .nombreCiudad("Roma")
                .latitud("41.89")
                .longitud("12.49")
                .descripcionMonumento("El Coliseo o Anfiteatro Flavio \u200B es un anfiteatro de la época del Imperio romano, construido en el siglo I. Está ubicado en el este del Foro Romano, y fue el más grande de los que se construyeron en el Imperio romano.")
                .nombreMonumento("Coliseo Romano")
                .imagenMonumento("https://www.lavanguardia.com/files/og_thumbnail/uploads/2017/05/15/5fa3c5d7ef234.jpeg")
                .build()));
        List<Monumento> encontrados = monumentoRepository.findAll();
        if (encontrados.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(encontrados);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Monumento> obtenerMonumento(@PathVariable Long id) {
        Monumento monumento = monumentoRepository.findById(id).orElse(null);
        return monumento != null ? new ResponseEntity<>(monumento, HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<Monumento> crearMonumento(@RequestBody Monumento monumento) {
        Monumento nuevoMonumento = monumentoService.guardar(monumento);
        return new ResponseEntity<>(nuevoMonumento, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Monumento> actualizarMonumento(@PathVariable Long id, @RequestBody Monumento monumento) {
        Monumento nuevoMonumento = monumentoRepository.findById(id).orElse(null);
        if (nuevoMonumento == null) {
            return ResponseEntity.notFound().build();
        } else {
            nuevoMonumento.setDescripcionMonumento("Quevedo con el linton");
            nuevoMonumento = monumentoRepository.save(nuevoMonumento);
            return new ResponseEntity<>(nuevoMonumento, HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Monumento> eliminarMonumento(@PathVariable Long id) {
        monumentoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
