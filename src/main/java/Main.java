import problem.Constants;
import problem.calculation.impl.ImplicitFiniteDifferenceMethod;
import problem.conditions.impl.OneDimensionHeatConductionBoundaryCondition;
import problem.utils.FieldManager;
import problem.utils.matrix.solver.impl.GaussMatrixAlgorithm;
import problem.utils.view.impl.DataFileSerializer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        FieldManager manager = new FieldManager();
        manager.init(Constants.Length, Constants.Time, Constants.N, Constants.M)
                .applyBoundaryCondition(new OneDimensionHeatConductionBoundaryCondition())
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .viewDataOn(new DataFileSerializer("file"))
                .done();

    }

}

