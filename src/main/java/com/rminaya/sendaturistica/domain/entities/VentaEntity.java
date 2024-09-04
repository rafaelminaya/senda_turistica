package com.rminaya.sendaturistica.domain.entities;

import java.time.LocalDateTime;

import com.rminaya.sendaturistica.domain.audit.AuditableEntity;
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
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity
@Table(name = "ventas")
public class VentaEntity extends AuditableEntity {

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
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_cliente", nullable = false)
    private EmpleadoEntity empleado;

    @ManyToOne
    @JoinColumn(name = "id_medio_pago", referencedColumnName = "id_medio_pago", nullable = false)
    private MedioPagoEntity medioPago;

    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")
    public ServicioEntity servicio;

    @ManyToOne
    @JoinColumn(name = "id_paquete_turistico", referencedColumnName = "id_paquete_turistico")
    public PaqueteTuristicoEntity paqueteTuristico;

    @Override
    public String toString() {
        return "VentaEntity{" +
                "idVenta=" + idVenta +
                ", fechaVenta=" + fechaVenta +
                ", tipo='" + tipo + '\'' +
                ", cliente=" + cliente +
                ", empleado=" + empleado +
                ", medioPago=" + medioPago +
                '}';
    }
}
