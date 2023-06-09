package org.panart.quadraticreducer;

import org.panart.commons.Pair;
import org.panart.linear.Polynomial;

import java.util.List;

public interface QuadraticReducer {

    Pair<List<Polynomial>, List<Integer>> reduce(Polynomial polynomial, int variableCnt);
}
