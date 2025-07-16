package com.medicamentos.farmacia.app_farmacia.service.impl;

import com.medicamentos.farmacia.app_farmacia.model.entity.Estado;
import com.medicamentos.farmacia.app_farmacia.model.entity.Usuario;
import com.medicamentos.farmacia.app_farmacia.repository.UsuarioRepository;
import com.medicamentos.farmacia.app_farmacia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(usuario.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setIdentificacion(usuario.getIdentificacion());
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setIngreso(usuario.getIngreso());
        usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioExistente.setFechaIngreso(usuario.getFechaIngreso());
        usuarioExistente.setEstadoUsuario(usuario.getEstadoUsuario());
        usuarioExistente.setServicio(usuario.getServicio());
        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    public Optional<Usuario> findByIdUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario cambiarEstadoUsuario(Long idUsuario, Estado nuevoEstadoUsuario) {
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioExistente.setEstadoUsuario(nuevoEstadoUsuario);
        return usuarioRepository.save(usuarioExistente);
    }
}
