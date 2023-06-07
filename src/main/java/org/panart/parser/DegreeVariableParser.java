package org.panart.parser;

import org.panart.linear.DegreeVariable;
import org.panart.linear.Variable;

import java.text.ParseException;

// TODO: parse double coef
public class DegreeVariableParser extends BaseParser implements PartialParser<DegreeVariable> {

    @Override
    public ParsedNext<DegreeVariable> parse(String str, int offset) throws ParseException {
        if (str.charAt(offset) != 'x') {
            throw exception("x symbol expected as a start of variable", offset);
        }
        offset++;
        var idNext = parseInt(str, offset);
        int id = idNext.getNext();
        offset = idNext.getNext();

        if (str.charAt(offset) != 'd') {
            throw exception("d symbol expected as a start of pow part", offset);
        }
        offset++;
        var degreeNext = parseInt(str, offset);
        int degree = degreeNext.getNext();
        offset = degreeNext.getNext();

        return new ParsedNext<>(new DegreeVariable(new Variable(id), degree), offset);
    }
}
