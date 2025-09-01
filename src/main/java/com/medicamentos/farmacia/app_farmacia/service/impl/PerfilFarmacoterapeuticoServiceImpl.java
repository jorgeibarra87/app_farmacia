package com.medicamentos.farmacia.app_farmacia.service.impl;

import com.medicamentos.farmacia.app_farmacia.model.dto.PacientePerfilDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.PerfilFarmacoterapeuticoDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.UsuarioDTO;
import com.medicamentos.farmacia.app_farmacia.model.entity.Estado;
import com.medicamentos.farmacia.app_farmacia.model.entity.PerfilFarmacoterapeutico;
import com.medicamentos.farmacia.app_farmacia.repository.PerfilFarmacoterapeuticoRepository;
import com.medicamentos.farmacia.app_farmacia.service.PerfilFarmacoterapeuticoDataSource;
import com.medicamentos.farmacia.app_farmacia.service.PerfilFarmacoterapeuticoService;
import jakarta.annotation.PostConstruct;
//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Service
public class PerfilFarmacoterapeuticoServiceImpl implements PerfilFarmacoterapeuticoService {

    @Value("${app.data.source:mock}")
    private String dataSourceType;

    @Autowired
    @Qualifier("mockDataSource")
    private PerfilFarmacoterapeuticoDataSource mockDataSource;

    @Autowired(required = false)
    @Qualifier("apiDataSource")
    private PerfilFarmacoterapeuticoDataSource apiDataSource;

    private PerfilFarmacoterapeuticoDataSource activeDataSource;

    private final PerfilFarmacoterapeuticoRepository perfilFarmacoterapeuticoRepository;

    @PostConstruct
    public void init() {
        if ("api".equals(dataSourceType) && apiDataSource != null) {
            activeDataSource = apiDataSource;
            System.out.println("Usando fuente de datos: API REAL");
        } else {
            activeDataSource = mockDataSource;
            System.out.println("Usando fuente de datos: MOCK (Simulada)");
        }
    }

    public List<PerfilFarmacoterapeuticoDTO> obtenerTodosLosPerfiles() {
        return activeDataSource.obtenerTodosLosPerfiles();
    }

    public Optional<PerfilFarmacoterapeuticoDTO> obtenerPerfilPorId(Long id) {
        return activeDataSource.obtenerPerfilPorId(id);
    }

    public List<PerfilFarmacoterapeuticoDTO> obtenerPerfilesPorPaciente(String identificacion) {
        return activeDataSource.obtenerPerfilesPorUsuario(identificacion);
    }

    public Optional<PacientePerfilDTO> obtenerPerfilCompletoPaciente(String identificacion) {
        return activeDataSource.obtenerPerfilCompletoPaciente(identificacion);
    }

    public List<UsuarioDTO> obtenerPacientesPorServicio(Long idServicio) {
        return activeDataSource.obtenerUsuariosPorServicio(idServicio);
    }

    // Método adicional para verificar el estado del servicio
    public String obtenerTipoFuenteDatos() {
        return dataSourceType.toUpperCase();
    }

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
