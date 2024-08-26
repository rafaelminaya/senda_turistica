package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.PaqueteTuristicoEntity;
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
import java.util.Arrays;
import java.util.List;
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

    @Test
    @DisplayName("save() - works")
    void testSave() {

        ServicioEntity servicio1 = ServicioEntity.builder()
                .idServicio(1)
                .nombre("Alquiler 1 dormitorio")
                .descripcionBreve("Alquiler de 1 dormitorio para persona sola por 3 noches")
                .fechaServicio(LocalDateTime.of(LocalDate.of(2024, 5, 5), LocalTime.of(13, 20, 0, 0)))
                .costoServicio(500.0)
                .tipoServicio(new TipoServicioEntity(1, "Hotel por noche/s"))
                .paqueteTuristico(null)
                .activo(true)
                .build();

        ServicioEntity servicio2 = ServicioEntity.builder()
                .idServicio(2)
                .nombre("Alquiler auto BMW 193")
                .descripcionBreve("Alquiler de auto para el dia entero")
                .fechaServicio(LocalDateTime.of(LocalDate.of(2024, 5, 5), LocalTime.of(13, 20, 0, 0)))
                .costoServicio(100.0)
                .tipoServicio(new TipoServicioEntity(2, "Alquiler de auto"))
                .paqueteTuristico(null)
                .activo(true)
                .build();

        PaqueteTuristicoEntity paqueteTuristicoToPersist = PaqueteTuristicoEntity.builder()
                .servicios(Arrays.asList(servicio1, servicio2))
                .activo(true)
                .build();

        PaqueteTuristicoEntity paqueteTuristico = this.paqueteTuristicoRepository.save(paqueteTuristicoToPersist);
        Optional<PaqueteTuristicoEntity> paqueteTuristicoFromDB = this.paqueteTuristicoRepository.findById(paqueteTuristico.getIdPaqueteTuristico());
        assertTrue(paqueteTuristicoFromDB.isPresent());
        assertEquals(540, paqueteTuristicoFromDB.get().getCostoPaquete());
    }
}