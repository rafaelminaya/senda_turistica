package com.rminaya.sendaturistica.api.controllers;

import com.rminaya.sendaturistica.api.models.requests.PaqueteTuristicoRequest;
import com.rminaya.sendaturistica.api.models.responses.ErrorResponse;
import com.rminaya.sendaturistica.api.models.responses.ErrorsResponse;
import com.rminaya.sendaturistica.api.models.responses.PaqueteTuristicoResponse;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IPaqueteTuristicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RequiredArgsConstructor
@Tag(name = "paquetes_turisticos", description = "Operaciones CRUD para paquetes_turisticos")

@RestController
@RequestMapping(path = "/api/paquetes_turisticos")
public class PaqueteTuristicoController {

    private final IPaqueteTuristicoService paqueteTuristicoService;

    @Operation(summary = "Obtener un paquete turistico por su ID",
            description = "Recupera los detalles de un paquete turistico específico utilizando su ID único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "paquete turistico encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaqueteTuristicoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "id proporcionada no valida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "paquete turistico no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<PaqueteTuristicoResponse> get(@PathVariable(name = "id") Integer idPaquInteger) {
        return ResponseEntity.ok(this.paqueteTuristicoService.read(idPaquInteger));
    }

    @Operation(summary = "Guarda un nuevo paquete turistico",
            description = "Creacion del paquete turistico ingresando sus datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "paquete turistico guardado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaqueteTuristicoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "propiedades invalidas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))})})
    @PostMapping
    public ResponseEntity<PaqueteTuristicoResponse> post(@Valid @RequestBody PaqueteTuristicoRequest paqueteTuristicoRequest) {
        PaqueteTuristicoResponse paqueteTuristicoResponse = this.paqueteTuristicoService.create(paqueteTuristicoRequest);
        URI paqueteTuristicoUri = URI.create("/api/paquetes_turisticos/" + paqueteTuristicoResponse.getIdPaqueteTuristico());
        return ResponseEntity.created(paqueteTuristicoUri).body(paqueteTuristicoResponse);
    }

    @Operation(summary = "Actualiza un nuevo paquete turistico",
            description = "Actualizacion del paquete turistico ingresando sus datos segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "paquete turistico actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaqueteTuristicoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "propiedades invalidas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))}),
            @ApiResponse(responseCode = "404", description = "paquete turistico no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PutMapping(path = "/{id}")
    public ResponseEntity<PaqueteTuristicoResponse> put(@Valid @RequestBody PaqueteTuristicoRequest paqueteTuristicoRequest,
                                                        @PathVariable(name = "id") Integer idPaqueteTuristico) {
        return ResponseEntity.ok(this.paqueteTuristicoService.update(paqueteTuristicoRequest, idPaqueteTuristico));
    }

    @Operation(summary = "Elimina un paquete turistico", description = "Elimina un paquete turistico segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "paquete turistico eliminado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "paquete turistico no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<PaqueteTuristicoResponse> delete(@PathVariable(name = "id") Integer idPaquInteger) {
        this.paqueteTuristicoService.delete(idPaquInteger);
        return ResponseEntity.noContent().build();
    }
}
