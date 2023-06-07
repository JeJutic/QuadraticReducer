package org.panart.linear;

import org.panart.formatting.Formatter;

import java.util.List;

public class BasicTermFormatter implements Formatter<BasicTerm> {

    private static BasicTermFormatter singleton;

    private BasicTermFormatter() {
    }
    
    public static Formatter<BasicTerm> getInstance() {
        if (singleton == null) {
            singleton = new BasicTermFormatter();
        }
        return singleton;
    }

    @Override
    public String format(BasicTerm o) {
        List<DegreeVariable> degreeVariables = o.getDegreeVariables();

        StringBuilder sb = new StringBuilder();
        for (var degreeVariable : degreeVariables) {
            sb.append(degreeVariable.format());
        }
        
        return sb.toString();
    }
}
