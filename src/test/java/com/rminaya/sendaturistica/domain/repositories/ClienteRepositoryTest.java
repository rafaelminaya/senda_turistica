package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
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
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    @DisplayName("findById() - Encuentra cliente con ID 1")
    void testFindById() {
        Optional<ClienteEntity> cliente = this.clienteRepository.findById(1);

        assertTrue(cliente.isPresent());
        assertEquals(1, cliente.get().getIdCliente());
        System.out.println("cliente = " + cliente.get());
    }

    @Test
    @DisplayName("findById() - Debería fallar la busqueda de cliente con un ID inexistente")
    void testFindByIdException() {
        Optional<ClienteEntity> cliente = this.clienteRepository.findById(1000);
        assertTrue(cliente.isEmpty());
    }

}