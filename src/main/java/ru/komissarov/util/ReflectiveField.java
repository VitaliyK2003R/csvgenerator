package ru.komissarov.util;

import java.util.Objects;

public class ReflectiveField {
    private String fieldName;
    private String fieldValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectiveField that = (ReflectiveField) o;
        return Objects.equals(fieldName, that.fieldName) && Objects.equals(fieldValue, that.fieldValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, fieldValue);
    }

    @Override
    public String toString() {
        if (fieldName == null) {
            throw new IllegalStateException();
        }
        return fieldName + "=" + (fieldValue == null ? "null" : fieldValue);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ReflectiveField reflectiveField;

        public Builder() {
            this.reflectiveField = new ReflectiveField();
        }

        public Builder fieldName(String fieldName) {
            reflectiveField.setFieldName(fieldName);
            return this;
        }

        public Builder fieldValue(String fieldValue) {
            reflectiveField.setFieldValue(fieldValue);
            return this;
        }

        public ReflectiveField build() {
            return reflectiveField;
        }
    }
}
