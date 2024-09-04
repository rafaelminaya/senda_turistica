package com.rminaya.sendaturistica.api.controllers.error_handler;

import com.rminaya.sendaturistica.api.models.responses.BaseErrorResponse;
import com.rminaya.sendaturistica.api.models.responses.ErrorResponse;
import com.rminaya.sendaturistica.api.models.responses.ErrorsResponse;
import com.rminaya.sendaturistica.util.exceptions.ServicioOpaqueteValidationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    // Control para las excepciones @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class) // anotación que intercepta la excepción indicada
    public BaseErrorResponse handleValidationExceptions(MethodArgumentNotValidException exception) {
        // listado que contendrá los errores obtenidos de la excepción "MethodArgumentNotValidException"
        var errors = new ArrayList<String>();
        // Obtenemos todos los errores y los iteramos
        exception.getBindingResult()
                .getFieldErrors() // Obtenemos todos los errores
                .forEach(error -> errors.add("El campo '" + error.getField() + "' " + error.getDefaultMessage())); // iteramos y agregamos cada error al listado


        // Devolvemos una instancia de "ErrorsResponse" que contiene una lista de errores.
        // "ErrorsResponse" es hijo de la clase "BaseErrorResponse" a retornar.
        return ErrorsResponse.builder()
                .errors(errors) // asignamos la lista de errores
                .status(HttpStatus.BAD_REQUEST.name()) // "BAD_REQUEST"
                .code(HttpStatus.BAD_REQUEST.value()) // 400
                .build();
    }

    // Control para las excepciones @Valid
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseErrorResponse handleConstraintViolationException(ConstraintViolationException exception) {

        List<String> errors = new ArrayList<>();
        // Obtenemos todos los errores y los iteramos
        exception.getConstraintViolations() // Obtenemos todos los errores
                .forEach(cv -> errors.add("El campo '" + cv.getPropertyPath().toString() + "' " + cv.getMessage())); // iteramos y agregamos cada error al listado

        // Devolvemos una instancia de "ErrorsResponse" que contiene una lista de errores.
        // "ErrorsResponse" es hijo de la clase "BaseErrorResponse" a retornar.
        return ErrorsResponse.builder()
                .errors(errors) // asignamos la lista de errores
                .status(HttpStatus.BAD_REQUEST.name()) // "BAD_REQUEST"
                .code(HttpStatus.BAD_REQUEST.value()) // 400
                .build();
    }
    // Control para las excepciones en los @PathVariable de los endpoint, como los IDs
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HandlerMethodValidationException.class})
    public BaseErrorResponse handleArgument(RuntimeException exception) {
        String error = "El parámetro 'id' debe ser un número entero positivo";
        return ErrorResponse.builder()
                .error(error)
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(ServicioOpaqueteValidationException.class)
    public BaseErrorResponse handleServicioOpaqueteValidationException(ServicioOpaqueteValidationException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }

}
