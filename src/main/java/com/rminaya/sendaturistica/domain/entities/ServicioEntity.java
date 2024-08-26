package com.rminaya.sendaturistica.domain.entities;

import com.rminaya.sendaturistica.domain.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

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
    @JoinColumn(name = "id_paquete_turistico", referencedColumnName = "id_paquete_turistico")
    private PaqueteTuristicoEntity paqueteTuristico;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof ServicioEntity servicio)) return false;
        return Objects.equals(idServicio, servicio.idServicio) && Objects.equals(nombre, servicio.nombre) && Objects.equals(descripcionBreve, servicio.descripcionBreve) && Objects.equals(fechaServicio, servicio.fechaServicio) && Objects.equals(costoServicio, servicio.costoServicio) && Objects.equals(tipoServicio, servicio.tipoServicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idServicio, nombre, descripcionBreve, fechaServicio, costoServicio, tipoServicio);
    }

    @Override
    public String toString() {
        return "ServicioEntity{" +
                "idServicio=" + idServicio +
                ", nombre='" + nombre + '\'' +
                ", descripcionBreve='" + descripcionBreve + '\'' +
                ", fechaServicio=" + fechaServicio +
                ", costoServicio=" + costoServicio +
                ", tipoServicio=" + tipoServicio.getNombre() +
                '}';
    }
}
