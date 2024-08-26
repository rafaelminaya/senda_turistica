package com.rminaya.sendaturistica.api.controllers;

import com.rminaya.sendaturistica.api.models.requests.ServicioRequest;
import com.rminaya.sendaturistica.api.models.responses.ErrorResponse;
import com.rminaya.sendaturistica.api.models.responses.ErrorsResponse;
import com.rminaya.sendaturistica.api.models.responses.ServicioResponse;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IServicioService;
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
@Tag(name = "servicios", description = "Operaciones CRUD para servicios")

@RestController
@RequestMapping(path = "/api/servicios")
public class ServicioController {

    private final IServicioService servicioService;

    @Operation(summary = "Obtener un servicio por su ID",
            description = "Recupera los detalles de un paquete turistico específico utilizando su ID único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "servicio encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioResponse.class))}),
            @ApiResponse(responseCode = "400", description = "id proporcionada no valida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "servicio no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping(path = "/{id}")
    public ResponseEntity<ServicioResponse> get(@PathVariable(name = "id") Integer idServicio) {
        return ResponseEntity.ok(this.servicioService.read(idServicio));
    }

    @Operation(summary = "Crea un nuevo servicio", description = "Creacion del servicio ingresando sus datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "servicio guardado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioResponse.class))}),
            @ApiResponse(responseCode = "400", description = "propiedades invalidas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))})})
    @PostMapping
    public ResponseEntity<ServicioResponse> post(@Valid @RequestBody ServicioRequest servicioRequest) {
        ServicioResponse servicioResponse = this.servicioService.create(servicioRequest);
        URI servicioUri = URI.create("/api/servicios/" + servicioResponse.getIdServicio());
        return ResponseEntity.created(servicioUri).body(servicioResponse);
    }

    @Operation(summary = "Actualiza un servicio",
            description = "Actualizacion del servicio ingresando sus datos segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "servicio actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ServicioResponse.class))}),
            @ApiResponse(responseCode = "400", description = "propiedades invalidas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))}),
            @ApiResponse(responseCode = "404", description = "servicio no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PutMapping(path = "/{id}")
    public ResponseEntity<ServicioResponse> put(@Valid @RequestBody ServicioRequest servicioRequest,
                                                @PathVariable(name = "id") Integer idServicio) {
        return ResponseEntity.ok(this.servicioService.update(servicioRequest, idServicio));
    }

    @Operation(summary = "Elimina un servicio", description = "Elimina un servicio segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "servicio eliminado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "servicio no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer idServicio) {
        this.servicioService.delete(idServicio);
        return ResponseEntity.noContent().build();
    }
}
