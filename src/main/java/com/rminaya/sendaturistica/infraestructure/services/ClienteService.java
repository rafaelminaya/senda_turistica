package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.api.models.requests.ClienteResponse;
import com.rminaya.sendaturistica.api.models.responses.ClienteRequest;
import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import com.rminaya.sendaturistica.domain.repositories.ClienteRepository;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;
import com.rminaya.sendaturistica.infraestructure.mappers.ClienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public ClienteResponse create(ClienteRequest clienteRequest) {
        return null;
    }

    @Override
    public ClienteResponse read(Integer idCliente) {
        ClienteEntity clienteFromDB = this.clienteRepository.findById(idCliente).orElseThrow();
        return this.clienteMapper.toCliente(clienteFromDB);
    }

    @Override
    public ClienteResponse update(ClienteRequest clienteRequest, Integer idCliente) {
        return null;
    }

    @Override
    public void delete(Integer idCliente) {

    }
}
