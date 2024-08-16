package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    private static final Integer ID_VALID = 1;
    private static final Integer ID_NO_ACTIVO = 15;
    private static final Integer ID_INVALID = 1000;

    @Test
    @DisplayName("findById() - Encuentra cliente con ID 1")
    void testFindById() {
        Optional<ClienteEntity> cliente = this.clienteRepository.findById(ID_VALID);

        assertTrue(cliente.isPresent());
        assertEquals(ID_VALID, cliente.get().getIdCliente());
        System.out.println("cliente = " + cliente.get());
    }

    @Test
    @DisplayName("findById() - Debería fallar la busqueda de cliente con un ID inexistente")
    void testFindByIdException() {
        Optional<ClienteEntity> cliente = this.clienteRepository.findById(ID_INVALID);
        assertTrue(cliente.isEmpty());
    }

    @Test
    @DisplayName("save() - Guarda un cliente")
    void testSave() {

        ClienteEntity clienteToPersist = ClienteEntity.builder()
                .nombre("Carlos")
                .apellido("Heredia")
                .direccion("Av. Rosales 1293")
                .dni("38467245")
                .fechaNacimiento(LocalDate.of(1985, 7, 12))
                .nacionalidad("Peruana")
                .celular("934854732")
                .email("carlos.heredia@gmail.com")
                .activo(true)
                .build();

        ClienteEntity clienteFromDB = this.clienteRepository.save(clienteToPersist);
        Optional<ClienteEntity> cliente = this.clienteRepository.findById(clienteFromDB.getIdCliente());
        assertTrue(cliente.isPresent());
        assertEquals("Carlos", cliente.get().getNombre());
        assertEquals("Heredia", cliente.get().getApellido());
    }

    @Test
    @DisplayName("findByActivoTrue() - Works")
    void testFindByIdActivoTrue() {
        Optional<ClienteEntity> cliente = this.clienteRepository.findFirstByActivoTrueAndIdCliente(ID_VALID);
        assertTrue(cliente.isPresent());
        assertEquals(true, cliente.get().getActivo());
    }

    @Test
    @DisplayName("findByActivoTrue() - Doesn't Work")
    void testFindByIdActivoTrueFailed() {
        Optional<ClienteEntity> cliente = this.clienteRepository.findFirstByActivoTrueAndIdCliente(ID_NO_ACTIVO);
        assertTrue(cliente.isEmpty());
    }
}