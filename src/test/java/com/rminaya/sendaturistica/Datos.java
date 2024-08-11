package com.rminaya.sendaturistica;

import com.rminaya.sendaturistica.api.models.requests.ClienteRequest;
import com.rminaya.sendaturistica.api.models.responses.ClienteResponse;
import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import com.rminaya.sendaturistica.util.enums.Tables;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datos {

    public static final ClienteEntity CLIENTE =
            ClienteEntity.builder()
                    .idCliente(1000)
                    .nombre("Carlos")
                    .apellido("Rojas")
                    .direccion("av peru 3823")
                    .dni("47873921")
                    .fechaNacimiento(LocalDate.now())
                    .nacionalidad("Peruana")
                    .celular("987283318")
                    .email("carlos.rojas@gmail.com")
                    .activo(true)
                    .build();

    public static final ClienteResponse CLIENTE_RESPONSE =
            ClienteResponse.builder()
                    .idCliente(1000)
                    .nombre("Carlos")
                    .apellido("Rojas")
                    .direccion("av peru 3823")
                    .dni("47873921")
                    .fechaNacimiento(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .nacionalidad("Peruana")
                    .celular("987283318")
                    .email("carlos.rojas@gmail.com")
                    .build();

    public static final ClienteRequest CLIENTE_REQUEST =
            ClienteRequest.builder()
                    .nombre("Carlos")
                    .apellido("Rojas")
                    .direccion("av peru 3823")
                    .dni("47873921")
                    .fechaNacimiento(LocalDate.now())
                    .nacionalidad("Peruana")
                    .celular("987283318")
                    .email("carlos.rojas@gmail.com")
                    .build();

    public static final IdNotFoundException CLIENTE_INVALID = new IdNotFoundException(Tables.clientes.name(), 1000);

}
