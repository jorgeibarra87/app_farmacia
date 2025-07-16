package com.medicamentos.farmacia.app_farmacia.service;

import com.medicamentos.farmacia.app_farmacia.model.entity.Estado;
import com.medicamentos.farmacia.app_farmacia.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario createUsuario(Usuario usuario);
    Usuario updateUsuario(Usuario usuario);
    Optional<Usuario> findByIdUsuario(Long id);
    List<Usuario> findAllUsuarios();
    Usuario cambiarEstadoUsuario(Long idUsuario, Estado nuevoEstadoUsuario);

}
