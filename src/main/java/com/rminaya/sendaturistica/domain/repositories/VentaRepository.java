package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.VentaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VentaRepository extends CrudRepository<VentaEntity, Integer> {
    Optional<VentaEntity> findFirstByActivoTrueAndIdVenta(Integer idVenta);
}
