package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.ClienteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<ClienteEntity, Integer> {
    Optional<ClienteEntity> findByActivoTrueAndIdCliente(Integer id);
}
