package com.rminaya.sendaturistica.util.annotations;

import com.rminaya.sendaturistica.api.models.requests.VentaRequest;
import com.rminaya.sendaturistica.util.exceptions.ServicioOpaqueteValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ServicioOpaqueteValidator implements ConstraintValidator<ServicioOpaqueteValid, VentaRequest> {

    @Override
    public boolean isValid(VentaRequest ventaRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (ventaRequest.getIdServicio() == null && ventaRequest.getIdPaqueteTuristico() == null) {
            throw new ServicioOpaqueteValidationException("Ambos 'idServicio' e 'idPaqueteTuristico' no pueden ser nulos al mismo tiempo.");
        }

        if (ventaRequest.getIdServicio() != null && ventaRequest.getIdPaqueteTuristico() != null) {
            throw new ServicioOpaqueteValidationException("Ambos 'idServicio' e 'idPaqueteTuristico' no pueden estar presentes al mismo tiempo.");
        }
        return true;
    }
}
