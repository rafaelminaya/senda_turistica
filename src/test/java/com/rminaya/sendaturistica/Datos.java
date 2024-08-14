package com.rminaya.sendaturistica;

import com.rminaya.sendaturistica.api.models.requests.ClienteRequest;
import com.rminaya.sendaturistica.api.models.requests.ServicioRequest;
import com.rminaya.sendaturistica.api.models.responses.ClienteResponse;
import com.rminaya.sendaturistica.api.models.responses.ServicioResponse;
import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import com.rminaya.sendaturistica.domain.entities.ServicioEntity;
import com.rminaya.sendaturistica.domain.entities.TipoServicioEntity;
import com.rminaya.sendaturistica.util.enums.Tables;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Datos {
    public static final ClienteEntity CLIENTE_ENTITY;
    public static final ClienteResponse CLIENTE_RESPONSE;
    public static final ClienteRequest CLIENTE_REQUEST;
    public static final IdNotFoundException CLIENTE_INVALID = new IdNotFoundException(Tables.clientes.name(), 1000);
    public static final ServicioEntity SERVICIO_ENTITY;
    public static final ServicioResponse SERVICIO_RESPONSE;
    public static final ServicioRequest SERVICIO_REQUEST;
    public static final TipoServicioEntity TIPO_SERVICIO_ENTITY;

    static {
        CLIENTE_ENTITY = ClienteEntity.builder()
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

        CLIENTE_RESPONSE = ClienteResponse.builder()
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
        CLIENTE_REQUEST = ClienteRequest.builder()
                .nombre("Carlos")
                .apellido("Rojas")
                .direccion("av peru 3823")
                .dni("47873921")
                .fechaNacimiento(LocalDate.now())
                .nacionalidad("Peruana")
                .celular("987283318")
                .email("carlos.rojas@gmail.com")
                .build();

        SERVICIO_ENTITY = ServicioEntity.builder()
                .idServicio(1000)
                .nombre("Alquiler auto BMW 193")
                .descripcionBreve("Alquiler de auto para el dia entero")
                .fechaServicio(LocalDateTime.now())
                .costoServicio(180.5)
                .tipoServicio(new TipoServicioEntity(1, "Hotel por noche/s"))
                .paqueteTuristico(null)
                .activo(true)
                .build();

        SERVICIO_RESPONSE = ServicioResponse.builder()
                .idServicio(1000)
                .nombre("Alquiler auto BMW 193")
                .descripcionBreve("Alquiler de auto para el dia entero")
                .fechaServicio(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .costoServicio(180.5)
                .tipoServicio("Hotel por noche/s")
                .build();

        SERVICIO_REQUEST = ServicioRequest.builder()
                .nombre("Alquiler auto BMW 193")
                .descripcionBreve("Alquiler de auto para el dia entero")
                .fechaServicio(LocalDateTime.now())
                .costoServicio(180.5)
                .idTipoServicio(1)
                .build();

        TIPO_SERVICIO_ENTITY = TipoServicioEntity.builder()
                .idTipoServicio(1)
                .nombre("Hotel por noche/s")
                .build();
    }
}
