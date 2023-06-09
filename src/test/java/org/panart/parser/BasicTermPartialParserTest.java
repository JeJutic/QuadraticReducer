package org.panart.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicTermPartialParserTest {

    @Test
    void parse() {
        assertAll(
                () -> assertEquals(4, new BasicTermPartialParser().parse(
                        "x1x3", 0
                ).next())
        );
    }
}