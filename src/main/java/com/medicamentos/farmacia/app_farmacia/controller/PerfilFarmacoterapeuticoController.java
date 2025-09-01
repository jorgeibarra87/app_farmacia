package com.medicamentos.farmacia.app_farmacia.controller;

import com.medicamentos.farmacia.app_farmacia.model.dto.PacientePerfilDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.PerfilFarmacoterapeuticoDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.UsuarioDTO;
import com.medicamentos.farmacia.app_farmacia.model.entity.PerfilFarmacoterapeutico;
import com.medicamentos.farmacia.app_farmacia.service.impl.PerfilFarmacoterapeuticoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfil-farmacoterapeutico")
@CrossOrigin(origins = "*")
public class PerfilFarmacoterapeuticoController {

    @Autowired
    private PerfilFarmacoterapeuticoServiceImpl perfilService;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Servicio activo - Fuente de datos: " +
                perfilService.obtenerTipoFuenteDatos());
    }

    @GetMapping("/dto")
    public ResponseEntity<List<PerfilFarmacoterapeuticoDTO>> obtenerTodosLosPerfiles() {
        List<PerfilFarmacoterapeuticoDTO> perfiles = perfilService.obtenerTodosLosPerfiles();
        return ResponseEntity.ok(perfiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilFarmacoterapeuticoDTO> obtenerPerfilPorId(@PathVariable Long id) {
        return perfilService.obtenerPerfilPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/paciente/{identificacion}")
    public ResponseEntity<List<PerfilFarmacoterapeuticoDTO>> obtenerPerfilesPorPaciente(
            @PathVariable String identificacion) {
        List<PerfilFarmacoterapeuticoDTO> perfiles =
                perfilService.obtenerPerfilesPorPaciente(identificacion);
        if (perfiles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(perfiles);
    }

    @GetMapping("/paciente/{identificacion}/completo")
    public ResponseEntity<PacientePerfilDTO> obtenerPerfilCompletoPaciente(
            @PathVariable String identificacion) {
        return perfilService.obtenerPerfilCompletoPaciente(identificacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/servicio/{idServicio}/pacientes")
    public ResponseEntity<List<UsuarioDTO>> obtenerPacientesPorServicio(
            @PathVariable Long idServicio) {
        List<UsuarioDTO> usuarios = perfilService.obtenerPacientesPorServicio(idServicio);
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/crearPerfil")
    public ResponseEntity<PerfilFarmacoterapeutico> createPerfilFarmacoterapeutico(@RequestBody PerfilFarmacoterapeutico perfilFarmacoterapeutico) {
        // Implementación del método para crear un perfil farmacoterapéutico
        PerfilFarmacoterapeutico nuevoPerfil = perfilService.createPerfilFarmacoterapeutico(perfilFarmacoterapeutico);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPerfil);
    }

    @GetMapping("/entidad")
    public ResponseEntity<List<PerfilFarmacoterapeutico>> findAllPerfiles(){
        // Implementación del método para obtener todos los perfiles farmacoterapeuticos
        List<PerfilFarmacoterapeutico> perfilFarmacoterapeutico= perfilService.findAllPerfilesFarmacoterapeuticos();
        return ResponseEntity.ok(perfilFarmacoterapeutico);
    }
}
