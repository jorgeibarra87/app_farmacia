package com.medicamentos.farmacia.app_farmacia.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "public")
public class Usuario {
    @Id
    @Column(name = "id_usuario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name = "identificacion")
    private String identificacion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ingreso")
    private String ingreso;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;

    @Column(name = "estado_usuario")
    private Estado estadoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "id_perfil_farmacoterapeutico", referencedColumnName = "id_perfil_farmacoterapeutico")
    private PerfilFarmacoterapeutico perfilFarmacoterapeutico;
}
