package com.rminaya.sendaturistica.api.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor // Necesario para la deserializacion del JSON
@AllArgsConstructor// Necesario para que funcione el @Builder
@Setter // Necesario para la deserializacion del JSON
@Getter // Necesario para la obtención de cada atributo desde la clase @Service
@Builder // Implementa el patron builder--usado en los unit test(service)
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
