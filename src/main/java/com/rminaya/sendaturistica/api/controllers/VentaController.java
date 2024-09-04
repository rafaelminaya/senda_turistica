package com.rminaya.sendaturistica.api.controllers;

import com.rminaya.sendaturistica.api.models.requests.VentaRequest;
import com.rminaya.sendaturistica.api.models.responses.VentaResponse;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@Tag(name = "ventas", description = "Operaciones CRUD para ventas")

@RestController
@RequestMapping(path = "/api/ventas")
public class VentaController {

    private final IVentaService ventaService;

    @Operation(summary = "Obtener una venta por su ID",
            description = "Recupera los detalles de una venta específica utilizando su ID único")
    @GetMapping(path = "/{id}")
    public ResponseEntity<VentaResponse> get(@PathVariable(name = "id") Integer idVenta) {
        return ResponseEntity.ok(this.ventaService.read(idVenta));
    }

    @Operation(summary = "Crea una nueva venta", description = "Creacion de la venta ingresando sus datos")
    @PostMapping
    public ResponseEntity<VentaResponse> post(@Valid @RequestBody VentaRequest ventaRequest) {
        VentaResponse ventaResponse = this.ventaService.create(ventaRequest);
        URI ventaUri = URI.create("/api/ventas/" + ventaResponse.getIdVenta());
        return ResponseEntity.created(ventaUri).body(ventaResponse);
    }

    @Operation(summary = "Actualiza una venta",
            description = "Actualizacion de la venta ingresando sus datos segun su ID")
    @PutMapping(path = "/{id}")
    public ResponseEntity<VentaResponse> put(@Valid @RequestBody VentaRequest ventaRequest,
                                             @PathVariable(name = "id") Integer idVenta) {
        return ResponseEntity.ok(this.ventaService.update(ventaRequest, idVenta));
    }

    @Operation(summary = "Elimina una venta", description = "Elimina una venta segun su ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer idVenta) {
        this.ventaService.delete(idVenta);
        return ResponseEntity.noContent().build();
    }
}
