package com.rminaya.sendaturistica.domain.repositories;

import com.rminaya.sendaturistica.domain.entities.EmpleadoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<EmpleadoEntity, Integer> {
    Optional<EmpleadoEntity> findFirstByActivoTrueAndIdCliente(Integer idEmpleado);

}
