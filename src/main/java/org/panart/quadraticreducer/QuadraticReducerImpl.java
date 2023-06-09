package org.panart.quadraticreducer;

import org.panart.commons.Pair;
import org.panart.linear.*;

import java.util.ArrayList;
import java.util.List;

public class QuadraticReducerImpl implements QuadraticReducer {

    protected Pair<Pair<Polynomial, Integer>, Polynomial> reduce(Polynomial polynomial, Variable variable) {
        Term pivot = null;
        List<Term> newTerms = new ArrayList<>();
        for (Term term : polynomial.getTerms()) {
            DegreeVariable degreeVariable = term.getDegreeVariable(variable);
            Term newTerm = term.multiply(
                    new Term(new BasicTerm(new DegreeVariable(variable))).revert()
            );
            if (degreeVariable.getDegree() == 1) {
                newTerms.add(newTerm.lambda(0.5));
            } else if (degreeVariable.getDegree() == 2) {
                pivot = newTerm;
            }
        }
        if (pivot == null) {
            return new Pair<>(
                    new Pair<>(new Polynomial(new Term(new BasicTerm(new DegreeVariable(variable, 1)), 1)),
                            0),
                    polynomial
            );
        }
        newTerms.add(pivot);
        Polynomial newVariable = new Polynomial(newTerms).lambda(
                1.0 / Math.sqrt(Math.abs(pivot.getCoef()))
        );
        int sign = (pivot.getCoef() > 0 ? 1 : -1);
        Polynomial newPolynomial = polynomial.add(newVariable.multiply(newVariable).lambda(-sign));
        return new Pair<>(new Pair<>(newVariable, sign), newPolynomial);
    }

    @Override
    public Pair<List<Polynomial>, List<Integer>> reduce(Polynomial polynomial, int degree) {
        if (!polynomial.isQuadraticForm()) {
            throw new IllegalArgumentException("Polynomial isn't in a right form: " + polynomial.format());
        }
        if (degree <= 1) {
            throw new IllegalArgumentException("Can't deal with variable change");  // TODO
        }

        List<Polynomial> newVariables = new ArrayList<>();
        List<Integer> signs = new ArrayList<>();
        for (int i = 1; i <= degree; i++) {
            var oneReduce = reduce(polynomial, new Variable(i));
            newVariables.add(oneReduce.first().first());
            signs.add(oneReduce.first().second());
            polynomial = oneReduce.second();
        }
        System.out.println("Left part of polynomial: " + polynomial.format());
        if (!polynomial.isZero()) {
            System.out.println("WARNING: Unable to fully solve the polynomial");
        }
        return new Pair<>(newVariables, signs);
    }
}
