import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.komissarov.CSVFieldParser;
import ru.komissarov.Reformer;
import ru.komissarov.annotation.CSV;
import ru.komissarov.application.Person;
import ru.komissarov.exception.ParsingNotSupportedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CSVFieldParserTest {
    @InjectMocks
    private CSVFieldParser parser;
    @Mock
    private Reformer reformer;
    private static String reformingValue;
    private static String reformedValue;

    @BeforeAll
    public static void setUp() {
        reformingValue = "\"value\"";
        reformedValue = "\"\"value\"\"";
    }

    @Test
    public void parseClassWithValidAnnotationTest() {
        Person person = new Person(reformingValue,reformingValue);

        when(reformer.reform(reformingValue)).thenReturn(reformedValue);

        assertDoesNotThrow(() -> parser.parse(person));
    }

    @Test
    public void parseClassWithoutValidAnnotationTest() {
        class InvalidClass {
            private String value;

            public InvalidClass(String value) {
                this.value = value;
            }
        }
        InvalidClass invalidClass = new InvalidClass(reformedValue);

        assertThrows(ParsingNotSupportedException.class, () -> parser.parse(invalidClass));
    }

    @Test
    public void parseClassWithoutAttributeAnnotations() {
        @CSV
        class ParsingClass {
            private String value;

            public ParsingClass(String value) {
                this.value = value;
            }
        }
        ParsingClass parsingClass = new ParsingClass(reformingValue);

        assertEquals(0, parser.parse(parsingClass).size());
    }
}
