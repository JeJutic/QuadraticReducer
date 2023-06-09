package org.panart.parser;

import org.panart.linear.Polynomial;

import java.text.ParseException;

public interface PolynomialParser {

    Polynomial parse(String str) throws ParseException;
}
