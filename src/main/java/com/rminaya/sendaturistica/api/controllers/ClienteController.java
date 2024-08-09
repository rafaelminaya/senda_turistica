package com.rminaya.sendaturistica.api.controllers;

import com.rminaya.sendaturistica.api.models.requests.ClienteResponse;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteResponse> get(@PathVariable(name = "id") Integer idCliente) {
        return ResponseEntity.ok(this.clienteService.read(idCliente));
    }
}
