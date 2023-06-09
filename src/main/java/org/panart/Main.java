package org.panart;

import org.panart.parser.PolynomialPartialParser;
import org.panart.quadraticreducer.QuadraticReducerImpl;
import org.panart.quadraticreducer.ui.QuadraticReducerClient;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        new QuadraticReducerClient(
                new QuadraticReducerImpl(), new PolynomialPartialParser(),
                "-input"
        ).run(args);
    }
}