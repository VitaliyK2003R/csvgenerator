import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.komissarov.AnnotationCSVWriter;
import ru.komissarov.FieldParser;
import ru.komissarov.annotation.Attribute;
import ru.komissarov.annotation.CSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnnotationCSVWriterTest {
    private static final String CSV_SOURCE = "./";
    private AnnotationCSVWriter writer;
    @Mock
    private FieldParser fieldParser;
    private static final String CSV_FILE_NAME = "testFile";
    private static List<TestClass> parsingTestList;
    private static Map<String,List<String>> parsedTestList;

    @BeforeAll
    public static void setUp() {
        TestClass elem1 = new TestClass("value1");
        TestClass elem2 = new TestClass("value2");
        parsingTestList = new ArrayList<>();
        parsingTestList.add(elem1);
        parsingTestList.add(elem2);

        List<String> fieldValues = new ArrayList<>();
        fieldValues.add("value1");
        fieldValues.add("value2");
        parsedTestList = new HashMap<>();
        parsedTestList.put("value", fieldValues);
    }

    @AfterEach
    public void deleteTestingCSVFile() {
        File testingFile = new File(wrapCSVSourcePath());
        testingFile.deleteOnExit();
    }

    @Test
    public void validWritingDataTest() {
        writer = new AnnotationCSVWriter(fieldParser, CSV_SOURCE);

        when(fieldParser.parseObjects(parsingTestList)).thenReturn(parsedTestList);

        writer.writeData(parsingTestList, CSV_FILE_NAME);

        assertEquals("value\nvalue1\nvalue2\n", getCSVFileAsString());
    }

    private String getCSVFileAsString() {
        StringBuilder csv = new StringBuilder();
        String fileLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(wrapCSVSourcePath()))) {
            while ((fileLine = reader.readLine()) != null) {
                csv.append(fileLine).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return csv.toString();
    }

    private String wrapCSVSourcePath() {
        return CSV_SOURCE + CSV_FILE_NAME.trim() + ".csv";
    }

    @CSV
    private static class TestClass {
        @Attribute
        private String value;

        public TestClass(String value) {
            this.value = value;
        }
    }
}
