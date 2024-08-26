package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.responses.PaqueteTuristicoResponse;
import com.rminaya.sendaturistica.domain.entities.PaqueteTuristicoEntity;
import com.rminaya.sendaturistica.domain.repositories.PaqueteTuristicoRepository;
import com.rminaya.sendaturistica.domain.repositories.ServicioRepository;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PaqueteTuristicoServiceTest {

    @Autowired
    private PaqueteTuristicoService paqueteTuristicoService;

    @MockBean
    private PaqueteTuristicoRepository paqueteTuristicoRepository;

    @MockBean
    private ServicioRepository servicioRepository;

    private static final Integer VALID_ID = 1;
    private static final Integer INVALID_ID = 1000;

    @Test
    @DisplayName("create() - works")
    void testCreate() {
        when(this.servicioRepository.findAllById(anyList())).thenReturn(List.of(Datos.SERVICIO_ENTITY, Datos.SERVICIO_ENTITY_2));
        when(this.paqueteTuristicoRepository.save(any(PaqueteTuristicoEntity.class))).thenReturn(Datos.PAQUETE_TURISTICO_ENTITY);

        PaqueteTuristicoResponse paqueteTuristico = this.paqueteTuristicoService.create(Datos.PAQUETE_TURISTICO_REQUEST);

        assertEquals(Datos.PAQUETE_TURISTICO_RESPONSE, paqueteTuristico);
        verify(this.servicioRepository, times(1)).findAllById(anyList());
        verify(this.paqueteTuristicoRepository, times(1)).save(any(PaqueteTuristicoEntity.class));
    }

    @Test
    @DisplayName("read() - works")
    void testRead() {

        when(this.paqueteTuristicoRepository.findFirstByActivoTrueAndIdPaqueteTuristico(VALID_ID))
                .thenReturn(Optional.of(Datos.PAQUETE_TURISTICO_ENTITY));

        PaqueteTuristicoResponse paqueteTuristico = this.paqueteTuristicoService.read(VALID_ID);
        PaqueteTuristicoResponse paqueteTuristico2 = this.paqueteTuristicoService.read(VALID_ID);

        assertEquals(VALID_ID, paqueteTuristico.getIdPaqueteTuristico());
        assertEquals(540.0, paqueteTuristico.getCostoPaquete());
        assertEquals(Datos.PAQUETE_TURISTICO_RESPONSE, paqueteTuristico);
        assertEquals(paqueteTuristico2, paqueteTuristico);
        assertEquals(paqueteTuristico2.hashCode(), paqueteTuristico.hashCode());
    }

    @Test
    @DisplayName("read() - exception")
    void testReadException() {
        when(this.paqueteTuristicoRepository.findFirstByActivoTrueAndIdPaqueteTuristico(anyInt())).thenThrow(Datos.PAQUETE_TURISTICO_INVALID);
        assertThrows(IdNotFoundException.class, () -> this.paqueteTuristicoService.read(INVALID_ID));
        verify(this.paqueteTuristicoRepository, times(1)).findFirstByActivoTrueAndIdPaqueteTuristico(INVALID_ID);
    }

    @Test
    @DisplayName("update() - works")
    void testUpdate() {
        when(this.paqueteTuristicoRepository.findById(VALID_ID)).thenReturn(Optional.of(Datos.PAQUETE_TURISTICO_ENTITY));
        when(this.servicioRepository.findAllById(anyList())).thenReturn(List.of(Datos.SERVICIO_ENTITY, Datos.SERVICIO_ENTITY_2));
        when(this.paqueteTuristicoRepository.save(any(PaqueteTuristicoEntity.class))).thenReturn(Datos.PAQUETE_TURISTICO_ENTITY);

        PaqueteTuristicoResponse paqueteTuristico = this.paqueteTuristicoService.update(Datos.PAQUETE_TURISTICO_REQUEST, VALID_ID);
        assertEquals(Datos.PAQUETE_TURISTICO_RESPONSE, paqueteTuristico);

        verify(this.paqueteTuristicoRepository, times(1)).findById(anyInt());
        verify(this.servicioRepository, times(1)).findAllById(anyList());
        verify(this.paqueteTuristicoRepository, times(1)).save(any(PaqueteTuristicoEntity.class));
    }

    @Test
    @DisplayName("delete() - works")
    void testDelete() {
        when(this.paqueteTuristicoRepository.findById(VALID_ID)).thenReturn(Optional.of(Datos.PAQUETE_TURISTICO_ENTITY));

        this.paqueteTuristicoService.delete(VALID_ID);
        verify(this.paqueteTuristicoRepository, times(1)).save(any(PaqueteTuristicoEntity.class));
        verify(this.paqueteTuristicoRepository, times(1)).findById(anyInt());
        verify(this.paqueteTuristicoRepository, times(1)).save(any(PaqueteTuristicoEntity.class));
    }
}
