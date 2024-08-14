package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.TipoServicioEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TipoServicioRepositoryTest {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    private final static Integer ID_VALID = 1;
    private final static Integer ID_INVALID = 1000;

    @Test
    @DisplayName("findById() - works")
    void testFindById() {
        Optional<TipoServicioEntity> tipoServicio = tipoServicioRepository.findFirstByActivoTrueAndIdTipoServicio(ID_VALID);
        assertTrue(tipoServicio.isPresent());
    }

    @Test
    @DisplayName("findByActivoTrueAndIdTipoServicio() - works")
    void testFindFirstByActivoTrueAndIdTipoServicio() {
        Optional<TipoServicioEntity> tipoServicio = tipoServicioRepository.findFirstByActivoTrueAndIdTipoServicio(ID_VALID);
        assertTrue(tipoServicio.isPresent());
        assertEquals(true, tipoServicio.get().getActivo());
        assertEquals(ID_VALID, tipoServicio.get().getIdTipoServicio());
    }

    @Test
    @DisplayName("findByActivoTrueAndIdTipoServicio() - doesn't work")
    void testFindFirstByActivoTrueAndIdTipoServicioInvalid() {
        Optional<TipoServicioEntity> tipoServicio = tipoServicioRepository.findFirstByActivoTrueAndIdTipoServicio(ID_INVALID);
        assertTrue(tipoServicio.isEmpty());
    }

}