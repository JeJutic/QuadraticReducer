package org.panart.quadraticreducer.ui;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.panart.parser.PolynomialPartialParser;
import org.panart.quadraticreducer.QuadraticReducerImpl;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

class QuadraticReducerClientTest {

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    private final QuadraticReducerClient client = new QuadraticReducerClient(
            new QuadraticReducerImpl(), new PolynomialPartialParser(), printStream, "-input"
    );

    @ParameterizedTest
    @MethodSource
    void runImpl(String[] args, String[] expected, boolean warningExpected) {
        client.runImpl(args);
        String output = outputStream.toString();

        int prevOccurence = -1;
        for (String substr : expected) {
            int newOccurence = output.lastIndexOf(substr);
            assertFalse(newOccurence == -1 || newOccurence < prevOccurence);
            prevOccurence = newOccurence;
        }

        assertTrue(warningExpected ^ (output.lastIndexOf("WARNING") == -1));
    }

    private static Stream<Arguments> runImpl() {
        return Stream.of(
                Arguments.of(new String[]{"3", "9x1d2+-12x1x2+-6x1x3+4x2d2+4x2x3+x3d2"},
                        new String[]{
                                "1 0 0",
                                "0 0 0",
                                "1 3.000x1+-2.000x2+-1.000x3"
                        },
                        false),
                Arguments.of(new String[]{"2", "3x1x2"},
                        new String[]{},
                        true),
                Arguments.of(new String[]{"3", "5x1d2+16x1x2+-14x1x3+13x2d2+-22x2x3+10x3d2"},
                        new String[]{
                                "1 0 0",
                                "0 1 0",
                                "0 0 0"
                        },
                        false),
                Arguments.of(
                        new String[]{
                                "4",
                                "x1d2+2x1x2+-6x1x3+12x1x4+2x2d2+-8x2x3+18x2x4+9x3d2+-40x3x4+44x4d2"},
                        new String[]{
                                "1 0 0 0",
                                "0 1 0 0",
                                "0 0 -1 0",
                                "0 0 0 0"
                        },
                        false)
        );
    }
}