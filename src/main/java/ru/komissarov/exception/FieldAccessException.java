package ru.komissarov.exception;

public class FieldAccessException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Field can not be parsed";

    public FieldAccessException() {
        super(EXCEPTION_MESSAGE);
    }
}
