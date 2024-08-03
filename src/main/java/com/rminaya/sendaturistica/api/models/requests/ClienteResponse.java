package com.rminaya.sendaturistica.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private LocalDate fechaNacimiento;
    private String celular;
    private String email;
}
