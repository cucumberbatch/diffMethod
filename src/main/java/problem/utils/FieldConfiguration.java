package problem.utils;

import problem.models.Field;
import problem.models.Matrix;

public class FieldConfiguration {
    public Matrix matrix;
    public double length;
    public double time;
    public double lengthStep;
    public double timeStep;
    public int n;
    public int m;

    public FieldConfiguration(Field field) {
        this(field.getMatrix(), field.getLengthStep(), field.getTimeStep());
    }

    public FieldConfiguration(Matrix matrix, double lengthStep, double timeStep) {
        this.matrix = matrix;
        this.lengthStep = lengthStep;
        this.timeStep = timeStep;
        this.n = matrix.getN();
        this.m = matrix.getM();
        length = n * lengthStep;
        time = m * timeStep;
    }

}
