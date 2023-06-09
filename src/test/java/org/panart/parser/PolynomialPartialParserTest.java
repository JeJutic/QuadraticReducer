package org.panart.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialPartialParserTest {

    @Test
    void parse() {
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
//                () -> assertEquals("4.00x1d2+5.00x2",
//                        new PolynomialPartialParser().parse(
//                                "4x1d2+5x2"
//                        ).format()),
                () -> assertEquals("",
                        new PolynomialPartialParser().parse(
                                "9x1d2-12x1x2-6x1x3+4x2d2+4x2x3+x3d2"
                        ).format())
        );
    }
}