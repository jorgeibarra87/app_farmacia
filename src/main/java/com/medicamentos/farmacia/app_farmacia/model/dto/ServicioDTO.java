package com.medicamentos.farmacia.app_farmacia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDTO {
    private Long idServicio;
    private String nombre;
    private String tipo;
    private String estadoServicio;

    public ServicioDTO(String uci, String crítico, String activo) {
    }
}
