package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.PaqueteTuristicoEntity;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PaqueteTuristicoRepository extends CrudRepository<PaqueteTuristicoEntity, Integer> {
    Optional<PaqueteTuristicoEntity> findFirstByActivoTrueAndIdPaqueteTuristico(Integer idPaqueteTuristico);
}
