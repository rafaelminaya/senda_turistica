package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.EmpleadoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private static final Integer VALID_ID = 1;

    @Test
    @DisplayName("findFirstByActivoTrueAndIdCliente() - works")
    void testFindFirstByActivoTrueAndIdCliente() {
        Optional<EmpleadoEntity> empleado = this.empleadoRepository.findFirstByActivoTrueAndIdCliente(VALID_ID);
        assertTrue(empleado.isPresent());
        assertEquals("Administrador", empleado.get().getCargo());
        assertEquals(VALID_ID, empleado.get().getIdCliente());
        assertEquals(6000, empleado.get().getSueldo());
        assertEquals(true, empleado.get().getActivo());
    }
}