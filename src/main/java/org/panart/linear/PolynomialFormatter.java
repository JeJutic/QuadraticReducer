package org.panart.linear;

import org.panart.formatting.Formatter;

import java.util.List;

class PolynomialFormatter implements Formatter<Polynomial> {

    private static PolynomialFormatter singleton;

    private PolynomialFormatter() {
    }

    public static PolynomialFormatter getInstance() {
        if (singleton == null) {
            singleton = new PolynomialFormatter();
        }
        return singleton;
    }

    @Override
    public String format(Polynomial o) {
        if (o.isZero()) {
            return "0";
        }
        List<Term> terms = o.getTerms();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            sb.append(terms.get(i).format());
            if (i != terms.size() - 1) {
                sb.append('+');
            }
        }

        return sb.toString();
    }
}