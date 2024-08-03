package com.rminaya.sendaturistica;

import com.rminaya.sendaturistica.domain.entities.ClienteEntity;

import java.time.LocalDate;

public class Datos {

    public static ClienteEntity crearCliente001() {
        return ClienteEntity.builder()
                .idCliente(1)
                .nombre("Carlos")
                .apellido("Rojas")
                .direccion("av peru 3823")
                .fechaNacimiento(LocalDate.now())
                .celular("987283318")
                .email("carlos.rojas@gmail.com")
                .build();
    }
}
