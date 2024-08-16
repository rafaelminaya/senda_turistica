package com.rminaya.sendaturistica.api.models.responses;

import lombok.*;

import java.util.List;

@Getter // Permite serializar el objeto Java a JSON--Obtener cada atributo para unit test(Service)
@Setter // Necesario para el Mapping con MapStruct
@ToString // Necesario para imprimir el objeto en caso de fallo en los unit test
@EqualsAndHashCode // Necesario para assertEquals() en los unit test
@Builder // Implementa el patron builder--Clase Datos para el Testing--Para el mapping de MapStruct
@AllArgsConstructor // Necesario para implementar el patron builder
public class PaqueteTuristicoResponse {
    private Integer idPaqueteTuristico;
    private Double costoPaquete;
    private List<ServicioResponse> servicios;
}
