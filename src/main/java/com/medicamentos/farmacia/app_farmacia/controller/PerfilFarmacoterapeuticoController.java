package com.medicamentos.farmacia.app_farmacia.controller;

import com.medicamentos.farmacia.app_farmacia.model.entity.PerfilFarmacoterapeutico;
import com.medicamentos.farmacia.app_farmacia.service.PerfilFarmacoterapeuticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil-farmacoterapeutico")
public class PerfilFarmacoterapeuticoController {

    @Autowired
    private PerfilFarmacoterapeuticoService perfilFarmacoterapeuticoService;

    @PostMapping("/crearPerfil")
    public ResponseEntity<PerfilFarmacoterapeutico> createPerfilFarmacoterapeutico(@RequestBody PerfilFarmacoterapeutico perfilFarmacoterapeutico) {
        // Implementación del método para crear un perfil farmacoterapéutico
        PerfilFarmacoterapeutico nuevoPerfil = perfilFarmacoterapeuticoService.createPerfilFarmacoterapeutico(perfilFarmacoterapeutico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPerfil);
    }

    @GetMapping
    public ResponseEntity<List<PerfilFarmacoterapeutico>> findAllPerfiles(){
        // Implementación del método para obtener todos los perfiles farmacoterapeuticos
        List<PerfilFarmacoterapeutico> perfilFarmacoterapeutico= perfilFarmacoterapeuticoService.findAllPerfilesFarmacoterapeuticos();
        return ResponseEntity.ok(perfilFarmacoterapeutico);
    }
}
