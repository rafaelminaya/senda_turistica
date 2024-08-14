package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.responses.ServicioResponse;
import com.rminaya.sendaturistica.domain.entities.ServicioEntity;
import com.rminaya.sendaturistica.domain.repositories.ServicioRepository;
import com.rminaya.sendaturistica.domain.repositories.TipoServicioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ServicioServiceTest {

    @Autowired
    private ServicioService servicioService;

    @MockBean
    private ServicioRepository servicioRepository;

    @MockBean
    private TipoServicioRepository tipoServicioRepository;

    private final static Integer ID_VALID = 1;

    @Test
    @DisplayName("create() - Works")
    void create() {
        when(this.tipoServicioRepository.findFirstByActivoTrueAndIdTipoServicio(anyInt())).thenReturn(Optional.of(Datos.TIPO_SERVICIO_ENTITY));
        when(this.servicioRepository.save(any(ServicioEntity.class))).thenReturn(Datos.SERVICIO_ENTITY);
        ServicioResponse servicio = this.servicioService.create(Datos.SERVICIO_REQUEST);
        assertEquals(Datos.SERVICIO_RESPONSE, servicio);
        assertEquals(1000, servicio.getIdServicio());

        verify(this.tipoServicioRepository, times(1)).findFirstByActivoTrueAndIdTipoServicio(anyInt());
        verify(this.servicioRepository, times(1)).save(any(ServicioEntity.class));

    }

    @Test
    @DisplayName("read() - Works")
    void read() {
        when(this.servicioRepository.findFirstByActivoTrueAndIdServicio(ID_VALID)).thenReturn(Optional.of(Datos.SERVICIO_ENTITY));

        ServicioResponse servicio1 = this.servicioService.read(ID_VALID);
        ServicioResponse servicio2 = this.servicioService.read(ID_VALID);
        assertEquals(servicio1, servicio2);
        assertEquals("Alquiler auto BMW 193", servicio1.getNombre());
        assertEquals("Alquiler de auto para el dia entero", servicio2.getDescripcionBreve());

        verify(this.servicioRepository, times(2)).findFirstByActivoTrueAndIdServicio(ID_VALID);

    }

    @Test
    @DisplayName("update() - Works")
    void update() {
        when(this.tipoServicioRepository.findFirstByActivoTrueAndIdTipoServicio(anyInt())).thenReturn(Optional.of(Datos.TIPO_SERVICIO_ENTITY));
        when(this.servicioRepository.findFirstByActivoTrueAndIdServicio(anyInt())).thenReturn(Optional.of(Datos.SERVICIO_ENTITY));
        when(this.servicioRepository.save(any(ServicioEntity.class))).thenReturn(Datos.SERVICIO_ENTITY);

        ServicioResponse servicio = this.servicioService.update(Datos.SERVICIO_REQUEST, ID_VALID);
        assertEquals(Datos.SERVICIO_RESPONSE, servicio);
        assertEquals(1000, servicio.getIdServicio());

        verify(this.tipoServicioRepository, times(1)).findFirstByActivoTrueAndIdTipoServicio(anyInt());
        verify(this.servicioRepository, times(1)).findFirstByActivoTrueAndIdServicio(anyInt());
        verify(this.servicioRepository, times(1)).save(any(ServicioEntity.class));
    }

    @Test
    @DisplayName("delete() - Works")
    void delete() {
        when(this.servicioRepository.findFirstByActivoTrueAndIdServicio(anyInt())).thenReturn(Optional.of(Datos.SERVICIO_ENTITY));
        this.servicioService.delete(ID_VALID);

        verify(this.servicioRepository, times(1)).save(any(ServicioEntity.class));
        verify(this.servicioRepository, times(1)).findFirstByActivoTrueAndIdServicio(anyInt());
    }
}