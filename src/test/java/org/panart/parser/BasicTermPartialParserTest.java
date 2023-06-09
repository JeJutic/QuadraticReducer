package org.panart.parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.panart.linear.BasicTerm;
import org.panart.linear.DegreeVariable;
import org.panart.linear.Variable;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BasicTermPartialParserTest {

    @ParameterizedTest
    @MethodSource
    void parseTest(String str, BasicTerm expected) {
        assertAll(
                () -> assertEquals(new ParsedNext<>(expected, str.length()),
                        new BasicTermPartialParser().parse(
                            str, 0
                        )),
                () -> assertEquals(new ParsedNext<>(expected, str.length() + 3),
                        new BasicTermPartialParser().parse(
                                "uuu" + str, 3
                        )),
                () -> assertEquals(new ParsedNext<>(expected, str.length() + 3),
                        new BasicTermPartialParser().parse(
                                "uu0" + str + "ytr", 3
                        ))
        );
    }

    private static Stream<Arguments> parseTest() {
        return Stream.of(
                Arguments.of("x1x3", new BasicTerm(List.of(
                        new DegreeVariable(new Variable(1)), new DegreeVariable(new Variable(3))
                ))),
                Arguments.of("x3x1", new BasicTerm(List.of(
                        new DegreeVariable(new Variable(1)), new DegreeVariable(new Variable(3))
                ))),
                Arguments.of("x1d2x3d0", new BasicTerm(List.of(
                        new DegreeVariable(new Variable(1), 2), new DegreeVariable(null, 0)
                )))
        );
    }
}