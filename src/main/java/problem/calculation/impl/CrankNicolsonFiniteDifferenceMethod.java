package problem.calculation.impl;

import problem.calculation.FiniteDifferenceMethod;
import problem.models.Field;
import problem.utils.FieldConfiguration;
import problem.utils.matrix.solver.LinearEquationsSystemSolver;

public class CrankNicolsonFiniteDifferenceMethod implements FiniteDifferenceMethod {

	@Override
	public FieldConfiguration solve(FieldConfiguration configuration, LinearEquationsSystemSolver solver) {
		return null;
	}

	public CrankNicolsonFiniteDifferenceMethod(Field field) {

	}

}
