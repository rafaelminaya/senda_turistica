package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.ServicioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ServicioRepository extends CrudRepository<ServicioEntity, Integer> {

    Optional<ServicioEntity> findFirstByActivoTrueAndIdServicio(Integer idServicio);

    List<ServicioEntity> findAllByActivoTrueAndPaqueteTuristico_IdPaqueteTuristico(Integer idPaqueteTuristico);
}
