package ru.komissarov.exception;

public class FieldCanNotBeParsedException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Field can not be parsed";
    public FieldCanNotBeParsedException() {
        super(EXCEPTION_MESSAGE);
    }
}
