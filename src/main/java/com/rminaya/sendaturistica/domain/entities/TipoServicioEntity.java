package com.rminaya.sendaturistica.domain.entities;

import com.rminaya.sendaturistica.domain.audit.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity
@Table(name = "tipos_servicio")

public class TipoServicioEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_servicio", nullable = false)
    private Integer idTipoServicio;

    @Column(nullable = false, length = 45)
    private String nombre;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof TipoServicioEntity that)) return false;
        return Objects.equals(idTipoServicio, that.idTipoServicio) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoServicio, nombre);
    }

    @Override
    public String toString() {
        return "TipoServicioEntity{" +
                "idTipoServicio=" + idTipoServicio +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
