package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.requests.ClienteResponse;
import com.rminaya.sendaturistica.domain.repositories.ClienteRepository;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceTest {

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private IClienteService clienteService;

    @Test
    void testCreate() {
    }

    @Test
    @DisplayName("ClienteService - read()")
    void testRead() {
        // GIVEN
        when(this.clienteRepository.findById(1)).thenReturn(Optional.of(Datos.crearCliente001()));
        // WHEN
        ClienteResponse cliente = this.clienteService.read(1);
        // THEN
        assertEquals("Carlos", cliente.getNombre());
        assertEquals("Rojas", cliente.getApellido());

        verify(this.clienteRepository, times(1)).findById(1);
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testDelete() {
    }
}