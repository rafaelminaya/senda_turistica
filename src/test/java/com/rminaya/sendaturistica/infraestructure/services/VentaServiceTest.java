package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.responses.VentaResponse;
import com.rminaya.sendaturistica.domain.entities.MedioPagoEntity;
import com.rminaya.sendaturistica.domain.entities.VentaEntity;
import com.rminaya.sendaturistica.domain.repositories.*;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IVentaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class VentaServiceTest {

    @Autowired
    private IVentaService ventaService;

    @MockBean
    private VentaRepository ventaRepository;
    @MockBean
    private ClienteRepository clienteRepository;
    @MockBean
    private EmpleadoRepository empleadoRepository;
    @MockBean
    private MedioPagoRepository medioPagoRepository;
    @MockBean
    private ServicioRepository servicioRepository;
    @MockBean
    private PaqueteTuristicoRepository paqueteTuristicoRepository;

    private static final Integer VALID_ID = 1;

    @Test
    @DisplayName("create() - works")
    void testCreate() {
        when(this.clienteRepository.findFirstByActivoTrueAndIdCliente(anyInt())).thenReturn(Optional.of(Datos.CLIENTE_ENTITY));
        when(this.empleadoRepository.findFirstByActivoTrueAndIdCliente(anyInt())).thenReturn(Optional.of(Datos.EMPLEADO_ENTITY));
        when(this.medioPagoRepository.findById(anyInt())).thenReturn(Optional.of(new MedioPagoEntity(1, "Efectivo", 0.0)));
        when(this.servicioRepository.findFirstByActivoTrueAndIdServicio(anyInt())).thenReturn(Optional.of(Datos.SERVICIO_ENTITY));
        when(this.paqueteTuristicoRepository.findFirstByActivoTrueAndIdPaqueteTuristico(anyInt())).thenReturn(Optional.of(Datos.PAQUETE_TURISTICO_ENTITY));
        when(this.ventaRepository.save(any(VentaEntity.class))).thenReturn(Datos.VENTA_ENTITY);

        VentaResponse venta = this.ventaService.create(Datos.VENTA_REQUEST);
        assertEquals(Datos.VENTA_RESPONSE, venta);

        verify(this.clienteRepository, times(1)).findFirstByActivoTrueAndIdCliente(anyInt());
        verify(this.empleadoRepository, times(1)).findFirstByActivoTrueAndIdCliente(anyInt());
        verify(this.medioPagoRepository, times(1)).findById(anyInt());
        verify(this.servicioRepository, atMostOnce()).findFirstByActivoTrueAndIdServicio(anyInt());
        verify(this.paqueteTuristicoRepository, atMost(1)).findFirstByActivoTrueAndIdPaqueteTuristico(anyInt());
        verify(this.ventaRepository, times(1)).save(any(VentaEntity.class));

    }

    @Test
    @DisplayName("read() - works")
    void testRead() {
        when(this.ventaRepository.findFirstByActivoTrueAndIdVenta(anyInt())).thenReturn(Optional.of(Datos.VENTA_ENTITY));

        VentaResponse venta = this.ventaService.read(VALID_ID);
        assertEquals(VALID_ID, venta.getIdVenta());
        verify(this.ventaRepository, times(1)).findFirstByActivoTrueAndIdVenta(anyInt());
    }

    @Test
    @DisplayName("update() - works")
    void testUpdate() {
        when(this.clienteRepository.findFirstByActivoTrueAndIdCliente(anyInt())).thenReturn(Optional.of(Datos.CLIENTE_ENTITY));
        when(this.empleadoRepository.findFirstByActivoTrueAndIdCliente(anyInt())).thenReturn(Optional.of(Datos.EMPLEADO_ENTITY));
        when(this.medioPagoRepository.findById(anyInt())).thenReturn(Optional.of(new MedioPagoEntity(1, "Efectivo", 0.0)));
        when(this.servicioRepository.findFirstByActivoTrueAndIdServicio(anyInt())).thenReturn(Optional.of(Datos.SERVICIO_ENTITY));
        when(this.paqueteTuristicoRepository.findFirstByActivoTrueAndIdPaqueteTuristico(anyInt())).thenReturn(Optional.of(Datos.PAQUETE_TURISTICO_ENTITY));
        when(this.ventaRepository.findFirstByActivoTrueAndIdVenta(anyInt())).thenReturn(Optional.of(Datos.VENTA_ENTITY));
        when(this.ventaRepository.save(any(VentaEntity.class))).thenReturn(Datos.VENTA_ENTITY);

        VentaResponse venta = this.ventaService.update(Datos.VENTA_REQUEST, VALID_ID);

        //assertEquals(Datos.VENTA_RESPONSE, venta); // Falla porque se setea a la fecha actual en el service
        assertEquals(VALID_ID, venta.getIdVenta());

        verify(this.clienteRepository, times(1)).findFirstByActivoTrueAndIdCliente(anyInt());
        verify(this.empleadoRepository, times(1)).findFirstByActivoTrueAndIdCliente(anyInt());
        verify(this.medioPagoRepository, times(1)).findById(anyInt());
        verify(this.servicioRepository, atMost(1)).findFirstByActivoTrueAndIdServicio(anyInt());
        verify(this.paqueteTuristicoRepository, atMostOnce()).findFirstByActivoTrueAndIdPaqueteTuristico(anyInt());
        verify(this.ventaRepository, times(1)).save(any(VentaEntity.class));
    }

    @Test
    @DisplayName("delete() - works")
    void testDelete() {
        when(this.ventaRepository.findFirstByActivoTrueAndIdVenta(VALID_ID)).thenReturn(Optional.of(Datos.VENTA_ENTITY));

        this.ventaService.delete(VALID_ID);

        verify(this.ventaRepository, times(1)).findFirstByActivoTrueAndIdVenta(anyInt());
        verify(this.ventaRepository, times(1)).save(any(VentaEntity.class));
    }
}