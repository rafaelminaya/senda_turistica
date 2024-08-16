package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.responses.PaqueteTuristicoResponse;
import com.rminaya.sendaturistica.domain.entities.PaqueteTuristicoEntity;
import com.rminaya.sendaturistica.domain.repositories.PaqueteTuristicoRepository;
import com.rminaya.sendaturistica.domain.repositories.ServicioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
    void testCreate() {
        //TODO
    }

    @Test
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
    void testUpdate() {
        //TODO
    }

    @Test
    void testDelete() {
        //TODO
    }
}
