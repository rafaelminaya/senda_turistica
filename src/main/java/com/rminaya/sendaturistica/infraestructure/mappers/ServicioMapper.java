package com.rminaya.sendaturistica.infraestructure.mappers;

import com.rminaya.sendaturistica.api.models.responses.ServicioResponse;
import com.rminaya.sendaturistica.domain.entities.ServicioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServicioMapper {
    @Mappings({
            @Mapping(source = "fechaServicio", target = "fechaServicio", dateFormat = "dd/MM/yyyy"),
            @Mapping(source = "tipoServicio.nombre", target = "tipoServicio")
    })
    ServicioResponse toServicio(ServicioEntity servicio);

    List<ServicioResponse> toServicioList(List<ServicioEntity> servicioList);
}
