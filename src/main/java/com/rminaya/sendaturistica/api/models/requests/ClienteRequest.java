package com.rminaya.sendaturistica.api.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 3, max = 60, message = "debe tener entre 3 y 255 caracteres.")
    private String nombre;

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 3, max = 60, message = "debe tener entre 3 y 255 caracteres.")
    private String apellido;

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 3, max = 60, message = "debe tener entre 3 y 255 caracteres.")
    private String direccion;

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 8, max = 40, message = "debe tener entre 8 y 20 caracteres.")
    private String dni;

    @NotNull(message = "no puede esta vacio.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 3, max = 45, message = "debe tener entre 8 y 20 caracteres.")
    private String nacionalidad;

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 3, max = 45, message = "debe tener entre 3 y 255 caracteres.")
    private String celular;

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 3, max = 60, message = "debe tener entre 3 y 50 caracteres.")
    private String email;
}
