package org.panart.parser;

import org.panart.linear.DegreeVariable;
import org.panart.linear.Polynomial;
import org.panart.linear.Term;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SimplePolynomialParser extends BaseParser implements PolynomialParser {

    @Override
    public Polynomial parse(String str) {
        return null;
    }

    private ParsedNext<Term> parseTerm(String str, int offset) throws ParseException {
        double coef;
        if (str.charAt(offset) == 'x') {
            coef = 1;
        } else {
            ParsedNext<Integer> parsedNext = parseInt(str, offset);
            coef = parsedNext.getParsed();
            offset = parsedNext.getNext();
        }
        List<DegreeVariable> degreeVariables = new ArrayList<>();
        while (offset < str.length()) {
            if (str.charAt(offset) != '+') {
                throw exception("+ expected", offset);
            }

        }
        return null;
    }


}
