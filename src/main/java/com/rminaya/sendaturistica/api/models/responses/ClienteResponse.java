package com.rminaya.sendaturistica.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse {
    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private String fechaNacimiento;
    private String celular;
    private String email;
}
