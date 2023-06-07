package org.panart.parser;

public class ParsedNext<T> {
    private final T parsed;
    private final int next;

    public ParsedNext(T parsed, int next) {
        this.parsed = parsed;
        this.next = next;
    }

    public T getParsed() {
        return parsed;
    }

    public int getNext() {
        return next;
    }
}
