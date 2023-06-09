package org.panart.parser;

import org.junit.jupiter.api.Test;
import org.panart.linear.BasicTerm;
import org.panart.linear.DegreeVariable;
import org.panart.linear.Term;
import org.panart.linear.Variable;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TermPartialParserTest {

    @Test
    void parse() throws ParseException {
        Term t2xy2 = new Term(
                new BasicTerm(
                        List.of(
                                new DegreeVariable(new Variable(1), 1),
                                new DegreeVariable(new Variable(2), 3))),
                2);
        assertAll(
                () -> assertEquals(new ParsedNext<>(t2xy2, 9),
                        new TermPartialParser().parse("2x1d1x2d3", 0)),
                () -> assertEquals(new ParsedNext<>(t2xy2, 10),
                        new TermPartialParser().parse("abc2x1x2d3z", 3))
        );
        assertEquals(6, new TermPartialParser().parse(
                "-6x1x3", 0
        ).next());
    }
}