package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.api.models.responses.ClienteResponse;
import com.rminaya.sendaturistica.api.models.requests.ClienteRequest;
import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import com.rminaya.sendaturistica.domain.repositories.ClienteRepository;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IClienteService;
import com.rminaya.sendaturistica.infraestructure.mappers.ClienteMapper;
import com.rminaya.sendaturistica.util.enums.Tables;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public ClienteResponse create(ClienteRequest clienteRequest) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse read(Integer idCliente) {
        System.out.println("idCliente: "+ idCliente);
        ClienteEntity clienteFromDB = this.clienteRepository.findById(idCliente)
                        .orElseThrow(() -> new IdNotFoundException(Tables.clientes.name(), idCliente));

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
