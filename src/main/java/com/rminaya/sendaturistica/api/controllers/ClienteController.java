package com.rminaya.sendaturistica.api.controllers;

import com.rminaya.sendaturistica.api.models.requests.ClienteRequest;
import com.rminaya.sendaturistica.api.models.responses.ClienteResponse;
import com.rminaya.sendaturistica.api.models.responses.ErrorResponse;
import com.rminaya.sendaturistica.api.models.responses.ErrorsResponse;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/clientes")
@RequiredArgsConstructor
@Tag(name = "clientes")
public class ClienteController {

    private final IClienteService clienteService;

    @Operation(summary = "Obtener un cliente por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "id proporcionada no valida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "cliente no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteResponse> get(
            @Parameter(description = "id del cliente a buscar")
            @PathVariable(name = "id") Integer idCliente) {
        return ResponseEntity.ok(this.clienteService.read(idCliente));
    }


    @Operation(summary = "Guarda un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente guardado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "propiedades invalidas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))})})
    @PostMapping
    public ResponseEntity<ClienteResponse> post(@Valid @RequestBody ClienteRequest clienteRequest) {
        return ResponseEntity.ok().body(this.clienteService.create(clienteRequest));
    }

    @Operation(summary = "Actualiza un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cliente actualizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "propiedades invalidas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorsResponse.class))}),
            @ApiResponse(responseCode = "404", description = "cliente no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> put(@Valid @RequestBody ClienteRequest clienteRequest,
                                               @PathVariable(name = "id") Integer idCliente) {
        return ResponseEntity.ok(this.clienteService.update(clienteRequest, idCliente));
    }

    @Operation(summary = "Elimina un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "cliente eliminado",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "cliente no encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer idCliente) {
        this.clienteService.delete(idCliente);
        return ResponseEntity.noContent().build();
    }

}
