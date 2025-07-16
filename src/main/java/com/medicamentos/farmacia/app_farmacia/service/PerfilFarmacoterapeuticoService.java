package com.medicamentos.farmacia.app_farmacia.service;

import com.medicamentos.farmacia.app_farmacia.model.entity.Estado;
import com.medicamentos.farmacia.app_farmacia.model.entity.PerfilFarmacoterapeutico;

import java.util.List;
import java.util.Optional;

public interface PerfilFarmacoterapeuticoService {

    PerfilFarmacoterapeutico createPerfilFarmacoterapeutico(PerfilFarmacoterapeutico perfilFarmacoterapeutico);
    PerfilFarmacoterapeutico updatePerfilFarmacoterapeutico(PerfilFarmacoterapeutico perfilFarmacoterapeutico);
    Optional<PerfilFarmacoterapeutico> findPerfilFarmacoterapeuticoById(Long id);
    List<PerfilFarmacoterapeutico> findAllPerfilesFarmacoterapeuticos();
    PerfilFarmacoterapeutico cambiarEstadoPerfilFarmacoterapeutico(Long id, Estado nuevoEstadoPerfil);
}
