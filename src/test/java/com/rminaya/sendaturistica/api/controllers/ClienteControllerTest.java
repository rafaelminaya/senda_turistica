package com.rminaya.sendaturistica.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IClienteService clienteService;

    ObjectMapper objectMapper;

    private static final Integer VALID_ID = 1;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("get() - Encuentra al cliente con ID 1")
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
}