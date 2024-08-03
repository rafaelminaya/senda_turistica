package com.rminaya.sendaturistica.api.models.requests;

import java.time.LocalDate;

public record ClientResponse(Integer idCliente, String nombre, String apellido, String direccion, String dni,
                             LocalDate fechaNacimiento, String celular, String email) {
}
