package org.panart.linear;

import org.panart.formatting.Formatable;

import java.util.Comparator;
import java.util.Objects;

public class DegreeVariable implements Comparable<DegreeVariable>, Formatable { // value object

    private Variable variable; // final
    private final int degree;

    public DegreeVariable(Variable variable, int degree) {
        this.degree = degree;   // order important here
        setVariable(variable);
    }

    private void setVariable(Variable variable) {
        if (variable == null ^ degree == 0) {   // number case
            throw new IllegalArgumentException();
        }

        this.variable = variable;
    }

    public Variable getVariable() {
        return variable;
    }

    public int getDegree() {
        return degree;
    }

    @Override
    public String format() {
        return DegreeVariableFormatter.getInstance().format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreeVariable that = (DegreeVariable) o;
        return degree == that.degree && Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable, degree);
    }

    @Override
    public int compareTo(DegreeVariable o) {
        return Comparator.nullsFirst(Comparator.comparing((DegreeVariable var) -> var.variable)
                .thenComparing(var -> var.degree))
                .compare(this, o);
    }
}
