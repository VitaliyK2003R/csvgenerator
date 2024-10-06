package ru.komissarov.util;

import ru.komissarov.exception.FieldCanNotBeParsedException;

import java.util.List;

public class FieldView {
    private final String name;
    private final List<String> values;

    public FieldView(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }

    public String getValue(int index) {
        try {
            return values.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new FieldCanNotBeParsedException();
        }
    }

    public int getValueSize() {
        try {
            return values.size();
        } catch (NullPointerException ex) {
            throw new FieldCanNotBeParsedException();
        }
    }

    public String getName() {
        return name;
    }
}
