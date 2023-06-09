package org.panart.parser;

import org.panart.linear.BasicTerm;
import org.panart.linear.Term;

import java.text.ParseException;

// TODO: parse double coef
public class TermPartialParser extends BasePartialParser<Term> {

    @Override
    public ParsedNext<Term> parse(String str, int offset) throws ParseException {
        double coef;
        if (str.charAt(offset) == 'x') {
            coef = 1;
        } else {
            var coefNext = parseInt(str, offset);
            offset = coefNext.next();
            coef = coefNext.parsed();
        }

        BasicTerm basicTerm;
        if (offset < str.length() && str.charAt(offset) == 'x') {
            var basicTermNext = new BasicTermPartialParser().parse(str, offset);
            offset = basicTermNext.next();
            basicTerm = basicTermNext.parsed();
        } else {
            basicTerm = BasicTerm.valueOfOne();
        }

        return new ParsedNext<>(new Term(coef, basicTerm), offset);
    }
}
