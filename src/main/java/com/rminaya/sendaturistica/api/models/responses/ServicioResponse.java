package com.rminaya.sendaturistica.api.models.responses;

import lombok.*;

@Getter // Permite serializar el objeto Java a JSON--Obtener cada atributo para unit test(Service)
@Setter // Necesario para el Mapping con MapStruct
@ToString // Necesario para imprimir el objeto en caso de fallo en los unit test
@EqualsAndHashCode // Necesario para assertEquals() en los unit test
@AllArgsConstructor // Necesario para implementar el patron builder
@Builder // Implementa el patron builder--Clase Datos para el Testing--Para el mapping de MapStruct
public class ServicioResponse {
    private Integer idServicio;
    private String nombre;
    private String descripcionBreve;
    private String fechaServicio;
    private Double costoServicio;
    // RELACIONES
    private String tipoServicio;
}
