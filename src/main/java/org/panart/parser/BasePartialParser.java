package org.panart.parser;

import java.text.ParseException;

public abstract class BasePartialParser<T> implements PartialParser<T> {

    protected ParsedNext<Integer> parseInt(String str, int start) throws ParseException {
        int end = start + (str.charAt(start) == '-' ? 1 : 0);
        while (end < str.length() && Character.isDigit(str.charAt(end))) {
            end++;
        }
        if (start == end) {
            throw exception("No int found", start);
        }

        int res;
        try {
            res = Integer.parseInt(str.substring(start, end));
        } catch (NumberFormatException e) {
            throw exception(e.getMessage(), start);
        }
        return new ParsedNext<>(res, end);
    }

    protected ParseException exception(String s, int offset) {
        return new ParseException(s, offset);
    }
}
