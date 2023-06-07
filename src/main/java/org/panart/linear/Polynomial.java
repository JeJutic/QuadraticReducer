package org.panart.linear;

import org.panart.formatting.Formatable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Polynomial implements Algebra<Polynomial>, Formatable {  // probably aggregate root

    private List<Term> terms;

    public Polynomial(List<Term> terms) {
        setTerms(terms);
    }

    private void setTerms(List<Term> terms) {
        if (terms == null) {
            throw new IllegalArgumentException();
        }
        for (var term : terms) {
            if (term == null) {
                throw new IllegalArgumentException();
            }
        }

        terms = new ArrayList<>(terms); // FIXME?

        this.terms = terms;
        sortAndReduce();
    }

    List<Term> getTerms() { // package-private for formatter
        return terms;
    }

    private void sortAndReduce() {
        if (terms.isEmpty()) {
            return;
        }
        Collections.sort(terms);

        List<Term> newTerms = new ArrayList<>();
        Term curTerm = terms.get(0);
        for (int i = 1; i < terms.size(); i++) {
            if (terms.get(i).equalsByBasicTerm(curTerm)) {
                curTerm = curTerm.add(terms.get(i));
            } else {
                newTerms.add(curTerm);
                curTerm = terms.get(i);
            }
        }
        newTerms.add(curTerm);

        terms = newTerms;
    }

    @Override
    public Polynomial add(Polynomial o) {
        List<Term> newTerms = new ArrayList<>(terms);
        newTerms.addAll(o.terms);
        return new Polynomial(newTerms);
    }

    @Override
    public Polynomial lambda(double scalar) {
        List<Term> newTerms = new ArrayList<>(terms.size());
        for (var term : terms) {
            newTerms.add(term.lambda(scalar));
        }
        return new Polynomial(newTerms);
    }

    @Override
    public Polynomial multiply(Polynomial o) {
        List<Term> newTerms = new ArrayList<>();
        for (var term1 : terms) {
            for (var term2 : o.terms) {
                newTerms.add(term1.multiply(term2));
            }
        }
        return new Polynomial(newTerms);
    }

    @Override
    public String format() {
        return PolynomialFormatter.getInstance().format(this);
    }
}
