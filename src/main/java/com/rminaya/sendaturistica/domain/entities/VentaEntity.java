package com.rminaya.sendaturistica.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ventas")
@NoArgsConstructor
@Getter
@Setter
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta", nullable = false)
    public Integer idVenta;

    @Column(name = "fecha_venta", nullable = false, columnDefinition = "DATETIME")
    public LocalDateTime fechaVenta;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    public String tipo;
    // RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false, insertable = false, updatable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_medio_pago", referencedColumnName = "id_medio_pago", nullable = false, insertable = false, updatable = false)
    private MedioPagoEntity medioPago;

    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio", insertable = false, updatable = false)
    public ServicioEntity servicio;

    @ManyToOne
    @JoinColumn(name = "id_paquete_turistico", referencedColumnName = "id_paquete_turistico", insertable = false, updatable = false)
    public PaqueteTuristicoEntity paqueteTuristico;
}
