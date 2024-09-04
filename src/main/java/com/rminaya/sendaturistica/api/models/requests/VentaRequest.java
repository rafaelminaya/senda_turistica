package com.rminaya.sendaturistica.api.models.requests;

import com.rminaya.sendaturistica.util.annotations.ServicioOpaqueteValid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ServicioOpaqueteValid
public class VentaRequest {

    @Pattern(regexp = "[SP]", message = "debe ser 'S' de servicio o 'P' de paquete turistico.")
    private String tipo;
    @Positive(message = "debe ser mayor a cero.")
    @NotNull(message = "no puede estar vacio.")
    private Integer idCliente;
    @Positive(message = "debe ser mayor a cero.")
    @NotNull(message = "no puede estar vacio.")
    private Integer idEmpleado;
    @Positive(message = "debe ser mayor a cero.")
    @NotNull(message = "no puede estar vacio.")
    private Integer idMedioPago;
    @Positive(message = "debe ser mayor a cero.")
    //@NotNull(message = "no puede estar vacio.")
    private Integer idServicio;
    @Positive(message = "debe ser mayor a cero.")
    //@NotNull(message = "no puede estar vacio.")
    private Integer idPaqueteTuristico;
}
