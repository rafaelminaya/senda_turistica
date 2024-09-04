package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.VentaEntity;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    private static final Integer VALID_ID = 1;
    private static final Integer ID_NO_ACTIVO = 20;
    private static final Integer INVALID_ID = 1000;

    @Test
    @DisplayName("findFirstActivoTrueAndIdVenta() - works")
    void testFindFirstActivoTrueAndIdVenta() {
        Optional<VentaEntity> venta = this.ventaRepository.findFirstByActivoTrueAndIdVenta(VALID_ID);
        assertTrue(venta.isPresent());
        assertEquals(true,venta.get().getActivo());
        assertEquals(VALID_ID, venta.get().idVenta);
    }

    @Test
    @DisplayName("findFirstActivoTrueAndIdVenta() - doesn't work")
    void testFindFirstActivoTrueAndIdVentaNotFound() {
        Optional<VentaEntity> venta = this.ventaRepository.findFirstByActivoTrueAndIdVenta(INVALID_ID);
        assertTrue(venta.isEmpty());
    }

    @Test
    @DisplayName("findFirstActivoTrueAndIdVenta() - get inactive")
    void testFindFirstActivoTrueAndIdVentaException() {
        Optional<VentaEntity> venta = this.ventaRepository.findById(ID_NO_ACTIVO);
        assertTrue(venta.isPresent());
        assertEquals(false, venta.get().getActivo());

    }
}