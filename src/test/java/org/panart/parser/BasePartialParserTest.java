package org.panart.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasePartialParserTest {

    private static final BasePartialParser<?> basePartialParser = new BasePartialParser<>() {
        @Override
        public ParsedNext<Object> parse(String str, int offset) {
            return null;
        }
    };

    @Test
    void parseInt() {
        assertAll(
                () -> assertEquals(new ParsedNext<>(12, 2),
                        basePartialParser.parseInt("12", 0)),
                () -> assertEquals(new ParsedNext<>(89, 2),
                        basePartialParser.parseInt("89", 0)),
                () -> assertEquals(new ParsedNext<>(12, 3),
                        basePartialParser.parseInt("112", 1)),
                () -> assertEquals(new ParsedNext<>(12, 3),
                        basePartialParser.parseInt("112x", 1)),
                () -> assertEquals(new ParsedNext<>(-12, 4),
                        basePartialParser.parseInt("1-12x", 1))
        );
    }

    @Test
    void exception() {
    }
}