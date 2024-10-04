package ru.komissarov.exception;

public class ParsingNotSupportedException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "Parsing is not supported";
    public ParsingNotSupportedException() {
        super(EXCEPTION_MESSAGE);
    }
}
