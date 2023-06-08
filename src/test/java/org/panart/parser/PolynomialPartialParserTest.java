package org.panart.parser;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialPartialParserTest {

    @Test
    void parse() throws ParseException {
//        assertAll(
//                () -> assertEquals(new ParsedNext<>(12, 2),
//                        new PolynomialPartialParser("12", 0)),
//                () -> assertEquals(new ParsedNext<>(89, 2),
//                        new PolynomialPartialParser("89", 0)),
//                () -> assertEquals(new ParsedNext<>(12, 3),
//                        new PolynomialPartialParser("112", 1)),
//                () -> assertEquals(new ParsedNext<>(12, 3),
//                        new PolynomialPartialParser("112x", 1))
//        );TODO
        assertAll(
                () -> assertEquals("4.00x1d2+5.00x2",
                        new PolynomialPartialParser().parse(
                                "4x1d2+5x2"
                        ).format())
        );
    }
}