package com.medicamentos.farmacia.app_farmacia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilFarmacoterapeuticoDTO {
    private Long idPerfilFarmacoterapeutico;
    private String codigo;
    private String descripcion;
    private String via;
    private String cantidadFormulada;
    private LocalDateTime fechaFormulacion;
    private Double cantidadDespachada;
    private LocalDateTime fechaDespacho;
    private Double cantidadAplicada;
    private LocalDateTime fechaAplicacion;
    private Double cantidadDevuelta;
    private LocalDateTime fechaDevolucion;
    private String posologia;
    private String diagnosticoPrincipal;
    private String diagnosticoSecundario;
    private String antecedentesAlergia;
    private String antecedentesFarmacologicos;
    private String observacion;
    private String estadoPerfilFarmacoterapeutico;
}
