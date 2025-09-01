package com.medicamentos.farmacia.app_farmacia.service.impl;

import com.medicamentos.farmacia.app_farmacia.model.entity.Estado;
import com.medicamentos.farmacia.app_farmacia.model.entity.PerfilFarmacoterapeutico;
import com.medicamentos.farmacia.app_farmacia.repository.PerfilFarmacoterapeuticoRepository;
import com.medicamentos.farmacia.app_farmacia.service.PerfilFarmacoterapeuticoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@NoArgsConstructor
@AllArgsConstructor
@Data
@Service
public class PerfilFarmacoterapeuticoServiceImpl implements PerfilFarmacoterapeuticoService {

    private final PerfilFarmacoterapeuticoRepository perfilFarmacoterapeuticoRepository;

    @Override
    public PerfilFarmacoterapeutico createPerfilFarmacoterapeutico(PerfilFarmacoterapeutico perfilFarmacoterapeutico) {
        return perfilFarmacoterapeuticoRepository.save(perfilFarmacoterapeutico);
    }

    @Override
    public PerfilFarmacoterapeutico updatePerfilFarmacoterapeutico(PerfilFarmacoterapeutico perfilFarmacoterapeutico) {
        PerfilFarmacoterapeutico perfilFarmacoterapeuticoExistente = perfilFarmacoterapeuticoRepository.findById(perfilFarmacoterapeutico.getIdPerfilFarmacoterapeutico())
                .orElseThrow(() -> new RuntimeException("PerfilFarmacoterapeutico no encontrado"));

        perfilFarmacoterapeuticoExistente.setCodigo(perfilFarmacoterapeutico.getCodigo());
        perfilFarmacoterapeuticoExistente.setDescripcion(perfilFarmacoterapeutico.getDescripcion());
        perfilFarmacoterapeuticoExistente.setVia(perfilFarmacoterapeutico.getVia());
        perfilFarmacoterapeuticoExistente.setCantidadFormulada(perfilFarmacoterapeutico.getCantidadFormulada());
        perfilFarmacoterapeuticoExistente.setFechaFormulacion(perfilFarmacoterapeutico.getFechaFormulacion());
        perfilFarmacoterapeuticoExistente.setCantidadDespachada(perfilFarmacoterapeutico.getCantidadDespachada());
        perfilFarmacoterapeuticoExistente.setFechaDespacho(perfilFarmacoterapeutico.getFechaDespacho());
        perfilFarmacoterapeuticoExistente.setCantidadAplicada(perfilFarmacoterapeutico.getCantidadAplicada());
        perfilFarmacoterapeuticoExistente.setFechaAplicacion(perfilFarmacoterapeutico.getFechaAplicacion());
        perfilFarmacoterapeuticoExistente.setCantidadDevuelta(perfilFarmacoterapeutico.getCantidadDevuelta());
        perfilFarmacoterapeuticoExistente.setFechaDevolucion(perfilFarmacoterapeutico.getFechaDevolucion());
        perfilFarmacoterapeuticoExistente.setPosologia(perfilFarmacoterapeutico.getPosologia());
        perfilFarmacoterapeuticoExistente.setDiagnosticoPrincipal(perfilFarmacoterapeutico.getDiagnosticoPrincipal());
        perfilFarmacoterapeuticoExistente.setDiagnosticoSecundario(perfilFarmacoterapeutico.getDiagnosticoSecundario());
        perfilFarmacoterapeuticoExistente.setAntecedentesAlergia(perfilFarmacoterapeutico.getAntecedentesAlergia());
        perfilFarmacoterapeuticoExistente.setAntecedentesFarmacologicos(perfilFarmacoterapeutico.getAntecedentesFarmacologicos());
        perfilFarmacoterapeuticoExistente.setObservacion(perfilFarmacoterapeutico.getObservacion());
        perfilFarmacoterapeuticoExistente.setEstadoPerfilFarmacoterapeutico(perfilFarmacoterapeutico.getEstadoPerfilFarmacoterapeutico());
        return perfilFarmacoterapeuticoRepository.save(perfilFarmacoterapeuticoExistente);
    }

    @Override
    public Optional<PerfilFarmacoterapeutico> findPerfilFarmacoterapeuticoById(Long id) {
        return perfilFarmacoterapeuticoRepository.findById(id);
    }

    @Override
    public List<PerfilFarmacoterapeutico> findAllPerfilesFarmacoterapeuticos() {
        return perfilFarmacoterapeuticoRepository.findAll();
    }

    @Override
    public PerfilFarmacoterapeutico cambiarEstadoPerfilFarmacoterapeutico(Long id, Estado nuevoEstadoPerfil) {
        PerfilFarmacoterapeutico perfilFarmacoterapeuticoExistente = perfilFarmacoterapeuticoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PerfilFarmacoterapeutico no encontrado"));
        perfilFarmacoterapeuticoExistente.setEstadoPerfilFarmacoterapeutico(nuevoEstadoPerfil);
        return perfilFarmacoterapeuticoRepository.save(perfilFarmacoterapeuticoExistente);
    }
}
