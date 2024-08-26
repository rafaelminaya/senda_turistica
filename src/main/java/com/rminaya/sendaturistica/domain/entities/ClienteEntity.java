package com.rminaya.sendaturistica.domain.entities;

import com.rminaya.sendaturistica.domain.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter

@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.JOINED)
public class ClienteEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer idCliente;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 60)
    private String apellido;

    @Column(nullable = false, length = 60)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String dni;

    @Column(name = "fecha_nacimiento", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 45)
    private String nacionalidad;

    @Column(nullable = false, length = 45)
    private String celular;

    @Column(nullable = false, length = 50)
    private String email;

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
