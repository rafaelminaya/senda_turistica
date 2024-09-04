package com.rminaya.sendaturistica.api.models.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaResponse {

    private Integer idVenta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime fechaVenta;
    private String tipo;
    // RELACIONES
    private String cliente;
    private String empleado;
    private String medioPago;
    private ServicioResponse servicio;
    private PaqueteTuristicoResponse paqueteTuristico;
}
