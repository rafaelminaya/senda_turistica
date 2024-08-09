package com.rminaya.sendaturistica.infraestructure.mappers;

import com.rminaya.sendaturistica.api.models.requests.ClienteResponse;
import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    @Mappings({
            @Mapping(source = "fechaNacimiento", target = "fechaNacimiento", dateFormat = "dd/MM/yyyy")
    })
    ClienteResponse toCliente(ClienteEntity clienteEntity);
}
