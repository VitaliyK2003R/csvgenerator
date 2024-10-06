package ru.komissarov;

import ru.komissarov.annotation.Attribute;
import ru.komissarov.annotation.CSV;
import ru.komissarov.exception.FieldAccessException;
import ru.komissarov.util.ReflectiveField;

import java.lang.reflect.Field;
import java.util.*;

public class CSVFieldParser extends AbstractAnnotationParser<CSV, Attribute> {
    private static final String NOT_INITIALIZED_PARSING_VALUE = " ";
    private final Reformer reformer;

    public CSVFieldParser(Reformer CSVAttributeReformer) {
        this.reformer = CSVAttributeReformer;
    }

    @Override
    public <T> Map<String,String> parse(T object) {
        Class<T> objectType = (Class<T>) object.getClass();
        checkParsingAnnotation(objectType, CSV.class);
        return parseFields(object);
    }

    private <T> Map<String,String> parseFields(T object) {
        Map<String, String> objectRelation = new HashMap<>();
        for (Field field: object.getClass().getDeclaredFields()) {
            if (haveAnnotationAttribute(field, Attribute.class)) {
                ReflectiveField reflectiveField = getReflectiveField(field, object);
                objectRelation.put(reflectiveField.getFieldName(), reformer.reform(reflectiveField.getFieldValue()));
            }
        }
        return objectRelation;
    }

    private <T> ReflectiveField getReflectiveField(Field field, T object) {
        field.setAccessible(true);
        String fieldName;
        String fieldValue;
        try {
            fieldName = field.getName();
            if (isAssignedAttributeValue(field)) {
                fieldValue = getAttributeValue(field);
            } else {
                if (field.get(object) == null) {
                    fieldValue = getAttributeValue(field);
                } else {
                    fieldValue = field.get(object).toString();
                }
            }
        } catch (IllegalAccessException ex) {
            throw new FieldAccessException();
        }
        field.setAccessible(false);
        return ReflectiveField.builder().fieldName(fieldName).fieldValue(fieldValue).build();
    }

    private String getAttributeValue(Field field) {
        return field.getAnnotation(Attribute.class).value();
    }

    private boolean isAssignedAttributeValue(Field field) {
        return !getAttributeValue(field).equals(NOT_INITIALIZED_PARSING_VALUE);
    }
}
