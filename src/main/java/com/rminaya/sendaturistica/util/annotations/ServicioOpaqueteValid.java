package com.rminaya.sendaturistica.util.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ServicioOpaqueteValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ServicioOpaqueteValid {
    String message() default "Solo uno de los atributos idServicio o idPaqueteTuristico puede ser nulo, pero no ambos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
