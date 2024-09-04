package com.rminaya.sendaturistica.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rminaya.sendaturistica.Datos;
import com.rminaya.sendaturistica.api.models.requests.VentaRequest;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IVentaService;
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

@WebMvcTest(value = VentaController.class)
class VentaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IVentaService ventaService;

    private ObjectMapper objectMapper;

    private static final Integer VALID_ID = 1;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        // Deshabilita la serialización de las fechas a "array de String",
        // manteniéndose en su tipo de dato original de la fecha elegida.
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    @DisplayName("GET - works")
    void testGet() throws Exception {

        when(this.ventaService.read(anyInt())).thenReturn(Datos.VENTA_RESPONSE);
        System.out.println(this.objectMapper.writeValueAsString(Datos.VENTA_RESPONSE));

        this.mvc.perform(get("/api/ventas/" + VALID_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.VENTA_RESPONSE)))
                .andExpect(jsonPath("$.idVenta").value(1))
                .andExpect(jsonPath("$.fechaVenta").value("2024-07-03T13:20:00"))
                .andExpect(jsonPath("$.tipo").value("S"));

        verify(this.ventaService, times(1)).read(anyInt());
    }

    @Test
    @DisplayName("POST - works")
    void testPost() throws Exception {

        when(this.ventaService.create(any(VentaRequest.class))).thenReturn(Datos.VENTA_RESPONSE);

        this.mvc.perform(post("/api/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(Datos.VENTA_REQUEST)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/ventas/" + Datos.VENTA_RESPONSE.getIdVenta()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.VENTA_RESPONSE)))
                .andExpect(jsonPath("$.idVenta").value(1));

        verify(this.ventaService, times(1)).create(any(VentaRequest.class));
    }

    @Test
    @DisplayName("PUT - works")
    void testPut() throws Exception {
        when(this.ventaService.update(any(VentaRequest.class), anyInt())).thenReturn(Datos.VENTA_RESPONSE);

        this.mvc.perform(put("/api/ventas/" + VALID_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(Datos.VENTA_REQUEST)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(this.objectMapper.writeValueAsString(Datos.VENTA_RESPONSE)));

        verify(this.ventaService, times(1)).update(any(VentaRequest.class), anyInt());
    }

    @Test
    @DisplayName("DELETE - works")
    void testDelete() throws Exception {
        this.mvc.perform(delete("/api/ventas/" + VALID_ID))
                .andExpect(status().isNoContent());

        verify(this.ventaService, times(1)).delete(anyInt());
    }
}