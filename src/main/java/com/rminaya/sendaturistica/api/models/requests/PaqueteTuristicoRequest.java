package com.rminaya.sendaturistica.api.models.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor // Necesario para la deserializacion del JSON
@Setter // Necesario para la deserializacion del JSON
@Getter // Necesario para la obtención de cada atributo desde la clase @Service
public class PaqueteTuristicoRequest {
    List<PaqueteTuristicoServicioRequest> servicios;
}
