package problem.models;

import problem.calculation.FiniteDifferenceMethod;
import problem.conditions.BoundaryCondition;
import problem.utils.FieldConfiguration;
import problem.utils.view.DataViewer;

import java.io.IOException;

public class Field {

    private FieldConfiguration configuration;
    private BoundaryCondition condition;
    private FiniteDifferenceMethod method;

    public Field(
            double length, double time,
            int n, int m) {
        configuration = new FieldConfiguration(
                new Matrix(n + 1, m + 1),
                length / n,
                time / m
        );
    }

    public void applyBoundaryCondition(BoundaryCondition condition) {
        condition.apply(configuration);
    }

    public void applyDifferenceMethod(FiniteDifferenceMethod diffMethod) {
        configuration = diffMethod.solve(configuration, null);
    }

    public void viewDataOn(DataViewer container) throws IOException {
        if (container == null) {
            return;
        }

        container.view(configuration);
    }

    public Matrix getMatrix() {
        return new Matrix(configuration.matrix);
    }

    public double getLength() {
        return configuration.length;
    }

    public double getTime() {
        return configuration.time;
    }

    public int getN() {
        return configuration.matrix.getN();
    }

    public int getM() {
        return configuration.matrix.getM();
    }

    public double getLengthStep() {
        return configuration.lengthStep;
    }

    public double getTimeStep() {
        return configuration.timeStep;
    }

}
