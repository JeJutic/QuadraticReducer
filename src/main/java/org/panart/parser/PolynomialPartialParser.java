package org.panart.parser;

import org.panart.linear.Polynomial;
import org.panart.linear.Term;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PolynomialPartialParser extends BasePartialParser<Polynomial> implements PolynomialParser {

    @Override
    public Polynomial parse(String str) throws ParseException {
        return parse(str, 0).parsed();
    }

    @Override
    public ParsedNext<Polynomial> parse(String str, int offset) throws ParseException {
        List<Term> terms = new ArrayList<>();
        while (offset < str.length()) {
            var termNext = new TermPartialParser().parse(str, offset);
            offset += termNext.next();
            terms.add(termNext.parsed());
            if (offset < str.length() && str.charAt(offset) != '+') {
                throw exception("+ sign expected, not found", offset);
            }
            offset++;
        }
        return new ParsedNext<>(new Polynomial(terms), offset);
    }


}
