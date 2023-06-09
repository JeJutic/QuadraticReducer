package org.panart.parser;

import org.panart.linear.DegreeVariable;
import org.panart.linear.Variable;

import java.text.ParseException;

public class DegreeVariablePartialParser extends BasePartialParser<DegreeVariable> {

    @Override
    public ParsedNext<DegreeVariable> parse(String str, int offset) throws ParseException {
        if (str.charAt(offset) != 'x') {
            throw exception("x symbol expected as a start of variable", offset);
        }
        offset++;
        var idNext = parseInt(str, offset);
        int id = idNext.parsed();
        offset = idNext.next();

        int degree;
        if (offset < str.length() && str.charAt(offset) == 'd') {
            offset++;
            var degreeNext = parseInt(str, offset);
            degree = degreeNext.parsed();
            offset = degreeNext.next();
        } else {
            degree = 1;
        }

        Variable variable = (degree == 0 ? null : new Variable(id));
        return new ParsedNext<>(new DegreeVariable(variable, degree), offset);
    }
}
