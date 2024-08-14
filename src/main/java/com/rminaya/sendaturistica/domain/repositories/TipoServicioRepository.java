package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.TipoServicioEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TipoServicioRepository extends CrudRepository<TipoServicioEntity, Integer> {
    Optional<TipoServicioEntity> findFirstByActivoTrueAndIdTipoServicio(Integer idTipoServicio);
}
