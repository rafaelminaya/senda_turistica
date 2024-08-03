package com.rminaya.sendaturistica.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "empleados")
public class EmpleadoEntity extends ClienteEntity {

    @Column(nullable = false, length = 45)
    public String cargo;

    @Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
    public Double sueldo;

}
