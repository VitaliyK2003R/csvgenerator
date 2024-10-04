package ru.komissarov;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FieldParser {
    <T> Map<String,String> parse(T object);
    <T> Map<String, List<String>> parseObjects(Collection<T> objects);
}
