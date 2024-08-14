package com.rminaya.sendaturistica.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicioResponse {

    private Integer idServicio;
    private String nombre;
    private String descripcionBreve;
    private String fechaServicio;
    private Double costoServicio;
    // RELACIONES
    private String tipoServicio;

}
