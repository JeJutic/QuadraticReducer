package org.panart.linear;

import org.panart.formatting.Formatable;

import java.util.Comparator;
import java.util.Objects;

public class Term implements TermInterface<Term>, Algebra<Term>, IsZero, Formatable {

    final double coef;
    BasicTerm basicTerm; // "final", package-private for formatter

    public Term(BasicTerm basicTerm, double coef) {
        this.coef = coef;
        setBasicTerm(basicTerm);
    }

    public Term(BasicTerm basicTerm) {
        this(basicTerm, 1);
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
//        return coef == 0;   // FIXME: double comparison
        return Math.abs(coef) < 1e-9;
    }

    @Override
    public DegreeVariable getDegreeVariable(Variable variable) {
        return basicTerm.getDegreeVariable(variable);
    }

    @Override
    public Term multiply(Term o) {
        return new Term(basicTerm.multiply(o.basicTerm), coef * o.coef);
    }

    @Override
    public Term revert() {
        if (isZero()) {
            throw new ArithmeticException("Division by zero");
        }
        return new Term(basicTerm.revert(), 1 / coef);
    }

    @Override
    public Term add(Term o) {
        if (!basicTerm.equals(o.basicTerm)) {
            throw new IllegalArgumentException();
        }

        return new Term(basicTerm, coef + o.coef);
    }

    @Override
    public Term lambda(double scalar) {
        return new Term(basicTerm, coef * scalar);
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
        return Comparator.comparing((Term term) -> term.basicTerm)
                .thenComparing(term -> coef)
                .compare(this, o);
    }
}
