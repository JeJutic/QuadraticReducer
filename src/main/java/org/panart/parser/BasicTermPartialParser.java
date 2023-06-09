package org.panart.parser;

import org.panart.linear.BasicTerm;
import org.panart.linear.DegreeVariable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class BasicTermPartialParser extends BasePartialParser<BasicTerm> {

    @Override
    public ParsedNext<BasicTerm> parse(String str, int offset) throws ParseException {
        if (str.charAt(offset) != 'x') {
            return new ParsedNext<>(
                    new BasicTerm(List.of(DegreeVariable.valueOfOne())), offset
            );
        }

        List<DegreeVariable> degreeVariables = new ArrayList<>();
        while (offset < str.length() && str.charAt(offset) == 'x') {
            var degreeVariableNext = new DegreeVariablePartialParser().parse(str, offset);
            degreeVariables.add(degreeVariableNext.parsed());
            offset = degreeVariableNext.next();
        }

        return new ParsedNext<>(new BasicTerm(degreeVariables), offset);
    }
}
