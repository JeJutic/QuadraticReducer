package org.panart.parser;

import java.text.ParseException;

public interface PartialParser<T> {

    ParsedNext<? extends T> parse(String str, int offset) throws ParseException;
}
