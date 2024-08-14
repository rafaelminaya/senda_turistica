package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.domain.entities.ServicioEntity;
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
class ServicioRepositoryTest {

    @Autowired
    private ServicioRepository servicioRepository;

    private static final Integer ID_VALID = 1;
    private static final Integer ID_NO_ACTIVO = 20;
    private static final Integer ID_INVALID = 1000;

    @Test
    @DisplayName("findById() - works")
    void testFindyById() {
        Optional<ServicioEntity> servicio = this.servicioRepository.findById(ID_VALID);
        assertTrue(servicio.isPresent());
        assertEquals(ID_VALID, servicio.get().getIdServicio());
    }

    @Test
    @DisplayName("findByActivoTrueAndIdServicio() - works")
    void testFindFirstByActivoTrueAndIdServicio() {
        Optional<ServicioEntity> servicio = this.servicioRepository.findFirstByActivoTrueAndIdServicio(ID_VALID);
        assertTrue(servicio.isPresent());
        assertEquals(true, servicio.get().getActivo());
        assertEquals(ID_VALID, servicio.get().getIdServicio());
    }

    @Test
    @DisplayName("findByActivoTrueAndIdServicio() - get inactive")
    void testFindFirstByActivoTrueAndIdServicioNoActive() {
        Optional<ServicioEntity> servicio = this.servicioRepository.findById(ID_NO_ACTIVO);
        assertTrue(servicio.isPresent());
        assertEquals(false, servicio.get().getActivo());
    }

    @Test
    @DisplayName("findByActivoTrueAndIdServicio() - doesn't work")
    void testFindFirstByActivoTrueAndIdServicioException() {
        Optional<ServicioEntity> servicio = this.servicioRepository.findById(ID_INVALID);
        assertTrue(servicio.isEmpty());
    }

    @Test
    @DisplayName("save() - works")
    void testSave() {
        ServicioEntity servicioFromDB = this.servicioRepository.save(Datos.SERVICIO_ENTITY);
        Optional<ServicioEntity> servicio = this.servicioRepository.findById(servicioFromDB.getIdServicio());

        assertTrue(servicio.isPresent());
        assertEquals("Alquiler auto BMW 193", servicio.get().getNombre());
        assertEquals("Alquiler de auto para el dia entero", servicio.get().getDescripcionBreve());
    }
}