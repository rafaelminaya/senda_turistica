package com.rminaya.sendaturistica;

import com.rminaya.sendaturistica.api.models.requests.*;
import com.rminaya.sendaturistica.api.models.responses.ClienteResponse;
import com.rminaya.sendaturistica.api.models.responses.PaqueteTuristicoResponse;
import com.rminaya.sendaturistica.api.models.responses.ServicioResponse;
import com.rminaya.sendaturistica.api.models.responses.VentaResponse;
import com.rminaya.sendaturistica.domain.entities.*;
import com.rminaya.sendaturistica.util.enums.Tables;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Datos {
    public static final ClienteEntity CLIENTE_ENTITY;
    public static final ClienteResponse CLIENTE_RESPONSE;
    public static final ClienteRequest CLIENTE_REQUEST;
    public static final IdNotFoundException CLIENTE_INVALID = new IdNotFoundException(Tables.clientes.name(), 1000);
    public static final EmpleadoEntity EMPLEADO_ENTITY;
    public static final ServicioEntity SERVICIO_ENTITY;
    public static final ServicioEntity SERVICIO_ENTITY_2;
    public static final ServicioResponse SERVICIO_RESPONSE;
    public static final ServicioResponse SERVICIO_RESPONSE_2;
    public static final ServicioRequest SERVICIO_REQUEST;
    public static final TipoServicioEntity TIPO_SERVICIO_ENTITY;
    public static final PaqueteTuristicoEntity PAQUETE_TURISTICO_ENTITY;
    public static final PaqueteTuristicoResponse PAQUETE_TURISTICO_RESPONSE;
    public static final PaqueteTuristicoRequest PAQUETE_TURISTICO_REQUEST;
    public static final PaqueteTuristicoServicioRequest PAQUETE_TURISTICO_SERVICIO_REQUEST;
    public static final PaqueteTuristicoServicioRequest PAQUETE_TURISTICO_SERVICIO_REQUEST_2;
    public static final IdNotFoundException PAQUETE_TURISTICO_INVALID = new IdNotFoundException(Tables.paquetes_turisticos.name(), 1000);
    public static final VentaEntity VENTA_ENTITY;
    public static final VentaResponse VENTA_RESPONSE;
    public static final VentaRequest VENTA_REQUEST;

    static {
        CLIENTE_ENTITY = ClienteEntity.builder()
                .idCliente(1)
                .nombre("Carlos")
                .apellido("Heredia")
                .direccion("Av. Rosales 1293")
                .dni("38467245")
                .fechaNacimiento(LocalDate.of(1985, 7, 12))
                .nacionalidad("Peruana")
                .celular("934854732")
                .email("carlos.heredia@gmail.com")
                .activo(true)
                .build();

        CLIENTE_RESPONSE = ClienteResponse.builder()
                .idCliente(1)
                .nombre("Carlos")
                .apellido("Heredia")
                .direccion("Av. Rosales 1293")
                .dni("38467245")
                .fechaNacimiento(LocalDate.of(1985, 7, 12).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .nacionalidad("Peruana")
                .celular("934854732")
                .email("carlos.heredia@gmail.com")
                .build();

        CLIENTE_REQUEST = ClienteRequest.builder()
                .nombre("Carlos")
                .apellido("Heredia")
                .direccion("Av. Rosales 1293")
                .dni("38467245")
                .fechaNacimiento(LocalDate.of(1985, 7, 12))
                .nacionalidad("Peruana")
                .celular("934854732")
                .email("carlos.heredia@gmail.com")
                .build();

        EMPLEADO_ENTITY = EmpleadoEntity.builder()
                .idCliente(1)
                .nombre("Carlos")
                .apellido("Heredia")
                .direccion("Av. Rosales 1293")
                .dni("38467245")
                .fechaNacimiento(LocalDate.of(1985, 7, 12))
                .nacionalidad("Peruana")
                .celular("934854732")
                .email("carlos.heredia@gmail.com")
                .activo(true)
                .cargo("Administrador")
                .sueldo(6000.0)
                .build();

        SERVICIO_ENTITY = ServicioEntity.builder()
                .idServicio(1)
                .nombre("Alquiler 1 dormitorio")
                .descripcionBreve("Alquiler de 1 dormitorio para persona sola por 3 noches")
                .fechaServicio(LocalDateTime.of(LocalDate.of(2024, 5, 5), LocalTime.of(13, 20, 0, 0)))
                .costoServicio(500.0)
                .tipoServicio(new TipoServicioEntity(1, "Hotel por noche/s"))
                .paqueteTuristico(null)
                .activo(true)
                .build();

        SERVICIO_ENTITY_2 = ServicioEntity.builder()
                .idServicio(2)
                .nombre("Alquiler auto BMW 193")
                .descripcionBreve("Alquiler de auto para el dia entero")
                .fechaServicio(LocalDateTime.of(LocalDate.of(2024, 5, 5), LocalTime.of(13, 20, 0, 0)))
                .costoServicio(100.0)
                .tipoServicio(new TipoServicioEntity(2, "Alquiler de auto"))
                .paqueteTuristico(null)
                .activo(true)
                .build();

        SERVICIO_RESPONSE = ServicioResponse.builder()
                .idServicio(1)
                .nombre("Alquiler 1 dormitorio")
                .descripcionBreve("Alquiler de 1 dormitorio para persona sola por 3 noches")
                .fechaServicio(LocalDateTime.of(LocalDate.of(2024, 5, 5), LocalTime.of(13, 20, 0, 0)).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .costoServicio(500.0)
                .tipoServicio("Hotel por noche/s")
                .build();

        SERVICIO_RESPONSE_2 = ServicioResponse.builder()
                .idServicio(2)
                .nombre("Alquiler auto BMW 193")
                .descripcionBreve("Alquiler de auto para el dia entero")
                .fechaServicio(LocalDateTime.of(LocalDate.of(2024, 5, 5), LocalTime.of(13, 20, 0, 0)).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .costoServicio(100.0)
                .tipoServicio("Alquiler de auto")
                .build();

        SERVICIO_REQUEST = ServicioRequest.builder()
                .nombre("Alquiler 1 dormitorio")
                .descripcionBreve("Alquiler de 1 dormitorio para persona sola por 3 noches")
                .fechaServicio(LocalDateTime.of(LocalDate.of(2024, 5, 5), LocalTime.of(13, 20, 0, 0)))
                .costoServicio(500.0)
                .idTipoServicio(1)
                .build();

        TIPO_SERVICIO_ENTITY = TipoServicioEntity.builder()
                .idTipoServicio(1)
                .nombre("Hotel por noche/s")
                .build();

        PAQUETE_TURISTICO_ENTITY = PaqueteTuristicoEntity.builder()
                .idPaqueteTuristico(1)
                .costoPaquete(540.0)
                .servicios(List.of(SERVICIO_ENTITY, SERVICIO_ENTITY_2))
                .activo(true)
                .build();

        PAQUETE_TURISTICO_RESPONSE = PaqueteTuristicoResponse.builder()
                .idPaqueteTuristico(1)
                .costoPaquete(540.0)
                .servicios(List.of(SERVICIO_RESPONSE, SERVICIO_RESPONSE_2))
                .build();

        PAQUETE_TURISTICO_SERVICIO_REQUEST = PaqueteTuristicoServicioRequest.builder()
                .id(1)
                .build();

        PAQUETE_TURISTICO_SERVICIO_REQUEST_2 = PaqueteTuristicoServicioRequest.builder()
                .id(2)
                .build();

        PAQUETE_TURISTICO_REQUEST = PaqueteTuristicoRequest.builder()
                .servicios(List.of(PAQUETE_TURISTICO_SERVICIO_REQUEST, PAQUETE_TURISTICO_SERVICIO_REQUEST_2))
                .build();

        VENTA_ENTITY = VentaEntity.builder()
                .idVenta(1)
                .fechaVenta(LocalDateTime.of(LocalDate.of(2024, 7, 3), LocalTime.of(13, 20, 0, 0)))
                .tipo("S")
                .cliente(CLIENTE_ENTITY)
                .empleado(EMPLEADO_ENTITY)
                .medioPago(new MedioPagoEntity(1, "Efectivo", 0.0))
                .servicio(SERVICIO_ENTITY)
                .paqueteTuristico(null)
                .build();

        VENTA_RESPONSE = VentaResponse.builder()
                .idVenta(1)
                .fechaVenta(LocalDateTime.of(LocalDate.of(2024, 7, 3), LocalTime.of(13, 20, 0, 0)))
                .tipo("S")
                .cliente(CLIENTE_RESPONSE.getNombre())
                .empleado(EMPLEADO_ENTITY.getNombre())
                .medioPago("Efectivo")
                .servicio(SERVICIO_RESPONSE)
                .paqueteTuristico(null)
                .build();

        VENTA_REQUEST = VentaRequest.builder()
                .tipo("S")
                .idCliente(1)
                .idEmpleado(1)
                .idMedioPago(1)
                .idServicio(1)
                .idPaqueteTuristico(null)
                .build();
    }
}
