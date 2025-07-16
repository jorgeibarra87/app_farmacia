package com.medicamentos.farmacia.app_farmacia.controller;

import com.medicamentos.farmacia.app_farmacia.model.entity.Usuario;
import com.medicamentos.farmacia.app_farmacia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crearUsuario")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        // Implementación del método para crear un usuario
        Usuario nuevoUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios() {
        // Implementación del método para obtener todos los usuarios
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
