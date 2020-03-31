package problem.conditions.impl;

import problem.Constants;
import problem.conditions.BoundaryCondition;

import java.util.logging.Logger;

public class OneDimensionHeatConductionBoundaryCondition implements BoundaryCondition {
    private Logger log;

    @Override
    public void setLogger(Logger log) {
        this.log = log;
    }

    @Override
    public double u(double x, double t) {

        if (t == 0) {
            // u(x, 0) = 0
            return 10.0d;
        } else if (x == 0) {
            // u(0, t) = 0
            return Constants.U0;
        }

        if (x == Constants.Length) {
            // u(l, t) = 0
            return 3.0d;
        }
        return 0.0d;
    }
}
