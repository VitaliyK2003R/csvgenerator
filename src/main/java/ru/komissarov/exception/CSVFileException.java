package ru.komissarov.exception;

public class CSVFileException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE = "csv file can not be written";

    public CSVFileException() {
        super(EXCEPTION_MESSAGE);
    }

    public CSVFileException(Throwable cause) {
        super(EXCEPTION_MESSAGE, cause);
    }
}
