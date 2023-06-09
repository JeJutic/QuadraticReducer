package org.panart.quadraticreducer.ui;

import org.panart.commons.commandlinerunner.AbstractCommandLineRunner;
import org.panart.linear.Polynomial;
import org.panart.parser.PolynomialParser;
import org.panart.quadraticreducer.QuadraticReducer;

import java.text.ParseException;
import java.util.List;

public class QuadraticReducerClient extends AbstractCommandLineRunner {

    private QuadraticReducer reducer;   // final
    private PolynomialParser parser;    // final

    public QuadraticReducerClient(QuadraticReducer reducer, PolynomialParser parser, String flag) {
        super(flag);
        setReducer(reducer);
        setParser(parser);
    }

    private void setReducer(QuadraticReducer reducer) {
        if (reducer == null) {
            throw new IllegalArgumentException();
        }

        this.reducer = reducer;
    }

    private void setParser(PolynomialParser parser) {
        if (parser == null) {
            throw new IllegalArgumentException();
        }

        this.parser = parser;
    }

    @Override
    public void runImpl(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }

        int degree = Integer.parseInt(args[0]);

        Polynomial polynomial;
        try {
            polynomial = parser.parse(args[1]);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "At position " + e.getErrorOffset() + ": " + e.getMessage()
            );
        }

        var results = reducer.reduce(polynomial, degree);
        List<Polynomial> newVariables = results.first();
        List<Integer> signs = results.second();
        for (int i = 0; i < degree; i++) {
            for (int j = 0; j < degree; j++) {
                if (i == j) {
                    System.out.print(signs.get(i));
                } else {
                    System.out.print(0);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        for (int i = 0; i < newVariables.size(); i++) {
            System.out.println((i + 1) + " " + newVariables.get(i).format());
        }
    }
}
