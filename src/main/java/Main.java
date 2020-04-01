import problem.Constants;
import problem.calculation.impl.CrankNicolsonFiniteDifferenceMethod;
import problem.conditions.impl.OneDimensionHeatConductionBoundaryCondition;
import problem.solution.Solution;
import problem.utils.FieldManager;
import problem.utils.matrix.solver.impl.GaussMatrixAlgorithm;
import problem.utils.view.impl.FileDataPrinter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        FieldManager manager = new FieldManager();
        manager.init(Constants.Length, Constants.Time, Constants.N, Constants.M)
                .applyBoundaryCondition(new OneDimensionHeatConductionBoundaryCondition())
                .applyDifferenceMethod(new CrankNicolsonFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .viewDataOn(new FileDataPrinter("file"))
                .done();

        System.out.print("\nx*1 = " + solution.x_(0.001));
        System.out.print("\nx*2 = " + solution.x_(0.005));
        System.out.print("\nx*3 = " + solution.x_(0.1));
    }
}

