package com.rminaya.sendaturistica;

import com.rminaya.sendaturistica.api.models.requests.ClienteResponse;
import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import com.rminaya.sendaturistica.util.enums.Tables;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datos {

    public static final ClienteEntity CLIENTE =
            ClienteEntity.builder()
                    .idCliente(1)
                    .nombre("Carlos")
                    .apellido("Rojas")
                    .direccion("av peru 3823")
                    .dni("47873921")
                    .fechaNacimiento(LocalDate.now())
                    .celular("987283318")
                    .email("carlos.rojas@gmail.com")
                    .build();

    public static final ClienteResponse CLIENTE_RESPONSE =
            ClienteResponse.builder()
                    .idCliente(1)
                    .nombre("Carlos")
                    .apellido("Rojas")
                    .direccion("av peru 3823")
                    .dni("47873921")
                    .fechaNacimiento(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .celular("987283318")
                    .email("carlos.rojas@gmail.com")
                    .build();

    public static final IdNotFoundException CLIENTE_INVALID = new IdNotFoundException(Tables.clientes.name(), 1000);

}
