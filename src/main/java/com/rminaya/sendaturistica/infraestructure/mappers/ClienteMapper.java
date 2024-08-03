package com.rminaya.sendaturistica.infraestructure.mappers;

import com.rminaya.sendaturistica.api.models.requests.ClienteResponse;
import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    ClienteResponse toCliente(ClienteEntity clienteEntity);
}
