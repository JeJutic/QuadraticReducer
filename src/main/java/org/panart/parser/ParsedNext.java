package org.panart.parser;

import java.util.Objects;

public record ParsedNext<T>(T parsed, int next) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParsedNext<?> that = (ParsedNext<?>) o;
        return next == that.next && Objects.equals(parsed, that.parsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parsed, next);
    }
}
