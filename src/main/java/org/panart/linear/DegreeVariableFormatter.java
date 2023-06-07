package org.panart.linear;

import org.panart.formatting.Formatter;

public class DegreeVariableFormatter implements Formatter<DegreeVariable> {

    private static DegreeVariableFormatter singleton;
    
    private DegreeVariableFormatter() {
    }

    public static Formatter<DegreeVariable> getInstance() {
        if (singleton == null) {
            singleton = new DegreeVariableFormatter();
        }
        return singleton;
    }

    @Override
    public String format(DegreeVariable o) {
        String str = "x" + (o.getVariable() == null ? 0 : o.getVariable().id());
        if (o.getDegree() != 1) {
            str += "d" + o.getDegree();
        }
        return str;
    }
}
