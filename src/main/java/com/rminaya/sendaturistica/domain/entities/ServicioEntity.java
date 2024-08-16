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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity
@Table(name = "servicios")
public class ServicioEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio", nullable = false)
    private Integer idServicio;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Column(name = "descripcion_breve", nullable = false, length = 60)
    private String descripcionBreve;

    @Column(name = "fecha_servicio", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime fechaServicio;

    @Column(name = "costo_servicio", nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double costoServicio;
    // RELACIONES
    @ManyToOne
    @JoinColumn(name = "id_tipo_servicio", referencedColumnName = "id_tipo_servicio", nullable = false)
    private TipoServicioEntity tipoServicio;

    @ManyToOne
    @JoinColumn(name = "id_paquete_turistico", referencedColumnName = "id_paquete_turistico", insertable = false, updatable = false)
    private PaqueteTuristicoEntity paqueteTuristico;
}
