package org.panart.linear;

import org.panart.formatting.Formatable;

import java.util.*;
import java.util.function.Predicate;

public class Polynomial implements Algebra<Polynomial>, IsQuadraticForm, IsZero, Formatable {  // probably aggregate root

    private List<Term> terms;

    public Polynomial(List<Term> terms) {
        setTerms(terms);
    }

    public Polynomial(Term term) {
        this(List.of(term));
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

        this.terms = new ArrayList<>(terms);
        sortAndReduce();
    }

    public List<Term> getTerms() {
        return Collections.unmodifiableList(terms);
    }

    private void sortAndReduce() {
        Collections.sort(terms);
        if (terms.isEmpty()) {
            return;
        }

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

        terms = newTerms.stream().filter(Predicate.not(Term::isZero)).toList();
    }

    @Override
    public boolean isZero() {
        return terms.isEmpty();
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
    public boolean isQuadraticForm() {
        return terms.stream().allMatch(Term::isQuadraticForm);
    }

    @Override
    public String format() {
        return PolynomialFormatter.getInstance().format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return terms.equals(that.terms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(terms);
    }
}
