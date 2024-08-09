package com.rminaya.sendaturistica.api.controllers.error_handler;

import com.rminaya.sendaturistica.api.models.responses.BaseErrorResponse;
import com.rminaya.sendaturistica.api.models.responses.ErrorResponse;
import com.rminaya.sendaturistica.util.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundController {

    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound(RuntimeException exception) {

        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .build();
    }
}
