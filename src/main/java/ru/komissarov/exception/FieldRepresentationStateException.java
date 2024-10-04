package ru.komissarov.exception;

public class FieldRepresentationStateException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Line must be ended with \\n symbol";
    public FieldRepresentationStateException() {
        super(EXCEPTION_MESSAGE);
    }
}
