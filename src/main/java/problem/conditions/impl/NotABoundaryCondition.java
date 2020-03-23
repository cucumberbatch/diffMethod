package problem.conditions.impl;

import problem.conditions.BoundaryCondition;
import problem.solution.Solution;

public class NotABoundaryCondition implements BoundaryCondition {
    private Solution solution = new Solution();

    @Override
    public double u(double x, double t) {
        return solution.u(x, t);
    }
}
