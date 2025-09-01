package com.medicamentos.farmacia.app_farmacia.service.impl;

import com.medicamentos.farmacia.app_farmacia.model.dto.PacientePerfilDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.PerfilFarmacoterapeuticoDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.ServicioDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.UsuarioDTO;
import com.medicamentos.farmacia.app_farmacia.service.PerfilFarmacoterapeuticoDataSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("mockDataSource")
public class MockPerfilFarmacoterapeuticoDataSourceImpl implements PerfilFarmacoterapeuticoDataSource {

    private List<PerfilFarmacoterapeuticoDTO> perfilesMock;
    private List<UsuarioDTO> usuariosMock;
    private List<ServicioDTO> serviciosMock;

    public MockPerfilFarmacoterapeuticoDataSourceImpl() {
        inicializarDatosMock();
    }

    private void inicializarDatosMock() {
        // Inicializar servicios mock
        serviciosMock = Arrays.asList(
                new ServicioDTO(1L, "UCI", "Crítico", "ACTIVO"),
                new ServicioDTO(2L, "Medicina Interna", "Hospitalización", "ACTIVO"),
                new ServicioDTO(3L, "Pediatría", "Hospitalización", "ACTIVO"),
                new ServicioDTO(4L, "Urgencias", "Urgencias", "ACTIVO")
        );

        // Inicializar perfiles farmacoterapéuticos mock
        perfilesMock = new ArrayList<>();
        perfilesMock.add(createPerfilMock(1L, "MED001", "Amoxicilina 500mg",
                "Oral", "30 tabletas", 30.0, 28.0, 25.0, 3.0));
        perfilesMock.add(createPerfilMock(2L, "MED002", "Ibuprofeno 400mg",
                "Oral", "20 tabletas", 20.0, 20.0, 18.0, 2.0));
        perfilesMock.add(createPerfilMock(3L, "MED003", "Omeprazol 20mg",
                "Oral", "14 cápsulas", 14.0, 14.0, 14.0, 0.0));
        perfilesMock.add(createPerfilMock(4L, "MED004", "Metformina 850mg",
                "Oral", "60 tabletas", 60.0, 60.0, 45.0, 0.0));
        perfilesMock.add(createPerfilMock(5L, "MED005", "Losartán 50mg",
                "Oral", "30 tabletas", 30.0, 30.0, 28.0, 0.0));

        // Inicializar usuarios mock
        usuariosMock = new ArrayList<>();
        usuariosMock.add(createUsuarioMock(1L, "1234567890", "Juan Pérez García",
                serviciosMock.get(0), perfilesMock.get(0)));
        usuariosMock.add(createUsuarioMock(2L, "0987654321", "María López Rodríguez",
                serviciosMock.get(1), perfilesMock.get(1)));
        usuariosMock.add(createUsuarioMock(3L, "1122334455", "Carlos Martínez Silva",
                serviciosMock.get(1), perfilesMock.get(2)));
        usuariosMock.add(createUsuarioMock(4L, "5544332211", "Ana González Torres",
                serviciosMock.get(2), perfilesMock.get(3)));
        usuariosMock.add(createUsuarioMock(5L, "9988776655", "Pedro Ramírez Díaz",
                serviciosMock.get(3), perfilesMock.get(4)));
    }

    private PerfilFarmacoterapeuticoDTO createPerfilMock(Long id, String codigo,
                                                           String descripcion, String via, String cantidadFormulada,
                                                           Double cantidadDespachada, Double cantidadAplicada,
                                                           Double cantidadDevuelta, Double cantidadRestante) {

        PerfilFarmacoterapeuticoDTO perfil = new PerfilFarmacoterapeuticoDTO();
        perfil.setIdPerfilFarmacoterapeutico(id);
        perfil.setCodigo(codigo);
        perfil.setDescripcion(descripcion);
        perfil.setVia(via);
        perfil.setCantidadFormulada(cantidadFormulada);
        perfil.setFechaFormulacion(LocalDateTime.now().minusDays(5));
        perfil.setCantidadDespachada(cantidadDespachada);
        perfil.setFechaDespacho(LocalDateTime.now().minusDays(4));
        perfil.setCantidadAplicada(cantidadAplicada);
        perfil.setFechaAplicacion(LocalDateTime.now().minusDays(1));
        perfil.setCantidadDevuelta(cantidadDevuelta);
        perfil.setFechaDevolucion(cantidadDevuelta > 0 ? LocalDateTime.now() : null);
        perfil.setPosologia("1 cada 8 horas");
        perfil.setDiagnosticoPrincipal("Infección respiratoria alta");
        perfil.setDiagnosticoSecundario("Hipertensión arterial controlada");
        perfil.setAntecedentesAlergia("No refiere");
        perfil.setAntecedentesFarmacologicos("Tratamiento previo con antibióticos");
        perfil.setObservacion("Paciente con buena adherencia al tratamiento");
        perfil.setEstadoPerfilFarmacoterapeutico("ACTIVO");

        return perfil;
    }

    private UsuarioDTO createUsuarioMock(Long id, String identificacion,
                                         String nombre, ServicioDTO servicio, PerfilFarmacoterapeuticoDTO perfil) {

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setIdUsuario(id);
        usuario.setIdentificacion(identificacion);
        usuario.setNombre(nombre);
        usuario.setIngreso("INGRESO-" + id);
        usuario.setFechaNacimiento(new Date(System.currentTimeMillis() -
                (long)(Math.random() * 2000000000000L)));
        usuario.setFechaIngreso(new Date());
        usuario.setEstadoUsuario("ACTIVO");
        usuario.setServicio(servicio);
        usuario.setPerfilFarmacoterapeutico(perfil);

        return usuario;
    }

    @Override
    public List<PerfilFarmacoterapeuticoDTO> obtenerTodosLosPerfiles() {
        return new ArrayList<>(perfilesMock);
    }

    @Override
    public Optional<PerfilFarmacoterapeuticoDTO> obtenerPerfilPorId(Long id) {
        return perfilesMock.stream()
                .filter(p -> p.getIdPerfilFarmacoterapeutico().equals(id))
                .findFirst();
    }

    @Override
    public List<PerfilFarmacoterapeuticoDTO> obtenerPerfilesPorUsuario(String identificacion) {
        return usuariosMock.stream()
                .filter(u -> u.getIdentificacion().equals(identificacion))
                .map(UsuarioDTO::getPerfilFarmacoterapeutico)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PacientePerfilDTO> obtenerPerfilCompletoPaciente(String identificacion) {
        return usuariosMock.stream()
                .filter(u -> u.getIdentificacion().equals(identificacion))
                .findFirst()
                .map(usuario -> {
                    PacientePerfilDTO pacientePerfil = new PacientePerfilDTO();
                    pacientePerfil.setUsuario(usuario);
                    pacientePerfil.setPerfilFarmacoterapeutico(usuario.getPerfilFarmacoterapeutico());
                    pacientePerfil.setServicio(usuario.getServicio());
                    return pacientePerfil;
                });
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorServicio(Long idServicio) {
        return usuariosMock.stream()
                .filter(u -> u.getServicio() != null &&
                        u.getServicio().getIdServicio().equals(idServicio))
                .collect(Collectors.toList());
    }
}
