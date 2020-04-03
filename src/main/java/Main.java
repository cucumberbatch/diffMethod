import problem.Constants;
import problem.calculation.impl.ExplicitFiniteDifferenceMethod;
import problem.conditions.impl.OneDimensionHeatConductionBoundaryCondition;
import problem.utils.FieldManager;
import problem.utils.matrix.solver.LinearEquationsSystemSolver;
import problem.utils.matrix.solver.impl.GaussMatrixAlgorithm;
import problem.utils.view.impl.FileDataPrinter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        LinearEquationsSystemSolver solver = new GaussMatrixAlgorithm();
        FieldManager manager = new FieldManager();
        manager .init(Constants.Length, Constants.Time, Constants.N, Constants.M)
                .applyBoundaryCondition(new OneDimensionHeatConductionBoundaryCondition())

//                .applyDifferenceMethod(new CrankNicolsonFiniteDifferenceMethod(solver))
//                .viewDataOn(new FileDataPrinter("crank-nicolson"))
//
//                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(solver))
//                .viewDataOn(new FileDataPrinter("implicit"))

                .applyDifferenceMethod(new ExplicitFiniteDifferenceMethod())
                .viewDataOn(new FileDataPrinter("explicit"))

                .done();
    }
}

