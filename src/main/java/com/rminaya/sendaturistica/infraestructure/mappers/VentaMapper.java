package com.rminaya.sendaturistica.infraestructure.mappers;

import com.rminaya.sendaturistica.api.models.responses.VentaResponse;
import com.rminaya.sendaturistica.domain.entities.VentaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ServicioMapper.class, PaqueteTuristicoMapper.class})
public interface VentaMapper {
    @Mappings({
            @Mapping(source = "medioPago.nombre", target = "medioPago"),
            @Mapping(source = "cliente.nombre", target = "cliente"),
            @Mapping(source = "empleado.nombre", target = "empleado"),
    })
    VentaResponse toVenta(VentaEntity ventaEntity);
}
