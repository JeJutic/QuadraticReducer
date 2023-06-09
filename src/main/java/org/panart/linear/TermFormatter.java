package org.panart.linear;

import org.panart.formatting.Formatter;

class TermFormatter implements Formatter<Term> {

    private static TermFormatter singleton;

    private TermFormatter() {
    }

    public static TermFormatter getInstance() {
        if (singleton == null) {
            singleton = new TermFormatter();
        }
        return singleton;
    }

    @Override
    public String format(Term o) {
        return (o.coef == 1 ? "" : String.format("%.3f", o.coef)) + o.basicTerm.format();
    }
}
