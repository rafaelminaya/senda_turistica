package com.rminaya.sendaturistica.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.requests.PaqueteTuristicoRequest;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IPaqueteTuristicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaqueteTuristicoController.class)
class PaqueteTuristicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IPaqueteTuristicoService paqueteTuristicoService;

    ObjectMapper objectMapper;
    private static final Integer VALID_ID = 1;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("GET - Works")
    void testGet() throws Exception {
        when(this.paqueteTuristicoService.read(anyInt())).thenReturn(Datos.PAQUETE_TURISTICO_RESPONSE);

        this.mvc.perform(get("/api/paquetes_turisticos/" + VALID_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.PAQUETE_TURISTICO_RESPONSE)))
                .andExpect(jsonPath("$.idPaqueteTuristico").value(1));

        verify(this.paqueteTuristicoService, times(1)).read(anyInt());
    }

    @Test
    @DisplayName("POST - Works")
    void testPost() throws Exception {
        when(this.paqueteTuristicoService.create(any(PaqueteTuristicoRequest.class))).thenReturn(Datos.PAQUETE_TURISTICO_RESPONSE);

        this.mvc.perform(post("/api/paquetes_turisticos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(Datos.PAQUETE_TURISTICO_REQUEST)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/paquetes_turisticos/" + Datos.PAQUETE_TURISTICO_RESPONSE.getIdPaqueteTuristico()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.PAQUETE_TURISTICO_RESPONSE)))
                .andExpect(jsonPath("$.idPaqueteTuristico").value(1));

        verify(this.paqueteTuristicoService, times(1)).create(any(PaqueteTuristicoRequest.class));
    }

    @Test
    @DisplayName("PUT - Works")
    void testPut() throws Exception {
        when(this.paqueteTuristicoService.update(any(PaqueteTuristicoRequest.class), anyInt())).thenReturn(Datos.PAQUETE_TURISTICO_RESPONSE);

        this.mvc.perform(put("/api/paquetes_turisticos/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(Datos.PAQUETE_TURISTICO_REQUEST)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.PAQUETE_TURISTICO_RESPONSE)))
                .andExpect(jsonPath("$.idPaqueteTuristico").value(VALID_ID));

        verify(this.paqueteTuristicoService, times(1)).update(any(PaqueteTuristicoRequest.class), anyInt());
    }

    @Test
    @DisplayName("DELETE - Works")
    void testDelete() throws Exception {
        this.mvc.perform(delete("/api/paquetes_turisticos/" + VALID_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(this.paqueteTuristicoService, times(1)).delete(VALID_ID);
    }
}