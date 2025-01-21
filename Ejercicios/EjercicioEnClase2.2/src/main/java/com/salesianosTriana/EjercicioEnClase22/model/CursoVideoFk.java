package com.salesianosTriana.EjercicioEnClase22.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoVideoFk {
    private CursoOnline curso;
    private Long id;
}
