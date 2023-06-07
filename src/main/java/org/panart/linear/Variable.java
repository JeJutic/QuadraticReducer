package org.panart.linear;

import java.util.Objects;

public record Variable(int id) implements Comparable<Variable> {    // value object

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return id == variable.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Variable o) {
        if (o == null) {
            return 1;
        }
        return Integer.compare(id, o.id);
    }
}
