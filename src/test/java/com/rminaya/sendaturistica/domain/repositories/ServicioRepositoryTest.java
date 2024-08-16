package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.ServicioEntity;
import com.rminaya.sendaturistica.domain.entities.TipoServicioEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        LocalDateTime fechaServicio = LocalDateTime.of(
                LocalDate.of(2024, 5, 5),
                LocalTime.of(13, 20, 0, 0));

        ServicioEntity servicioToPersist = ServicioEntity.builder()
                .nombre("Alquiler 1 dormitorio")
                .descripcionBreve("Alquiler de 1 dormitorio para persona sola por 3 noches")
                .fechaServicio(fechaServicio)
                .costoServicio(500.0)
                .tipoServicio(new TipoServicioEntity(1, "Hotel por noche/s"))
                .paqueteTuristico(null)
                .activo(true)
                .build();

        ServicioEntity servicioPersisted = this.servicioRepository.save(servicioToPersist);
        Optional<ServicioEntity> servicio = this.servicioRepository.findById(servicioPersisted.getIdServicio());

        assertTrue(servicio.isPresent());
        assertEquals("Alquiler 1 dormitorio", servicio.get().getNombre());
        assertEquals("Alquiler de 1 dormitorio para persona sola por 3 noches", servicio.get().getDescripcionBreve());
    }

    @Test
    @DisplayName("testFindAllByPaqueteTuristico_IdPaqueteTuristico() - works")
    void testFindAllByActivoTrueAndPaqueteTuristico_IdPaqueteTuristico() {
        List<ServicioEntity> servicios = this.servicioRepository.findAllByActivoTrueAndPaqueteTuristico_IdPaqueteTuristico(1);

        assertFalse(servicios.isEmpty());
        assertEquals(6, servicios.size());
    }
}