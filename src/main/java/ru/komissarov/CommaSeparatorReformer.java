package ru.komissarov;

public class CommaSeparatorReformer implements Reformer {
    private static final String NEXT_LINE_SYMBOL = "\n";
    private static final String COMMA_SYMBOL = ",";
    private static final String DOUBLE_QUOTES_SYMBOL = "\"";
    private static final String[] TWICE_BOUNDED_SERVICE_CHARACTERS = new String[] { COMMA_SYMBOL, DOUBLE_QUOTES_SYMBOL};

    @Override
    public String reform(String value) {
        return representTwiceBoundedServiceCharacters(value);
    }

    private String representTwiceBoundedServiceCharacters(String value) {
        value = representNexLineSymbol(value);
        value = representDoubleQuotes(value);
        StringBuilder representation = new StringBuilder(value);
        for (String character: TWICE_BOUNDED_SERVICE_CHARACTERS) {
            if (value.contains(character)) {
                boundByQuotes(representation);
                break;
            }
        }
        return representation.toString();
    }

    private String representDoubleQuotes(String value) {
        if (value.contains(DOUBLE_QUOTES_SYMBOL)) {
            value = value.replaceAll(DOUBLE_QUOTES_SYMBOL,DOUBLE_QUOTES_SYMBOL + DOUBLE_QUOTES_SYMBOL);
        }
        return value;
    }

    private String representNexLineSymbol(String value) {
        if (value.contains(NEXT_LINE_SYMBOL)) {
            value = value.replaceAll(NEXT_LINE_SYMBOL, "\\\\n");
        }
        return value;
    }

    private void boundByQuotes(StringBuilder representation) {
        representation.insert(0, DOUBLE_QUOTES_SYMBOL)
                        .insert(representation.toString().length(), DOUBLE_QUOTES_SYMBOL);
    }
}
