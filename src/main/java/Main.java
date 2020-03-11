import problem.Constants;
import problem.models.Field;
import problem.conditions.impl.OneDimensionHeatConductionBoundaryCondition;

public class Main {

	public static void main(String[] args) {
		Field D = new Field(
				Constants.Length,
				Constants.Time,
				Constants.N,
				Constants.M
		);

		D.applyBoundaryCondition(
				new OneDimensionHeatConductionBoundaryCondition()
		);

		D.solveProblem();
		D.viewInConsole();
	}


}

