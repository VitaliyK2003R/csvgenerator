import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.komissarov.CommaSeparatorReformer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommaSeparatorReformerTest {
    @InjectMocks
    private CommaSeparatorReformer reformer;
    private static String reformingWordWithComma;
    private static String reformedWordWithComma;
    private static String reformingWordWithQuotes;
    private static String reformedWordWithQuotes;
    private static String reformingWordWithNextLineSymbol;
    private static String reformedWordWithNextLineSymbol;

    @BeforeAll
    public static void setUp() {
        reformingWordWithComma = "va,lue";
        reformedWordWithComma = "\"va,lue\"";
        reformingWordWithQuotes = "\"value\"";
        reformedWordWithQuotes = "\"\"\"value\"\"\"";
        reformingWordWithNextLineSymbol = "value\n";
        reformedWordWithNextLineSymbol = "value\\n";
    }

    @Test
    public void reformDoubleQuotesTest() {
        assertEquals(reformedWordWithQuotes, reformer.reform(reformingWordWithQuotes));
    }

    @Test
    public void reformWordWithCommaTest() {
        assertEquals(reformedWordWithComma, reformer.reform(reformingWordWithComma));
    }

    @Test
    public void reformWordWithNextLineSymbolTest() {
        assertEquals(reformedWordWithNextLineSymbol, reformer.reform(reformingWordWithNextLineSymbol));
    }
}
