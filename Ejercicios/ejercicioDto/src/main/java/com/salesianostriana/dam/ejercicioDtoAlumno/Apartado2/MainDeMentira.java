package com.salesianostriana.dam.ejercicioDtoAlumno.Apartado2;


import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.dto.AlumnoDto;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.models.Alumno;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.models.Curso;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado1.models.Direccion;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado2.dto.ProductoDto;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado2.models.Categoria;
import com.salesianostriana.dam.ejercicioDtoAlumno.Apartado2.models.Producto;

import java.util.List;

public class MainDeMentira {

    public static void main(String[] args) {
        Categoria categoria1 = Categoria.builder()
                .id(1L)
                .nombre("Electrónica")
                .build();

        Categoria categoria2 = Categoria.builder()
                .id(2L)
                .nombre("Hogar")
                .build();

        Categoria categoria3 = Categoria.builder()
                .id(3L)
                .nombre("Ropa")
                .build();

        Producto producto1 = Producto.builder()
                .id(1L)
                .nombre("Smartphone XYZ")
                .descripcion("Smartphone de última generación con pantalla OLED y 128GB de almacenamiento.")
                .pvp(699.99)
                .imagenes(List.of("img1.jpg", "img2.jpg", "img3.jpg"))
                .categoria(Categoria.builder().id(1L).nombre("Electrónica").build())
                .build();

        Producto producto2 = Producto.builder()
                .id(2L)
                .nombre("Aspiradora UltraPower")
                .descripcion("Aspiradora inalámbrica con batería de larga duración y filtro HEPA.")
                .pvp(199.99)
                .imagenes(List.of("img1.jpg", "img2.jpg"))
                .categoria(Categoria.builder().id(2L).nombre("Hogar").build())
                .build();

        Producto producto3 = Producto.builder()
                .id(3L)
                .nombre("Camiseta Básica")
                .descripcion("Camiseta de algodón 100% disponible en varios colores y tallas.")
                .pvp(19.99)
                .imagenes(List.of("img1.jpg"))
                .categoria(Categoria.builder().id(3L).nombre("Ropa").build())
                .build();

        ProductoDto dto1 = ProductoDto.of(producto1);
        ProductoDto dto2 = ProductoDto.of(producto2);
        ProductoDto dto3 = ProductoDto.of(producto3);
    }
}
