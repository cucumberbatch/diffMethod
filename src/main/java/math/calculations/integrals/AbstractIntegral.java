package math.calculations.integrals;

import math.Function;

public abstract class AbstractIntegral implements Integral {
    protected Function function;

    public AbstractIntegral(Function function) {
        this.function = function;
    }
}
