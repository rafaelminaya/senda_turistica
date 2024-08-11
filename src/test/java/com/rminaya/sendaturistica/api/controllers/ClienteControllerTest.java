package com.rminaya.sendaturistica.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.requests.ClienteRequest;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IClienteService clienteService;

    ObjectMapper objectMapper;

    private static final Integer VALID_ID = 1000;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("get() - Encuentra al cliente con ID valido")
    void testGet() throws Exception {
        // GIVEN
        when(this.clienteService.read(VALID_ID)).thenReturn(Datos.CLIENTE_RESPONSE);
        // WHEN
        this.mvc.perform(get("/api/clientes/" + VALID_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idCliente").value(VALID_ID))
                .andExpect(jsonPath("$.nombre").value("Carlos"))
                .andExpect(jsonPath("$.apellido").value("Rojas"))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.CLIENTE_RESPONSE)));
        // THEN
        verify(this.clienteService, times(1)).read(VALID_ID);
    }

    @Test
    @DisplayName("post() - Crea un nuevo cliente")
    void testPost() throws Exception {
        // GIVEN
        when(this.clienteService.create(eq(Datos.CLIENTE_REQUEST))).thenReturn(Datos.CLIENTE_RESPONSE);
        // WHEN
        this.mvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(Datos.CLIENTE_REQUEST)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.CLIENTE_RESPONSE)))
                .andExpect(jsonPath("$.idCliente", is(1000)))
                .andExpect(jsonPath("$.nombre", is("Carlos")))
                .andExpect(jsonPath("$.apellido", is("Rojas")));
        // THEN
        verify(this.clienteService, times(1)).create(any());
    }

    @Test
    @DisplayName("put() - Actualiza un cliente")
    void testPut() throws Exception {
        when(this.clienteService.update(any(ClienteRequest.class), anyInt())).thenReturn(Datos.CLIENTE_RESPONSE);

        this.mvc.perform(put("/api/clientes/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(Datos.CLIENTE_REQUEST)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.CLIENTE_RESPONSE)))
                .andExpect(jsonPath("$.idCliente", is(1000)));

        verify(this.clienteService, times(1)).update(any(ClienteRequest.class), anyInt());
    }

    @Test
    @DisplayName("delete() - Elimina un cliente")
    void testDelete() throws Exception {

        this.mvc.perform(delete("/api/clientes/" + VALID_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(this.clienteService, times(1)).delete(anyInt());

    }
}