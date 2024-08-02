package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.api.models.requests.ClienteResponse;
import com.rminaya.sendaturistica.api.models.responses.ClienteRequest;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;

public class ClienteService implements IClienteService {

    @Override
    public ClienteResponse create(ClienteRequest clienteRequest) {
        return null;
    }

    @Override
    public ClienteResponse read(Integer idCliente) {
        return null;
    }

    @Override
    public ClienteResponse update(ClienteRequest clienteRequest, Integer idCliente) {
        return null;
    }

    @Override
    public void delete(Integer idCliente) {

    }
}
