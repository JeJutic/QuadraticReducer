package org.panart.quadraticreducer.ui;

import org.junit.jupiter.api.Test;
import org.panart.parser.PolynomialPartialParser;
import org.panart.quadraticreducer.QuadraticReducerImpl;

class QuadraticReducerClientTest {

    private static final QuadraticReducerClient client = new QuadraticReducerClient(
            new QuadraticReducerImpl(), new PolynomialPartialParser(), "-input"
    );

    @Test
    void runImpl() {
//        client.runImpl(new String[]{"3", "9x1d2+-12x1x2+-6x1x3+4x2d2+4x2x3+x3d2"});
//        client.runImpl(new String[]{"4", "x1d2+2x1x2+-6x1x3+12x1x4+2x2d2+-8x2x3+18x2x4+9x3d2+-40x3x4+44x4d2"});
        client.runImpl(new String[]{"3", "5x1d2+16x1x2+-14x1x3+13x2d2+-22x2x3+10x3d2"});
    }
}