package com.medicamentos.farmacia.app_farmacia.controller;

import com.medicamentos.farmacia.app_farmacia.model.entity.Servicio;
import com.medicamentos.farmacia.app_farmacia.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping("/crearServicio")
    public ResponseEntity<?> createServicio(@RequestBody Servicio servicio) {
        // Implementación del método para crear un usuario
        Servicio nuevoServicio = servicioService.createServicio(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoServicio);
    }

    @GetMapping
    public ResponseEntity<List<Servicio>> findAllServicios() {
        // Implementación del método para obtener todos los usuarios
        List<Servicio> servicios = servicioService.findAllServicio();
        return ResponseEntity.ok(servicios);
    }
}
