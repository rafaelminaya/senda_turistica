package com.rminaya.sendaturistica.api.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@NoArgsConstructor // Necesario para la deserializacion del JSON
@AllArgsConstructor// Necesario para que funcione el @Builder
@Setter // Necesario para la deserializacion del JSON
@Getter // Necesario para la obtención de cada atributo desde la clase @Service
@Builder // Implementa el patron builder--usado en los unit test(service)
public class PaqueteTuristicoRequest {
    @NotNull
    @Size(min = 2, message = "debe tener minimo 2 servicios por paquete turistico.")
    private List<PaqueteTuristicoServicioRequest> servicios;
}
