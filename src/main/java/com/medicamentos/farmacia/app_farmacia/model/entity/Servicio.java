package com.medicamentos.farmacia.app_farmacia.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "servicio", schema = "public")
public class Servicio {

    @Id
    @Column(name = "id_servicio", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "estado-servicio")
    private Estado estadoServicio;
}
