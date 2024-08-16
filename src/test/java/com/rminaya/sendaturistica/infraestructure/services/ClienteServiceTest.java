package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.responses.ClienteResponse;
import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
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
    @DisplayName("create() - Deberia crear un cliente")
    void testCreate() {
        // GIVEN
        when(this.clienteRepository.save(any(ClienteEntity.class))).then(invocationOnMock -> {
            ClienteEntity cliente = invocationOnMock.getArgument(0);
            cliente.setIdCliente(100);
            cliente.setNombre("Carlos");
            cliente.setApellido("Rojas");
            return cliente;
        });
        // WHEN
        ClienteResponse cliente = this.clienteService.create(Datos.CLIENTE_REQUEST);
        assertEquals(100, cliente.getIdCliente());
        assertEquals("Carlos", cliente.getNombre());
        assertEquals("Rojas", cliente.getApellido());
        // THEN
        verify(this.clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    @DisplayName("read() - Encuentra al cliente con ID valido")
    void testRead() {
        // GIVEN
        when(this.clienteRepository.findFirstByActivoTrueAndIdCliente(VALID_ID)).thenReturn(Optional.of(Datos.CLIENTE_ENTITY));
        // WHEN
        ClienteResponse cliente1 = this.clienteService.read(VALID_ID);
        ClienteResponse cliente2 = this.clienteService.read(VALID_ID);
        // THEN
        System.out.println(cliente1.equals(cliente2));
        System.out.println(cliente1.hashCode() == cliente2.hashCode());
        assertEquals(cliente1, cliente2);
        assertEquals(VALID_ID, cliente1.getIdCliente());
        assertEquals("Carlos", cliente1.getNombre());
        assertEquals("Heredia", cliente2.getApellido());

        verify(this.clienteRepository, times(2)).findFirstByActivoTrueAndIdCliente(VALID_ID);
    }

    @Test
    @DisplayName("read() - Debería fallar la busqueda de cliente con un ID inexistente")
    void testReadException() {
        // GIVEN
        when(this.clienteRepository.findFirstByActivoTrueAndIdCliente(INVALID_ID)).thenThrow(Datos.CLIENTE_INVALID);
        // WHEN y THEN juntos
        assertThrows(IdNotFoundException.class, () -> Optional.of(this.clienteService.read(INVALID_ID)));
        //THEN
        verify(this.clienteRepository, times(1)).findFirstByActivoTrueAndIdCliente(anyInt());
    }

    @Test
    @DisplayName("update() - Deberia actualizar un cliente")
    void testUpdate() {
        when(this.clienteRepository.findFirstByActivoTrueAndIdCliente(anyInt())).thenReturn(Optional.of(Datos.CLIENTE_ENTITY));
        when(this.clienteRepository.save(any(ClienteEntity.class))).thenReturn(Datos.CLIENTE_ENTITY);

        ClienteResponse cliente = this.clienteService.update(Datos.CLIENTE_REQUEST, VALID_ID);
        assertEquals(Datos.CLIENTE_RESPONSE, cliente);

        verify(this.clienteRepository, times(1)).findFirstByActivoTrueAndIdCliente(anyInt());
        verify(this.clienteRepository, times(1)).save(any(ClienteEntity.class));
    }

    @Test
    @DisplayName("delete() - Elimina un cliente por su id")
    void testDelete() {
        when(this.clienteRepository.findFirstByActivoTrueAndIdCliente(anyInt())).thenReturn(Optional.of(Datos.CLIENTE_ENTITY));
        when(this.clienteRepository.save(any(ClienteEntity.class))).then(invocationOnMock -> {
            ClienteEntity clienteUpdated = invocationOnMock.getArgument(0);
            clienteUpdated.setActivo(false);
            return clienteUpdated;
        });

        this.clienteService.delete(VALID_ID);

        verify(this.clienteRepository, times(1)).findFirstByActivoTrueAndIdCliente(anyInt());
        verify(this.clienteRepository, times(1)).save(any(ClienteEntity.class));
    }
}