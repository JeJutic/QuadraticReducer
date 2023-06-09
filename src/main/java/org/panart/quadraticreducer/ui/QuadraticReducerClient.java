package org.panart.quadraticreducer.ui;

import org.panart.commons.commandlinerunner.AbstractCommandLineRunner;
import org.panart.linear.Polynomial;
import org.panart.parser.PolynomialParser;
import org.panart.quadraticreducer.QuadraticReducer;

import java.io.PrintStream;
import java.text.ParseException;
import java.util.List;

public class QuadraticReducerClient extends AbstractCommandLineRunner {

    private QuadraticReducer reducer;   // final
    private PolynomialParser parser;    // final
    private PrintStream printStream;    // final

    public QuadraticReducerClient(
            QuadraticReducer reducer, PolynomialParser parser, PrintStream printStream, String flag
    ) {
        super(flag);
        setReducer(reducer);
        setParser(parser);
        setPrintStream(printStream);
    }

    public QuadraticReducerClient(QuadraticReducer reducer, PolynomialParser parser, String flag) {
        this(reducer, parser, System.out, flag);
    }

    public QuadraticReducerClient(QuadraticReducer reducer, PolynomialParser parser) {
        this(reducer, parser, "-input");
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

    public void setPrintStream(PrintStream printStream) {
        if (printStream == null) {
            throw new IllegalArgumentException();
        }

        this.printStream = printStream;
    }

    @Override
    public void runImpl(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }

        int variableCnt = Integer.parseInt(args[0]);

        Polynomial polynomial;
        try {
            polynomial = parser.parse(args[1]);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "At position " + e.getErrorOffset() + ": " + e.getMessage()
            );
        }

        var results = reducer.reduce(polynomial, variableCnt);
        List<Polynomial> newVariables = results.first();
        List<Integer> signs = results.second();

        if (newVariables.size() > variableCnt) {
            printStream.println(
                    "WARNING: Unable to fully solve the polynomial: " +
                            newVariables.get(variableCnt).format()
            );
        }

        for (int i = 0; i < variableCnt; i++) {
            for (int j = 0; j < variableCnt; j++) {
                if (i == j) {
                    printStream.print(signs.get(i));
                } else {
                    printStream.print(0);
                }
                printStream.print(" ");
            }
            printStream.println();
        }
        for (int i = 0; i < variableCnt; i++) {
            printStream.println((i + 1) + " " + newVariables.get(i).format());
        }
    }
}
