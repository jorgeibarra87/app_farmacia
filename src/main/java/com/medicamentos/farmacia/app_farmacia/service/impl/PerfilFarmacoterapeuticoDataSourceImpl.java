package com.medicamentos.farmacia.app_farmacia.service.impl;

import com.medicamentos.farmacia.app_farmacia.model.dto.PacientePerfilDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.PerfilFarmacoterapeuticoDTO;
import com.medicamentos.farmacia.app_farmacia.model.dto.UsuarioDTO;
import com.medicamentos.farmacia.app_farmacia.service.PerfilFarmacoterapeuticoDataSource;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("apiDataSource")
@ConditionalOnProperty(name = "app.data.source", havingValue = "api")
public class PerfilFarmacoterapeuticoDataSourceImpl implements PerfilFarmacoterapeuticoDataSource {

    private static final Logger logger = LoggerFactory.getLogger(PerfilFarmacoterapeuticoDataSourceImpl.class);
    private WebClient webClient;

    @Value("${app.api.base-url:http://localhost:8080/api}")
    private String apiBaseUrl;

    @Value("${app.api.timeout:10}")
    private int timeoutSeconds;

    @Value("${app.api.max-retry:3}")
    private int maxRetryAttempts;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(apiBaseUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(10 * 1024 * 1024)) // 10MB buffer
                .build();

        logger.info("WebClient configurado para API: {}", apiBaseUrl);
    }

    @Override
    public List<PerfilFarmacoterapeuticoDTO> obtenerTodosLosPerfiles() {
        try {
            return webClient.get()
                    .uri("/perfiles")
                    .retrieve()
                    .bodyToFlux(PerfilFarmacoterapeuticoDTO.class)
                    .collectList()
                    .timeout(Duration.ofSeconds(timeoutSeconds))
                    .retry(maxRetryAttempts)
                    .onErrorReturn(WebClientResponseException.class, Collections.emptyList())
                    .doOnError(error -> logger.error("Error obteniendo perfiles: ", error))
                    .block();
        } catch (Exception e) {
            logger.error("Error inesperado al obtener todos los perfiles: ", e);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<PerfilFarmacoterapeuticoDTO> obtenerPerfilPorId(Long id) {
        try {
            PerfilFarmacoterapeuticoDTO perfil = webClient.get()
                    .uri("/perfiles/{id}", id)
                    .retrieve()
                    .bodyToMono(PerfilFarmacoterapeuticoDTO.class)
                    .timeout(Duration.ofSeconds(timeoutSeconds))
                    .retry(maxRetryAttempts)
                    .onErrorResume(WebClientResponseException.NotFound.class,
                            error -> {
                                logger.warn("Perfil no encontrado con ID: {}", id);
                                return Mono.empty();
                            })
                    .onErrorResume(Exception.class,
                            error -> {
                                logger.error("Error obteniendo perfil con ID {}: ", id, error);
                                return Mono.empty();
                            })
                    .block();

            return Optional.ofNullable(perfil);
        } catch (Exception e) {
            logger.error("Error inesperado al obtener perfil por ID {}: ", id, e);
            return Optional.empty();
        }
    }

    @Override
    public List<PerfilFarmacoterapeuticoDTO> obtenerPerfilesPorUsuario(String identificacion) {
        try {
            return webClient.get()
                    .uri("/usuarios/{identificacion}/perfiles", identificacion)
                    .retrieve()
                    .bodyToFlux(PerfilFarmacoterapeuticoDTO.class)
                    .collectList()
                    .timeout(Duration.ofSeconds(timeoutSeconds))
                    .retry(maxRetryAttempts)
                    .onErrorReturn(WebClientResponseException.NotFound.class, Collections.emptyList())
                    .doOnError(error -> logger.error("Error obteniendo perfiles para usuario {}: ",
                            identificacion, error))
                    .block();
        } catch (Exception e) {
            logger.error("Error inesperado al obtener perfiles por usuario {}: ", identificacion, e);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<PacientePerfilDTO> obtenerPerfilCompletoPaciente(String identificacion) {
        try {
            PacientePerfilDTO pacientePerfil = webClient.get()
                    .uri("/pacientes/{identificacion}/perfil-completo", identificacion)
                    .retrieve()
                    .bodyToMono(PacientePerfilDTO.class)
                    .timeout(Duration.ofSeconds(timeoutSeconds))
                    .retry(maxRetryAttempts)
                    .onErrorResume(WebClientResponseException.NotFound.class,
                            error -> {
                                logger.warn("Perfil completo no encontrado para paciente: {}", identificacion);
                                return Mono.empty();
                            })
                    .onErrorResume(Exception.class,
                            error -> {
                                logger.error("Error obteniendo perfil completo para paciente {}: ",
                                        identificacion, error);
                                return Mono.empty();
                            })
                    .block();

            return Optional.ofNullable(pacientePerfil);
        } catch (Exception e) {
            logger.error("Error inesperado al obtener perfil completo del paciente {}: ",
                    identificacion, e);
            return Optional.empty();
        }
    }

    @Override
    public List<UsuarioDTO> obtenerUsuariosPorServicio(Long idServicio) {
        try {
            return webClient.get()
                    .uri("/servicios/{idServicio}/usuarios", idServicio)
                    .retrieve()
                    .bodyToFlux(UsuarioDTO.class)
                    .collectList()
                    .timeout(Duration.ofSeconds(timeoutSeconds))
                    .retry(maxRetryAttempts)
                    .onErrorReturn(WebClientResponseException.class, Collections.emptyList())
                    .doOnError(error -> logger.error("Error obteniendo usuarios para servicio {}: ",
                            idServicio, error))
                    .block();
        } catch (Exception e) {
            logger.error("Error inesperado al obtener usuarios por servicio {}: ", idServicio, e);
            return Collections.emptyList();
        }
    }

    // Método adicional para healthcheck de la API
    public boolean isApiAvailable() {
        try {
            return webClient.get()
                    .uri("/health")
                    .retrieve()
                    .toBodilessEntity()
                    .timeout(Duration.ofSeconds(5))
                    .map(response -> response.getStatusCode().is2xxSuccessful())
                    .onErrorReturn(false)
                    .block();
        } catch (Exception e) {
            logger.warn("API no disponible: ", e);
            return false;
        }
    }
}
