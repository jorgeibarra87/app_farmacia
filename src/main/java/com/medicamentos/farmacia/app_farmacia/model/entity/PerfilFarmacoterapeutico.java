package com.medicamentos.farmacia.app_farmacia.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "perfil_farmacoterapeutico", schema = "public")
public class PerfilFarmacoterapeutico {

    @Id
    @Column(name = "id_perfil_farmacoterapeutico", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfilFarmacoterapeutico;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "via")
    private String via;

    @Column(name = "cantidad_formulada")
    private String cantidadFormulada;

    @Column(name = "fecha_formulacion")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaFormulacion;

    @Column(name = "cantidad_despachada")
    private Double cantidadDespachada;

    @Column(name = "fecha_despacho")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaDespacho;

    @Column(name = "cantidad_aplicada")
    private Double cantidadAplicada;

    @Column(name = "fecha_aplicacion")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaAplicacion;

    @Column(name = "cantidad_devuelta")
    private Double cantidadDevuelta;

    @Column(name = "fecha_devolucion")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaDevolucion;

    @Column(name = "posologia")
    private String posologia;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "estado_perfil_farmacoterapeutico")
    private Estado estadoPerfilFarmacoterapeutico;
}
