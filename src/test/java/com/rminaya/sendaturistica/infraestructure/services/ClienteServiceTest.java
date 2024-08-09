package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.responses.ClienteResponse;
import com.rminaya.sendaturistica.domain.repositories.ClienteRepository;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceTest {

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private IClienteService clienteService;

    private static final Integer VALID_ID = 1;
    private static final Integer INVALID_ID = 1000;

    @Test
    void testCreate() {
    }

    @Test
    @DisplayName("read() - Encuentra al cliente con ID 1")
    void testRead() {
        // GIVEN
        //when(this.clienteRepository.findById(1)).thenReturn(Optional.of(Datos.CLIENTE));
        when(this.clienteRepository.findById(VALID_ID)).thenReturn(Optional.of(Datos.CLIENTE));
        // WHEN
        ClienteResponse cliente1 = this.clienteService.read(VALID_ID);
        ClienteResponse cliente2 = this.clienteService.read(VALID_ID);
        // THEN
        System.out.println(cliente1.equals(cliente2));
        System.out.println(cliente1.hashCode() == cliente2.hashCode());
        assertEquals(cliente1, cliente2);
        assertEquals("Carlos", cliente1.getNombre());
        assertEquals("Rojas", cliente2.getApellido());

        verify(this.clienteRepository, times(2)).findById(VALID_ID);
    }

    @Test
    @DisplayName("read() - Debería fallar la busqueda de cliente con un ID inexistente")
    void testReadException() {
        // GIVEN
        when(this.clienteRepository.findById(INVALID_ID)).thenThrow(Datos.CLIENTE_INVALID);
        // WHEN y THEN juntos
        assertThrows(IdNotFoundException.class, () -> Optional.of(this.clienteService.read(INVALID_ID)));
        //THEN
        verify(this.clienteRepository, times(1)).findById(anyInt());
    }

    @Test
    void testUpdate() {
    }

    @Test
    void testDelete() {
    }
}