package org.panart.parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.panart.linear.BasicTerm;
import org.panart.linear.DegreeVariable;
import org.panart.linear.Term;
import org.panart.linear.Variable;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TermPartialParserTest {

    @ParameterizedTest
    @MethodSource
    void parseTest(String str, Term expected) {
        assertAll(
                () -> assertEquals(new ParsedNext<>(expected, str.length()),
                        new TermPartialParser().parse(
                                str, 0
                        )),
                () -> assertEquals(new ParsedNext<>(expected, str.length() + 3),
                        new TermPartialParser().parse(
                                "uuu" + str, 3
                        )),
                () -> assertEquals(new ParsedNext<>(expected, str.length() + 3),
                        new TermPartialParser().parse(
                                "uu0" + str + "ytr", 3
                        ))
        );
    }

    private static Stream<Arguments> parseTest() {
        Term t2xy3 = new Term(2,
                new BasicTerm(
                List.of(
                        new DegreeVariable(new Variable(1)),
                        new DegreeVariable(new Variable(2), 3)))
        );
        Term xy2z4 = new Term(
                new BasicTerm(
                List.of(
                        new DegreeVariable(new Variable(1)),
                        new DegreeVariable(new Variable(2), 2),
                        new DegreeVariable(new Variable(3), 4)))
        );
        return Stream.of(
                Arguments.of("2x1d1x2d3", t2xy3),
                Arguments.of("x1x2d2x3d4", xy2z4)
        );
    }
}