package problem.models;

import problem.calculation.FiniteDifferenceMethod;
import problem.conditions.BoundaryCondition;
import problem.utils.FieldConfiguration;

public class Field {

	private Matrix matrix;
	private BoundaryCondition condition;
	private FiniteDifferenceMethod method;

	private double Length;
	private double Time;

	private double lengthStep;
	private double timeStep;

	public Field(double Length, double Time, int N, int M) {
		matrix = new Matrix(N, M);
		setLength(Length);
		setTime(Time);
		setLengthStep();
		setTimeStep();
	}

	public Field(double Length, double Time, int N, int M, BoundaryCondition condition) {
		this(Length, Time, N, M);
		this.condition = condition;
	}


	public void applyBoundaryCondition() {
		if (condition == null) {
			return;
		}

		FieldConfiguration configuration = new FieldConfiguration(this);
		condition.apply(configuration);
		this.matrix = configuration.matrix;
	}

	public void applyBoundaryCondition(BoundaryCondition condition) {
		this.condition = condition;
		applyBoundaryCondition();
	}

	// TODO: make
	public void collectDataTo() {

	}

	public void applyDifferenceMethod() {
		if (method == null) {
			return;
		}


	}

	public void applyDifferenceMethod(FiniteDifferenceMethod diffMethod) {
		this.method = diffMethod;
		applyDifferenceMethod();
	}

	public void solveProblem() {

	}

	public Matrix getMatrix() {
		return new Matrix(matrix);
	}

	public double getLength() {
		return Length;
	}

	public double getTime() {
		return Time;
	}

	public int getN() {
		return matrix.getN();
	}

	public int getM() {
		return matrix.getM();
	}

	public double getLengthStep() {
		return lengthStep;
	}

	public double getTimeStep() {
		return timeStep;
	}


	private void setLength(double length) {
		Length = length;
	}
	private void setTime(double time) {
		Time = time;
	}
	private void setLengthStep() {
		lengthStep = Length / matrix.getN();
	}
	private void setTimeStep() {
		timeStep = Time / matrix.getM();
	}

}
