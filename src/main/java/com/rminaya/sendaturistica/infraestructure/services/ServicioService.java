package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.api.models.requests.ServicioRequest;
import com.rminaya.sendaturistica.api.models.responses.ServicioResponse;
import com.rminaya.sendaturistica.domain.entities.ServicioEntity;
import com.rminaya.sendaturistica.domain.entities.TipoServicioEntity;
import com.rminaya.sendaturistica.domain.repositories.ServicioRepository;
import com.rminaya.sendaturistica.domain.repositories.TipoServicioRepository;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IServicioService;
import com.rminaya.sendaturistica.infraestructure.mappers.ServicioMapper;
import com.rminaya.sendaturistica.util.enums.Tables;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ServicioService implements IServicioService {

    private final ServicioRepository servicioRepository;
    private final TipoServicioRepository tipoServicioRepository;

    @Autowired
    ServicioMapper servicioMapper;

    @Override
    @Transactional
    public ServicioResponse create(ServicioRequest servicioRequest) {

        TipoServicioEntity tipoServicio = this.tipoServicioRepository
                .findFirstByActivoTrueAndIdTipoServicio(servicioRequest.getIdTipoServicio()).
                orElseThrow(() -> new IdNotFoundException(Tables.tipos_servicio.name(), servicioRequest.getIdTipoServicio()));

        ServicioEntity servicioToPersist = ServicioEntity.builder()
                .nombre(servicioRequest.getNombre())
                .descripcionBreve(servicioRequest.getDescripcionBreve())
                .fechaServicio(servicioRequest.getFechaServicio())
                .costoServicio(servicioRequest.getCostoServicio())
                .tipoServicio(tipoServicio)
                .build();

        ServicioEntity servicioPersisted = this.servicioRepository.save(servicioToPersist);

        return this.servicioMapper.toServicio(servicioPersisted);
    }

    @Override
    @Transactional(readOnly = true)
    public ServicioResponse read(Integer idServicio) {
        ServicioEntity servicioFromDB = this.servicioRepository.findFirstByActivoTrueAndIdServicio(idServicio)
                .orElseThrow(() -> new IdNotFoundException(Tables.servicios.name(), idServicio));
        return this.servicioMapper.toServicio(servicioFromDB);
    }

    @Override
    @Transactional
    public ServicioResponse update(ServicioRequest servicioRequest, Integer idServicio) {
        TipoServicioEntity tipoServicio = this.tipoServicioRepository
                .findFirstByActivoTrueAndIdTipoServicio(servicioRequest.getIdTipoServicio())
                .orElseThrow(() -> new IdNotFoundException(Tables.tipos_servicio.name(), servicioRequest.getIdTipoServicio()));

        ServicioEntity servicioToUpdate = this.servicioRepository.findFirstByActivoTrueAndIdServicio(idServicio)
                .orElseThrow(() -> new IdNotFoundException(Tables.servicios.name(), idServicio));

        servicioToUpdate.setNombre(servicioRequest.getNombre());
        servicioToUpdate.setDescripcionBreve(servicioRequest.getDescripcionBreve());
        servicioToUpdate.setFechaServicio(servicioRequest.getFechaServicio());
        servicioToUpdate.setCostoServicio(servicioToUpdate.getCostoServicio());
        servicioToUpdate.setTipoServicio(tipoServicio);

        ServicioEntity servicioUpdated = this.servicioRepository.save(servicioToUpdate);

        return this.servicioMapper.toServicio(servicioUpdated);
    }

    @Override
    @Transactional
    public void delete(Integer idServicio) {
        ServicioEntity servicioToDelete = this.servicioRepository.findFirstByActivoTrueAndIdServicio(idServicio)
                .orElseThrow(() -> new IdNotFoundException(Tables.servicios.name(), idServicio));
        servicioToDelete.setActivo(false);
        this.servicioRepository.save(servicioToDelete);

    }
}
