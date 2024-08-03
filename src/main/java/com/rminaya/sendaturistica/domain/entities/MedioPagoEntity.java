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

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "medios_pago")
public class MedioPagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medio_pago", nullable = false)
    private Integer idMedioPago;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(name = "porcentaje_comision", nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double porcentajeComision;

}
