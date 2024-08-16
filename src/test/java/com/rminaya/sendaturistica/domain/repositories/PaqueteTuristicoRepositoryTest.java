package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.PaqueteTuristicoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PaqueteTuristicoRepositoryTest {

    @Autowired
    PaqueteTuristicoRepository paqueteTuristicoRepository;

    private static final Integer ID_VALID = 1;
    private static final Integer ID_NO_ACTIVO = 4;
    private final static Integer ID_INVALID = 1000;

    @Test
    @DisplayName("findFirstByActivoTrueAndIdPaqueteTuristico() - works")
    void testFindFirstByActivoTrueAndIdPaqueteTuristico() {
        Optional<PaqueteTuristicoEntity> paqueteTuristico = this.paqueteTuristicoRepository
                .findFirstByActivoTrueAndIdPaqueteTuristico(ID_VALID);

        assertTrue(paqueteTuristico.isPresent());
        assertEquals(ID_VALID, paqueteTuristico.get().getIdPaqueteTuristico());
        assertEquals(true, paqueteTuristico.get().getActivo());
        assertEquals(540.00, paqueteTuristico.get().getCostoPaquete());
    }

    @Test
    @DisplayName("findById() - get inactive")
    void testFindFirstByActivoTrueAndIdServicioNoActive() {
        Optional<PaqueteTuristicoEntity> paqueteTuristico = this.paqueteTuristicoRepository.findById(ID_NO_ACTIVO);
        assertTrue(paqueteTuristico.isPresent());
        assertEquals(false, paqueteTuristico.get().getActivo());
    }

    @Test
    @DisplayName("findFirstByActivoTrueAndIdPaqueteTuristico() - doesn't works")
    void testFindFirstByActivoTrueAndIdPaqueteTuristicoInvalid() {
        Optional<PaqueteTuristicoEntity> paqueteTuristico = this.paqueteTuristicoRepository
                .findFirstByActivoTrueAndIdPaqueteTuristico(ID_INVALID);

        assertTrue(paqueteTuristico.isEmpty());
    }
}