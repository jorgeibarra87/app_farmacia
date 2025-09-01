package com.medicamentos.farmacia.app_farmacia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long idUsuario;
    private String identificacion;
    private String nombre;
    private String ingreso;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private String estadoUsuario;
    private ServicioDTO servicio;
    private PerfilFarmacoterapeuticoDTO perfilFarmacoterapeutico;
}