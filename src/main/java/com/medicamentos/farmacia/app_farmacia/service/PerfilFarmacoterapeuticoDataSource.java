package com.medicamentos.farmacia.app_farmacia.service;

import com.medicamentos.farmacia.app_farmacia.model.dto.PacientePerfilDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.PerfilFarmacoterapeuticoDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface PerfilFarmacoterapeuticoDataSource {
    List<PerfilFarmacoterapeuticoDTO> obtenerTodosLosPerfiles();
    Optional<PerfilFarmacoterapeuticoDTO> obtenerPerfilPorId(Long id);
    List<PerfilFarmacoterapeuticoDTO> obtenerPerfilesPorUsuario(String identificacion);
    Optional<PacientePerfilDTO> obtenerPerfilCompletoPaciente(String identificacion);
    List<UsuarioDTO> obtenerUsuariosPorServicio(Long idServicio);
}