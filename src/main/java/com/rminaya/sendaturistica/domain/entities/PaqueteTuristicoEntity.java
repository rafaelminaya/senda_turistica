package com.rminaya.sendaturistica.domain.entities;

import com.rminaya.sendaturistica.domain.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(mappedBy = "paqueteTuristico", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ServicioEntity> servicios;

    // MÉTODOS
    @PrePersist
    public void preCreate() {
        System.out.println("PRE-PERSIST");

        if (Objects.isNull(this.servicios)) {
            this.servicios = new ArrayList<>();
        }
        this.servicios.forEach(servicioEntity -> servicioEntity.setPaqueteTuristico(this));

        this.setCostoPaquete(this.calcularCostoPaquete(this.servicios));
    }

    public void actualizarServicios(List<ServicioEntity> serviciosToUpdate) {
        this.agregarNuevosServicios(serviciosToUpdate);
        this.eliminarServiciosActualesSinCoincidencias(serviciosToUpdate);
    }

    private void agregarNuevosServicios(List<ServicioEntity> serviciosToUpdate) {
        serviciosToUpdate.forEach(servicio -> {
            if (!this.servicios.contains(servicio.getIdServicio())) servicio.setPaqueteTuristico(this);
        });
    }

    private void eliminarServiciosActualesSinCoincidencias(List<ServicioEntity> serviciosToUpdate) {
        this.servicios.forEach(servicio -> {
            if (!serviciosToUpdate.contains(servicio.getIdServicio())) servicio.setPaqueteTuristico(null);
        });
    }

    public void removeAllServicios() {
        this.servicios.forEach(servicioEntity -> servicioEntity.setPaqueteTuristico(null));
    }

    public double calcularCostoPaquete(List<ServicioEntity> serviciosDelPaquete) {
        double totalServicioCosto = serviciosDelPaquete
                .stream()
                .mapToDouble(servicio -> servicio.getCostoServicio())
                .sum();
        return totalServicioCosto - (totalServicioCosto * 0.1);
    }

    @Override
    public String toString() {
        return "PaqueteTuristicoEntity{" +
                "idPaqueteTuristico=" + idPaqueteTuristico +
                ", costoPaquete=" + costoPaquete +
                ", servicios=" + servicios +
                '}';
    }
}
