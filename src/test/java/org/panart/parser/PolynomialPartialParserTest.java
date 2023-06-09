package org.panart.parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.panart.linear.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialPartialParserTest {

    @ParameterizedTest
    @MethodSource
    void parseTest(String str, Polynomial expected) throws ParseException {
        Polynomial cur = new PolynomialPartialParser().parse(str);
        assertAll(
                () -> assertEquals(expected, cur),
                () -> assertEquals(expected,
                        new PolynomialPartialParser().parse(
                                str, 0
                        ).parsed()),
                () -> assertEquals(expected,
                        new PolynomialPartialParser().parse(
                                "uuu" + str, 3
                        ).parsed())
        );
    }

    private static Stream<Arguments> parseTest() {
        Polynomial t2xy3 = new Polynomial(new Term(2,
                new BasicTerm(
                        List.of(
                                new DegreeVariable(new Variable(1)),
                                new DegreeVariable(new Variable(2), 3)))
        ));
        Polynomial xy2z4 = new Polynomial(new Term(
                new BasicTerm(
                        List.of(
                                new DegreeVariable(new Variable(1)),
                                new DegreeVariable(new Variable(2), 2),
                                new DegreeVariable(new Variable(3), 4)))
        ));
        Polynomial t6y3z = new Polynomial(new Term(6,
                new BasicTerm(
                        List.of(
                                new DegreeVariable(new Variable(2), 3),
                                new DegreeVariable(new Variable(3))))
        ));
        Polynomial number = new Polynomial(new Term(BasicTerm.valueOfOne()));

        return Stream.of(
                Arguments.of("2x1d1x2d3+x1x2d2x3d4", t2xy3.add(xy2z4)),
                Arguments.of("x1x2d2x3d4+2x1x2d3", t2xy3.add(xy2z4)),
                Arguments.of("2x1d1x2d3+-1x1x2d2x3d4", t2xy3.add(xy2z4.lambda(-1))),
                Arguments.of("2x1d1x2d3+-1x1x2d2x3d4+6x2d3x3",
                        t2xy3.add(xy2z4.lambda(-1)).add(t6y3z)),
                Arguments.of("2+2x1d1x2d3+3",
                        number.lambda(2).add(t2xy3).add(number.lambda(3)))
        );
    }
}