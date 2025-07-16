package com.medicamentos.farmacia.app_farmacia.repository;

import com.medicamentos.farmacia.app_farmacia.model.entity.Estado;
import com.medicamentos.farmacia.app_farmacia.model.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByEstadoServicio(Estado estadoServicio);
}
