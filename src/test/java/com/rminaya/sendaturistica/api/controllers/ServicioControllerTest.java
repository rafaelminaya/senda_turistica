package com.rminaya.sendaturistica.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.requests.ServicioRequest;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IServicioService;
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

@WebMvcTest(ServicioController.class)
class ServicioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IServicioService servicioService;

    private final static Integer VALID_ID = 1;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("GET - Works")
    void testGet() throws Exception {

        when(this.servicioService.read(anyInt())).thenReturn(Datos.SERVICIO_RESPONSE);

        this.mvc.perform(get("/api/servicios/" + VALID_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.SERVICIO_RESPONSE)))
                .andExpect(jsonPath("$.idServicio").value(VALID_ID))
                .andExpect(jsonPath("$.nombre").value("Alquiler 1 dormitorio"));

        verify(this.servicioService, times(1)).read(anyInt());
    }

    @Test
    @DisplayName("POST - Works")
    void testPost() throws Exception {

        when(this.servicioService.create(any(ServicioRequest.class))).thenReturn(Datos.SERVICIO_RESPONSE);

        this.mvc.perform(post("/api/servicios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(Datos.SERVICIO_REQUEST)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/servicios/" + Datos.SERVICIO_RESPONSE.getIdServicio()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.SERVICIO_RESPONSE)))
                .andExpect(jsonPath("$.idServicio").value(1))
                .andExpect(jsonPath("$.nombre").value("Alquiler 1 dormitorio"))
                .andExpect(jsonPath("$.descripcionBreve").value("Alquiler de 1 dormitorio para persona sola por 3 noches"));

        verify(this.servicioService, times(1)).create(any(ServicioRequest.class));
    }

    @Test
    @DisplayName("PUT - Works")
    void testPut() throws Exception {
        when(this.servicioService.update(any(ServicioRequest.class), anyInt())).thenReturn(Datos.SERVICIO_RESPONSE);

        this.mvc.perform(put("/api/servicios/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(Datos.SERVICIO_REQUEST)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.SERVICIO_RESPONSE)))
                .andExpect(jsonPath("$.idServicio", is(VALID_ID)))
                .andExpect(jsonPath("$.nombre", is("Alquiler 1 dormitorio")));

        verify(this.servicioService, times(1)).update(any(ServicioRequest.class), anyInt());
    }

    @Test
    @DisplayName("DELETE - Works")
    void testDelete() throws Exception {
        this.mvc.perform(delete("/api/servicios/" + VALID_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(this.servicioService, times(1)).delete(anyInt());
    }
}