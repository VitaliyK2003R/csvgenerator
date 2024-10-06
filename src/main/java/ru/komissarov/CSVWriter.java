package ru.komissarov;

import java.util.Collection;

public interface CSVWriter {
    <T> void writeData(Collection<T> data, String path);
}
