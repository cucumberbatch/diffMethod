package problem.utils;

import java.util.Arrays;
import java.util.Objects;

public class FieldConfiguration {
    public int m;               // Amount of time steps
    public int n;               // Amount of length steps
    public double time;         // Time of modelling
    public double length;       // Length of field
    public double timeStep;     // Time step value
    public double lengthStep;   // Length step value
    public double[][] matrix;   // Field

    public FieldConfiguration(double[][] matrix, double lengthStep, double timeStep) {
        this.matrix = matrix;
        this.lengthStep = lengthStep;
        this.timeStep = timeStep;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.time = m * timeStep;
        this.length = n * lengthStep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldConfiguration that = (FieldConfiguration) o;
        return m == that.m &&
                n == that.n &&
                Double.compare(that.time, time) == 0 &&
                Double.compare(that.length, length) == 0 &&
                Double.compare(that.timeStep, timeStep) == 0 &&
                Double.compare(that.lengthStep, lengthStep) == 0 &&
                Arrays.equals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(m, n, time, length, timeStep, lengthStep);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }
}
