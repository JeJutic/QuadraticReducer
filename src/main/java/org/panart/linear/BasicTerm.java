package org.panart.linear;

import org.panart.formatting.Formatable;

import java.util.*;
import java.util.function.Predicate;

// probably aggregate
public class BasicTerm implements TermInterface<BasicTerm>, Formatable {

    private List<DegreeVariable> degreeVariables; // final

    public BasicTerm(List<DegreeVariable> degreeVariables) {
        setDegreeVariables(degreeVariables);
    }

    public BasicTerm(DegreeVariable degreeVariable) {
        this(List.of(degreeVariable));
    }

    private void setDegreeVariables(List<DegreeVariable> degreeVariables) {
        if (degreeVariables == null) {
            throw new IllegalArgumentException("List should have been passed");
        }
        for (var degreeVariable : degreeVariables) {
            if (degreeVariable == null) {
                throw new IllegalArgumentException();
            }
        }

        degreeVariables = degreeVariables.stream().filter(
                Predicate.not(DegreeVariable::isOne)
        ).toList();   // it is immutable now
        degreeVariables = new ArrayList<>(degreeVariables); // making it modifiable
        Collections.sort(degreeVariables);

        for (int i = 1; i < degreeVariables.size(); i++) {
            if (degreeVariables.get(i - 1).getVariable().equals(
                    degreeVariables.get(i).getVariable())) {
                throw new IllegalArgumentException(
                        "BasicTerm can't have 2 DegreeVariable's of the same variable");
            }
        }

        this.degreeVariables = degreeVariables;    // making it modifiable
    }

    List<DegreeVariable> getDegreeVariables() {   // package-private for formatter
        return degreeVariables;
    }

    @Override
    public DegreeVariable getDegreeVariable(Variable variable) {
        if (degreeVariables.isEmpty()) {
            return new DegreeVariable(null, 0);
        }

        int pos = Collections.binarySearch(degreeVariables, new DegreeVariable(variable, Integer.MIN_VALUE));
        if (pos < 0) {
            pos = -pos - 1;
        }
        if (pos < degreeVariables.size() && degreeVariables.get(pos).getVariable().equals(variable)) {
            return degreeVariables.get(pos);
        } else {
            return new DegreeVariable(null, 0);
        }
    }

    @Override
    public BasicTerm multiply(BasicTerm o) {
        List<DegreeVariable> newDegreeVariables = new ArrayList<>();

        int firstPtr = 0;
        int secondPtr = 0;
        while (firstPtr < degreeVariables.size() && secondPtr < o.degreeVariables.size()) {
            DegreeVariable firstVar = degreeVariables.get(firstPtr);
            DegreeVariable secondVar = o.degreeVariables.get(secondPtr);
            int cmp = firstVar.getVariable().compareTo(secondVar.getVariable());
            if (cmp > 0) {
                newDegreeVariables.add(secondVar);
                secondPtr++;
            } else if (cmp < 0) {
                newDegreeVariables.add(firstVar);
                firstPtr++;
            } else {
                newDegreeVariables.add(new DegreeVariable(
                        firstVar.getVariable(), firstVar.getDegree() + secondVar.getDegree())
                );
                firstPtr++;
                secondPtr++;
            }
        }
        newDegreeVariables.addAll(degreeVariables.subList(firstPtr, degreeVariables.size()));
        newDegreeVariables.addAll(o.degreeVariables.subList(secondPtr, o.degreeVariables.size()));

        return new BasicTerm(newDegreeVariables);
    }

    @Override
    public BasicTerm revert() {
        List<DegreeVariable> newDegreeVariables = new ArrayList<>();
        for (var degreeVariable : degreeVariables) {
            newDegreeVariables.add(new DegreeVariable(
                    degreeVariable.getVariable(), -degreeVariable.getDegree())
            );
        }
        return new BasicTerm(newDegreeVariables);
    }

    @Override
    public boolean isQuadraticForm() {
        return (degreeVariables.size() == 1 && degreeVariables.get(0).getDegree() == 2) ||
                (degreeVariables.size() == 2 && degreeVariables.get(0).getDegree() == 1 &&
                        degreeVariables.get(1).getDegree() == 1);
    }

    @Override
    public String format() {
        return BasicTermFormatter.getInstance().format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicTerm basicTerm1 = (BasicTerm) o;
        return degreeVariables.equals(basicTerm1.degreeVariables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degreeVariables);
    }

    @Override
    public int compareTo(BasicTerm o) {
        for (int i = 0; i < Math.min(degreeVariables.size(), o.degreeVariables.size()); i++) {
            DegreeVariable firstVar = degreeVariables.get(i);
            DegreeVariable secondVar = o.degreeVariables.get(i);
            if (firstVar.compareTo(secondVar) != 0) {
                return firstVar.compareTo(secondVar);
            }
        }
        return Integer.compare(degreeVariables.size(), o.degreeVariables.size());
    }
}
