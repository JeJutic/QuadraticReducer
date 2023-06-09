package org.panart.parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.panart.linear.DegreeVariable;
import org.panart.linear.Variable;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DegreeVariablePartialParserTest {

    @ParameterizedTest
    @MethodSource
    void parseTest(String str, DegreeVariable expected) {
        assertAll(
                () -> assertEquals(new ParsedNext<>(expected, str.length()),
                        new DegreeVariablePartialParser().parse(
                                str, 0
                        )),
                () -> assertEquals(new ParsedNext<>(expected, str.length() + 3),
                        new DegreeVariablePartialParser().parse(
                                "uuu" + str, 3
                        )),
                () -> assertEquals(new ParsedNext<>(expected, str.length() + 3),
                        new DegreeVariablePartialParser().parse(
                                "uu0" + str + "xtr", 3
                        ))
        );
    }

    private static Stream<Arguments> parseTest() {
        return Stream.of(
                Arguments.of("x1", new DegreeVariable(new Variable(1))),
                Arguments.of("x1d1", new DegreeVariable(new Variable(1))),
                Arguments.of("x3d2", new DegreeVariable(new Variable(3), 2)),
                Arguments.of("x3d-1", new DegreeVariable(new Variable(3), -1)),
                Arguments.of("x-3d-1", new DegreeVariable(new Variable(-3), -1))
        );
    }
}