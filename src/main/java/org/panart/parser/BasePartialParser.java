package org.panart.parser;

import java.text.ParseException;

public abstract class BasePartialParser<T> implements PartialParser<T> {

    protected ParsedNext<Integer> parseInt(String str, int start) throws ParseException {
        int end = start;
        while (end < str.length() && Character.isDigit(str.charAt(end))) {
            end++;
        }
        if (start == end) {
            throw new ParseException("No int found", start);
        }
        return new ParsedNext<>(Integer.parseInt(str.substring(start, end)), end);
    }

    protected ParseException exception(String s, int offset) {
        return new ParseException(s, offset);
    }
}
