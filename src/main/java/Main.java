import problem.Constants;
import problem.calculation.impl.ExplicitFiniteDifferenceMethod;
import problem.conditions.impl.OneDimensionHeatConductionBoundaryCondition;
import problem.utils.FieldManager;
import problem.utils.view.impl.ConsoleDataViewer;
import problem.utils.view.impl.DataFileSerializer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        FieldManager manager = new FieldManager();
        manager.init(Constants.Length, Constants.Time, Constants.N, Constants.M)
                .applyBoundaryCondition(new OneDimensionHeatConductionBoundaryCondition())
                .viewDataOn(new ConsoleDataViewer())
                .applyDifferenceMethod(new ExplicitFiniteDifferenceMethod())
                .viewDataOn(new ConsoleDataViewer())
                .viewDataOn(new DataFileSerializer("file"))
                .done();

    }

}

