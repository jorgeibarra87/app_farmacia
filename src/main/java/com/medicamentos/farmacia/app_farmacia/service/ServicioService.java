package com.medicamentos.farmacia.app_farmacia.service;

import com.medicamentos.farmacia.app_farmacia.model.entity.Estado;
import com.medicamentos.farmacia.app_farmacia.model.entity.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {

    Servicio createServicio(Servicio servicio);
    Servicio updateServicio(Servicio servicio);
    Optional<Servicio> findByIdServicio(Long idServicio);
    List<Servicio> findAllServicio();
    Servicio cambiarEstadoServicio(Long idServicio, Estado nuevoEstadoServicio);
}
