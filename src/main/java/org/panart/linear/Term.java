package org.panart.linear;

import org.panart.formatting.Formatable;

import java.util.Comparator;
import java.util.Objects;

public class Term implements TermInterface<Term>, Algebra<Term>, IsZero, Formatable {

    final double coef;
    BasicTerm basicTerm; // "final", package-private for formatter

    public Term(double coef, BasicTerm basicTerm) {
        this.coef = coef;
        setBasicTerm(basicTerm);
    }

    public Term(BasicTerm basicTerm) {
        this(1, basicTerm);
    }

    private void setBasicTerm(BasicTerm basicTerm) {
        if (basicTerm == null) {
            throw new IllegalArgumentException();
        }

        this.basicTerm = basicTerm;
    }

    public double getCoef() {
        return coef;
    }

    @Override
    public boolean isZero() {
        return Math.abs(coef) < 1e-9;
    }

    @Override
    public DegreeVariable getDegreeVariable(Variable variable) {
        return basicTerm.getDegreeVariable(variable);
    }

    @Override
    public Term multiply(Term o) {
        return new Term(coef * o.coef, basicTerm.multiply(o.basicTerm));
    }

    @Override
    public Term revert() {
        if (isZero()) {
            throw new ArithmeticException("Division by zero");
        }
        return new Term(1 / coef, basicTerm.revert());
    }

    @Override
    public Term add(Term o) {
        if (!basicTerm.equals(o.basicTerm)) {
            throw new IllegalArgumentException();
        }

        return new Term(coef + o.coef, basicTerm);
    }

    @Override
    public Term lambda(double scalar) {
        return new Term(coef * scalar, basicTerm);
    }

    @Override
    public boolean isQuadraticForm() {
        return basicTerm.isQuadraticForm();
    }

    @Override
    public String format() {
        return TermFormatter.getInstance().format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return Double.compare(term.coef, coef) == 0 && basicTerm.equals(term.basicTerm);
    }

    public boolean equalsByBasicTerm(Term term) {
        if (term == null) {
            return false;
        }
        return basicTerm.equals(term.basicTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coef, basicTerm);
    }

    @Override
    public int compareTo(Term o) {
        return Comparator.nullsFirst(Comparator.comparing((Term term) -> term.basicTerm)
                .thenComparing(term -> coef))
                .compare(this, o);
    }
}
