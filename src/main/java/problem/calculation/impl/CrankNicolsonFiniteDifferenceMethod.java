package problem.calculation.impl;

import problem.calculation.FiniteDifferenceMethod;
import problem.models.Field;
import problem.utils.FieldConfiguration;

import java.util.logging.Logger;

public class CrankNicolsonFiniteDifferenceMethod implements FiniteDifferenceMethod {
    private Logger log;

    @Override
    public void setLogger(Logger log) {
        this.log = log;
    }

    @Override
    public FieldConfiguration solve(FieldConfiguration configuration) {
        return null;
    }

    public CrankNicolsonFiniteDifferenceMethod(Field field) {

    }

}
