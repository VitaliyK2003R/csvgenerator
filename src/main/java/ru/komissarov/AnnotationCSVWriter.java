package ru.komissarov;

import ru.komissarov.exception.CSVFileException;
import ru.komissarov.util.FieldView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AnnotationCSVWriter implements CSVWriter {
    private static final String DEFAULT_COMMA_SEPARATOR = ",";
    private static final String NEXT_LINE_SYMBOL = "\n";
    private static final String CSV_FILE_EXTENSION = ".csv";
    private final String csvSource;
    private final FieldParser fieldParser;
    private final String separator = DEFAULT_COMMA_SEPARATOR;

    public AnnotationCSVWriter(FieldParser fieldParser, String fileSource) {
        this.fieldParser = fieldParser;
        csvSource = fileSource.trim();
    }

    @Override
    public <T> void writeData(Collection<T> data, String fileName) {
        try (FileWriter fileWriter = new FileWriter(wrapCSVSourcePath(fileName))) {
            fileWriter.write(prepareFileView(data));
        } catch (IOException ex) {
            throw new CSVFileException(ex);
        }
    }

    private String wrapCSVSourcePath(String fileName) {
        return csvSource + fileName.trim() + CSV_FILE_EXTENSION;
    }

    private <T> String prepareFileView(Collection<T> data) {
        StringBuilder fileView = new StringBuilder();
        List<FieldView> sortedAttributes = prepareFieldViews(fieldParser.parseObjects(data));
        fileView.append(generateAttributeNamesLine(sortedAttributes));
        for (int indexValue = 0; indexValue < sortedAttributes.get(0).getValueSize(); indexValue++) {
            Iterator<FieldView> iterator = sortedAttributes.iterator();
            fileView.append(iterator.next().getValue(indexValue));
            while (iterator.hasNext()) {
                fileView.append(separator).append(iterator.next().getValue(indexValue));
            }
            fileView.append(NEXT_LINE_SYMBOL);
        }
        return fileView.toString();
    }

    private String generateAttributeNamesLine(List<FieldView> sortedAttributes) {
        StringBuilder attributeNamesLine = new StringBuilder();
        Iterator<FieldView> iterator = sortedAttributes.iterator();
        attributeNamesLine.append(iterator.next().getName());
        while (iterator.hasNext()) {
            attributeNamesLine.append(separator).append(iterator.next().getName());
        }
        return attributeNamesLine.append(NEXT_LINE_SYMBOL).toString();
    }

    private List<FieldView> prepareFieldViews(Map<String,List<String>> sortedAttributes) {
        List<FieldView> views = new ArrayList<>();
        for (Map.Entry<String,List<String>> attributeView: sortedAttributes.entrySet()) {
            FieldView view = new FieldView(attributeView.getKey(), attributeView.getValue());
            views.add(view);
        }
        return views;
    }
}
