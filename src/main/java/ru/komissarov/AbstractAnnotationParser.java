package ru.komissarov;

import ru.komissarov.exception.ParsingNotSupportedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public abstract class AbstractAnnotationParser<C extends Annotation, F extends Annotation> implements FieldParser {
    public <T> Map<String, List<String>> parseObjects(Collection<T> objects) {
        Map<String,List<String>> sortedAttributes = new HashMap<>();
        for (T elem: objects) {
            addAttributes(sortedAttributes, parse(elem));
        }
        return sortedAttributes;
    }

    private void addAttributes(Map<String,List<String>> sortedAttributes, Map<String, String> attributes) {
        List<String> attributeValues;
        for (Map.Entry<String,String> attribute: attributes.entrySet()) {
            if ((attributeValues = sortedAttributes.get(attribute.getKey())) == null) {
                attributeValues = new ArrayList<>();
                sortedAttributes.put(attribute.getKey(), attributeValues);
            }
            attributeValues.add(attribute.getValue());
        }
    }

    protected <T> void checkParsingAnnotation(Class<T> object, Class<C> classParsingAnnotation) {
        if (object.getAnnotation(classParsingAnnotation) == null) {
            throw new ParsingNotSupportedException();
        }
    }

    protected boolean haveAnnotationAttribute(Field field, Class<F> fieldParsingAnnotation) {
        return field.getAnnotation(fieldParsingAnnotation) != null;
    }
}
