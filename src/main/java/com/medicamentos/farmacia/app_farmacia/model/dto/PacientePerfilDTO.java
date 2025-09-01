package com.medicamentos.farmacia.app_farmacia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacientePerfilDTO {
    private UsuarioDTO usuario;
    private PerfilFarmacoterapeuticoDTO perfilFarmacoterapeutico;
    private ServicioDTO servicio;
}