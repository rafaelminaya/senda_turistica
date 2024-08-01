package com.rminaya.sendaturistica.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipos_servicio")
@NoArgsConstructor
@Getter
@Setter
public class TipoServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_servicio", nullable = false)
    private Integer idTipoServicio;

    @Column(nullable = false, length = 45)
    private String nombre;
}
