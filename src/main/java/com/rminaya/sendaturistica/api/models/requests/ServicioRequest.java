package com.rminaya.sendaturistica.api.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@Getter // Para obtención de atributos en el @Service
@Setter // Para la Deserializacion del JSON, en conjunto con @NoArgsConstructor
@AllArgsConstructor// Necesario para invocar cada metodo del @Builder(En la clase Datos.java) - OPCIONAL: Para Desearilizar eel JSON(Esta opción NO requiere setters)
@NoArgsConstructor // Para la Deserializacion del JSON
@Builder // Implementa el patron builder
public class ServicioRequest {

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 3, max = 45, message = "debe tener entre 3 y 45 caracteres.")
    private String nombre;

    @NotEmpty(message = "no puede estar vacio.")
    @Size(min = 3, max = 45, message = "debe tener entre 3 y 45 caracteres.")
    private String descripcionBreve;

    @NotNull(message = "no puede esta vacio.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaServicio;

    @Positive(message = "debe ser mayor a 0")
    @NotNull(message = "no puede estar vacio.")
    private Double costoServicio;

    @Positive(message = "debe ser mayor que 0")
    @NotNull(message = "no puede estar vacio.")
    private Integer idTipoServicio;
}
