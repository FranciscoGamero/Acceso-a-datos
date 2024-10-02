package com.salesianostriana.dam.ejercicio_evaluacion1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RestController = @Controller + @ResponseBody en cada método
@RequiredArgsConstructor
@RequestMapping("/")
public class TagController {
}
