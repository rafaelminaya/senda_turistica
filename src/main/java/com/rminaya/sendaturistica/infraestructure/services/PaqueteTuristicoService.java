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
import java.util.stream.Collectors;

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
        // Obtenemos los IDs de servicios
        List<Integer> idsServicio = paqueteTuristicoRequest
                .getServicios()
                .stream()
                .map(PaqueteTuristicoServicioRequest::getId)
                .toList();
        // Buscamos los servicios de los IDs obtenidos
        List<ServicioEntity> servicios = (List<ServicioEntity>) this.servicioRepository.findAllById(idsServicio);

        // Creamos y persistimos el nuevo paquete turistico
        PaqueteTuristicoEntity paqueteTuristicoToPersit = PaqueteTuristicoEntity.builder()
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
        // Recolectamos los servicios IDs
        List<Integer> idsServicios = paqueteTuristicoRequest
                .getServicios()
                .stream()
                .map(PaqueteTuristicoServicioRequest::getId)
                .collect(Collectors.toList());
        // Buscamos el paquete a actualizar
        PaqueteTuristicoEntity paqueteToUpdate = this.paqueteTuristicoRepository
                .findById(idPaqueteTuristico)
                .orElseThrow(() -> new IdNotFoundException(Tables.paquetes_turisticos.name(), idPaqueteTuristico));
        // Buscamos los nuevos servicios del paquete
        List<ServicioEntity> servicios = (List<ServicioEntity>) this.servicioRepository.findAllById(idsServicios);
        // Eliminamos los antiguos servicios al paquete actual
        // Agregamos los nuevos servicios al paquete actual
        paqueteToUpdate.actualizarServicios(servicios);
        paqueteToUpdate.setServicios(servicios);
        paqueteToUpdate.setCostoPaquete(paqueteToUpdate.calcularCostoPaquete(servicios));

        PaqueteTuristicoEntity paqueteTuristicoUpdated = this.paqueteTuristicoRepository.save(paqueteToUpdate);

        return this.paqueteTuristicoMapper.toPaqueteTuristico(paqueteTuristicoUpdated);
    }

    @Override
    @Transactional
    public void delete(Integer idPaqueteTuristico) {
        // Buscamos el paquete turistico a eliminar
        PaqueteTuristicoEntity paqueteToDeleted = this.paqueteTuristicoRepository
                .findById(idPaqueteTuristico)
                .orElseThrow(() -> new IdNotFoundException(Tables.paquetes_turisticos.name(), idPaqueteTuristico));
        // Eliminamos los actuales servicios del paquete turistico
        paqueteToDeleted.removeAllServicios();
        // Actualizamos el estado del paquete turistico
        paqueteToDeleted.setActivo(false);

        this.paqueteTuristicoRepository.save(paqueteToDeleted);
    }
}
