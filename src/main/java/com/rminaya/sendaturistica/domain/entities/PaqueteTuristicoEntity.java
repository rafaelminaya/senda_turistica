package com.rminaya.sendaturistica.domain.entities;

import java.util.List;

import com.rminaya.sendaturistica.domain.audit.AuditableEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "paquetes_turisticos")
public class PaqueteTuristicoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete_turistico", nullable = false)
    private Integer idPaqueteTuristico;

    @Column(name = "costo_paquete", nullable = false, columnDefinition = "DECIMAL(6,2)")
    private Double costoPaquete;
    // RELACIONES
    @OneToMany(mappedBy = "paqueteTuristico", fetch = FetchType.EAGER)
    private List<ServicioEntity> servicios;
}
