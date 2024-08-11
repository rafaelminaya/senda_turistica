package com.rminaya.sendaturistica.util.exceptions;

public class IdNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "No se encontró %s con el id %d";

    public IdNotFoundException(String tableName, Integer id) {
        super(String.format(ERROR_MESSAGE, tableName, id));
    }
}
