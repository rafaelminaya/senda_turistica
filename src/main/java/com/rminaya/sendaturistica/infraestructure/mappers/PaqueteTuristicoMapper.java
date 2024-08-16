package com.rminaya.sendaturistica.infraestructure.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.rminaya.sendaturistica.api.models.responses.PaqueteTuristicoResponse;
import com.rminaya.sendaturistica.domain.entities.PaqueteTuristicoEntity;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ServicioMapper.class})
public interface PaqueteTuristicoMapper {

    @Mappings({
            @Mapping(source = "idPaqueteTuristico", target = "idPaqueteTuristico"),
            @Mapping(source = "costoPaquete", target = "costoPaquete"),
            @Mapping(source = "servicios", target = "servicios")
    })
    PaqueteTuristicoResponse toPaqueteTuristico(PaqueteTuristicoEntity paqueteTuristicoEntity);

}
