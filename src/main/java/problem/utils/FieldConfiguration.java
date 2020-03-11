package problem.utils;

import problem.models.Field;
import problem.models.Matrix;

public class FieldConfiguration {
	public Matrix matrix;
	public double lengthStep;
	public double timeStep;
	public int N;
	public int M;

	public FieldConfiguration(Field field) {
		this(field.getMatrix(), field.getN(), field.getM(), field.getLengthStep(), field.getTimeStep());
	}

	public FieldConfiguration(Matrix matrix, int n, int m, double lengthStep, double timeStep) {
		this.matrix = matrix;
		this.lengthStep = lengthStep;
		this.timeStep = timeStep;
		N = n;
		M = m;
	}

}
