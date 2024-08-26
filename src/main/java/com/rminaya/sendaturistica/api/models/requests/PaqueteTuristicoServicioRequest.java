package com.rminaya.sendaturistica.api.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class PaqueteTuristicoServicioRequest {
    @Positive(message = "debe ser un numero mayor a 0.")
    @NotNull(message = "no puede estar vacio.")
    private Integer id;
}
