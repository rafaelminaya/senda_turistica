package com.rminaya.sendaturistica.infraestructure.services;

import com.rminaya.sendaturistica.api.models.requests.PaqueteTuristicoRequest;
import com.rminaya.sendaturistica.api.models.requests.PaqueteTuristicoServicioRequest;
import com.rminaya.sendaturistica.api.models.responses.PaqueteTuristicoResponse;
import com.rminaya.sendaturistica.domain.entities.PaqueteTuristicoEntity;
import com.rminaya.sendaturistica.domain.entities.ServicioEntity;
import com.rminaya.sendaturistica.domain.repositories.PaqueteTuristicoRepository;
import com.rminaya.sendaturistica.domain.repositories.ServicioRepository;
import com.rminaya.sendaturistica.infraestructure.abstract_services.IPaqueteTuristicoService;
import com.rminaya.sendaturistica.infraestructure.mappers.PaqueteTuristicoMapper;
import com.rminaya.sendaturistica.util.enums.Tables;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaqueteTuristicoService implements IPaqueteTuristicoService {

    private final PaqueteTuristicoRepository paqueteTuristicoRepository;
    private final ServicioRepository servicioRepository;

    @Autowired
    PaqueteTuristicoMapper paqueteTuristicoMapper;

    @Override
    @Transactional
    public PaqueteTuristicoResponse create(PaqueteTuristicoRequest paqueteTuristicoRequest) {

        List<Integer> idsServicio = paqueteTuristicoRequest
                .getServicios()
                .stream()
                .map(PaqueteTuristicoServicioRequest::getId)
                .toList();
        // buscamos los servicios
        List<ServicioEntity> servicios = (List<ServicioEntity>) this.servicioRepository.findAllById(idsServicio);

        PaqueteTuristicoEntity paqueteTuristicoToPersit = PaqueteTuristicoEntity.builder()
                .costoPaquete(1000.0)
                .servicios(servicios)
                .build();

        PaqueteTuristicoEntity paqueteTuristicoPersisted = this.paqueteTuristicoRepository.save(paqueteTuristicoToPersit);

        return this.paqueteTuristicoMapper.toPaqueteTuristico(paqueteTuristicoPersisted);
    }

    @Override
    @Transactional(readOnly = true)
    public PaqueteTuristicoResponse read(Integer idPaqueteTuristico) {
        PaqueteTuristicoEntity paqueteTuristicoFromDB = this.paqueteTuristicoRepository
                .findFirstByActivoTrueAndIdPaqueteTuristico(idPaqueteTuristico)
                .orElseThrow(() -> new IdNotFoundException(Tables.paquetes_turisticos.name(), idPaqueteTuristico));

        return this.paqueteTuristicoMapper.toPaqueteTuristico(paqueteTuristicoFromDB);
    }

    @Override
    @Transactional
    public PaqueteTuristicoResponse update(PaqueteTuristicoRequest paqueteTuristicoRequest, Integer idPaqueteTuristico) {

        List<Integer> idsServicios = paqueteTuristicoRequest
                .getServicios()
                .stream()
                .map(servicio -> servicio.getId())
                .toList();
        // Eliminamos los actuales servicios del paquete turistico
        PaqueteTuristicoEntity paqueteToUpdate = this.paqueteTuristicoRepository.findById(idPaqueteTuristico)
                .orElseThrow(() -> new IdNotFoundException(Tables.paquetes_turisticos.name(), idPaqueteTuristico));

        List<ServicioEntity> paqueteTuristicoServicios = this.servicioRepository
                .findAllByActivoTrueAndPaqueteTuristico_IdPaqueteTuristico(paqueteToUpdate.getIdPaqueteTuristico());

        paqueteTuristicoServicios.forEach(servicio -> {
            servicio.setPaqueteTuristico(null);
            this.servicioRepository.save(servicio);
        });
        // Buscamos los nuevos servicios
        List<ServicioEntity> servicios = (List<ServicioEntity>) this.servicioRepository.findAllById(idsServicios);
        // Agreagmos los nuevos servicios al paquete actual
        paqueteToUpdate.setCostoPaquete(2000.0);
        paqueteToUpdate.setServicios(servicios);

        PaqueteTuristicoEntity paqueteTuristicoUpdated = this.paqueteTuristicoRepository.save(paqueteToUpdate);

        return this.paqueteTuristicoMapper.toPaqueteTuristico(paqueteTuristicoUpdated);
    }

    @Override
    @Transactional
    public void delete(Integer idPaqueteTuristico) {
        // Eliminamos los actuales servicios del paquete turistico
        PaqueteTuristicoEntity paqueteToDeleted = this.paqueteTuristicoRepository.findById(idPaqueteTuristico)
                .orElseThrow(() -> new IdNotFoundException(Tables.paquetes_turisticos.name(), idPaqueteTuristico));

        List<ServicioEntity> paqueteTuristicoServicios = this.servicioRepository
                .findAllByActivoTrueAndPaqueteTuristico_IdPaqueteTuristico(paqueteToDeleted.getIdPaqueteTuristico());

        paqueteTuristicoServicios.forEach(servicio -> {
            servicio.setPaqueteTuristico(null);
            this.servicioRepository.save(servicio);
        });
        // Actualizamos el estado del paquete turistico
        paqueteToDeleted.setActivo(false);
        this.paqueteTuristicoRepository.save(paqueteToDeleted);
    }
}
