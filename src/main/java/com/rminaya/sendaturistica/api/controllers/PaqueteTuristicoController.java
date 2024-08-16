package com.rminaya.sendaturistica.api.controllers;

import com.rminaya.sendaturistica.api.models.requests.PaqueteTuristicoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rminaya.sendaturistica.api.models.responses.PaqueteTuristicoResponse;
import com.rminaya.sendaturistica.infraestructure.services.PaqueteTuristicoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Tag(name = "paquetes_turisticos")
@RestController
@RequestMapping(path = "/api/paquetes_turisticos")
public class PaqueteTuristicoController {

    private final PaqueteTuristicoService paqueteTuristicoService;

    @Operation(summary = "Obtener un paquete turistico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<PaqueteTuristicoResponse> get(@PathVariable(name = "id") Integer idPaquInteger) {
        return ResponseEntity.ok(this.paqueteTuristicoService.read(idPaquInteger));
    }

    @Operation(summary = "Guarda un nuevo paquete turistico")
    @PostMapping
    public ResponseEntity<PaqueteTuristicoResponse> post(@RequestBody PaqueteTuristicoRequest paqueteTuristicoRequest) {
        return ResponseEntity.ok(this.paqueteTuristicoService.create(paqueteTuristicoRequest));
    }

    @Operation(summary = "Actualiza un nuevo paquete turistico")
    @PutMapping(path = "/{id}")
    public ResponseEntity<PaqueteTuristicoResponse> put(@RequestBody PaqueteTuristicoRequest paqueteTuristicoRequest,
                                                        @PathVariable(name = "id") Integer idPaqueteTuristico) {
        return ResponseEntity.ok(this.paqueteTuristicoService.update(paqueteTuristicoRequest, idPaqueteTuristico));
    }

    @Operation(summary = "Elimina un paquete turistico")
    @DeleteMapping("/{id}")
    public ResponseEntity<PaqueteTuristicoResponse> delete(@PathVariable(name = "id") Integer idPaquInteger) {
        this.paqueteTuristicoService.delete(idPaquInteger);
        return ResponseEntity.noContent().build();
    }

}
