package com.medicamentos.farmacia.app_farmacia.service.impl;

import com.medicamentos.farmacia.app_farmacia.model.entity.Estado;
import com.medicamentos.farmacia.app_farmacia.model.entity.Servicio;
import com.medicamentos.farmacia.app_farmacia.repository.ServicioRepository;
import com.medicamentos.farmacia.app_farmacia.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Servicio createServicio(Servicio servicio){
        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio updateServicio(Servicio servicio) {
        Servicio servicioExistente = servicioRepository.findById(servicio.getIdServicio())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        servicioExistente.setNombre(servicio.getNombre());
        servicioExistente.setTipo(servicio.getTipo());
        servicioExistente.setEstadoServicio(servicio.getEstadoServicio());
        return servicioRepository.save(servicioExistente);
    }

    @Override
    public Optional<Servicio> findByIdServicio(Long idServicio) {
        return servicioRepository.findById(idServicio);
    }

    @Override
    public List<Servicio> findAllServicio() {
        return servicioRepository.findAll();
    }

    @Override
    public Servicio cambiarEstadoServicio(Long idServicio, Estado nuevoEstadoServicio) {
        Servicio sercicioExistente = servicioRepository.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        sercicioExistente.setEstadoServicio(nuevoEstadoServicio);
        return servicioRepository.save(sercicioExistente);
    }
}
