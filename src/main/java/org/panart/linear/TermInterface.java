package org.panart.linear;

public interface TermInterface<T extends TermInterface<?>>
        extends DegreeVariableContainer, MultiplyGroup<T>, IsQuadraticForm, Comparable<T> {
}
