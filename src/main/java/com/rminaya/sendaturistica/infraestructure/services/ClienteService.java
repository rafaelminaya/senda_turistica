package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.api.models.requests.ClienteRequest;
import com.rminaya.sendaturistica.api.models.responses.ClienteResponse;
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
    @Transactional
    public ClienteResponse create(ClienteRequest clienteRequest) {
        //ClienteEntity clienteEntity = this.clienteMapper.toEntity(clienteRequest);
        //LocalDate fechaNacimiento = LocalDate.parse(clienteRequest.getFechaNacimiento().toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ClienteEntity clienteToPersist = ClienteEntity.builder()
                .nombre(clienteRequest.getNombre())
                .apellido(clienteRequest.getApellido())
                .direccion(clienteRequest.getDireccion())
                .dni(clienteRequest.getDni())
                .fechaNacimiento(clienteRequest.getFechaNacimiento())
                .nacionalidad(clienteRequest.getNacionalidad())
                .celular(clienteRequest.getCelular())
                .email(clienteRequest.getEmail())
                .build();

        ClienteEntity clientePersisted = this.clienteRepository.save(clienteToPersist);

        return this.clienteMapper.toCliente(clientePersisted);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse read(Integer idCliente) {

        ClienteEntity clienteFromDB = this.clienteRepository.findByActivoTrueAndIdCliente(idCliente)
                .orElseThrow(() -> new IdNotFoundException(Tables.clientes.name(), idCliente));

        return this.clienteMapper.toCliente(clienteFromDB);
    }

    @Override
    @Transactional
    public ClienteResponse update(ClienteRequest clienteRequest, Integer idCliente) {

        ClienteEntity clienteToUpdate = this.clienteRepository.findByActivoTrueAndIdCliente(idCliente)
                .orElseThrow(() -> new IdNotFoundException(Tables.clientes.name(), idCliente));

        clienteToUpdate.setNombre(clienteRequest.getNombre());
        clienteToUpdate.setApellido(clienteRequest.getApellido());
        clienteToUpdate.setDireccion(clienteRequest.getDireccion());
        clienteToUpdate.setFechaNacimiento(clienteRequest.getFechaNacimiento());
        clienteToUpdate.setNacionalidad(clienteRequest.getNacionalidad());
        clienteToUpdate.setCelular(clienteRequest.getCelular());
        clienteToUpdate.setEmail(clienteRequest.getEmail());

        ClienteEntity ClienteUpdated = this.clienteRepository.save(clienteToUpdate);

        return this.clienteMapper.toCliente(ClienteUpdated);
    }

    @Override
    @Transactional
    public void delete(Integer idCliente) {
        ClienteEntity clienteToDelete = this.clienteRepository.findByActivoTrueAndIdCliente(idCliente)
                .orElseThrow(() -> new IdNotFoundException(Tables.clientes.name(), idCliente));

        clienteToDelete.setActivo(false);

        this.clienteRepository.save(clienteToDelete);
    }
}
