package problem.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problem.Constants;
import problem.calculation.impl.CrankNicolsonFiniteDifferenceMethod;
import problem.calculation.impl.ExplicitFiniteDifferenceMethod;
import problem.calculation.impl.ImplicitFiniteDifferenceMethod;
import problem.conditions.BoundaryCondition;
import problem.models.Field;
import problem.utils.FieldManager;
import problem.utils.matrix.solver.impl.GaussMatrixAlgorithm;
import problem.utils.matrix.solver.impl.TridiagonalMatrixAlgorithm;
import problem.utils.view.impl.ConsoleDataPrinter;

import java.io.IOException;

class ImplicitFiniteDifferenceMethodTest {

    private static final double DELTA = 1E-5;

    @Test
    void testStabilityOfFiniteDifferenceMethods() throws IOException {
        double length = 1.0d;
        double time = 0.1d;
        int lengthSteps = 6;
        int timeSteps = 10;

        BoundaryCondition condition = (x, t) -> {
            if (x == 0) {
                return 20;
            }
            if (x == length) {
                return 0;
            }
            if (t == 0) {
                return 5;
            }
            return 0;
        };

        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(condition)
                .applyDifferenceMethod(new ExplicitFiniteDifferenceMethod())
                .done();

        Field field2 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(condition)
                .applyDifferenceMethod(new CrankNicolsonFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .done();

        Assertions.assertEquals(field1, field2);
    }


    @Test
    void testImplicitMethodWithTridiagonalAlgorithm() throws IOException {
        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(1.0, 0.1, 6, 10)
                .applyBoundaryCondition((x, t) -> {
                    if (x == 0) {
                        return 1;
                    }
                    if (x == 1) {
                        return 100;
                    }
                    if (t == 0) {
                        return 5;
                    }

                    return 0;
                })
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new TridiagonalMatrixAlgorithm()))
                .viewDataOn(new ConsoleDataPrinter())
                .done();
    }

    @Test
    void testImplicitMethodWithGauss() throws IOException {
        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(1.0, 0.1, 6, 10)
                .applyBoundaryCondition((x, t) -> {
                    if (x == 0) {
                        return 1;
                    }
                    if (x == 1) {
                        return 100;
                    }
                    if (t == 0) {
                        return 5;
                    }

                    return 0;
                })
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .viewDataOn(new ConsoleDataPrinter())
                .done();
    }

    @Test
    void testCrankNicolsonMethodWithTridiagonalAlgorithm() throws IOException {
        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(1.0, 0.1, 6, 10)
                .applyBoundaryCondition((x, t) -> {
                    if (x == 0) {
                        return 1;
                    }
                    if (x == 1) {
                        return 100;
                    }
                    if (t == 0) {
                        return 5;
                    }

                    return 0;
                })
                .applyDifferenceMethod(new CrankNicolsonFiniteDifferenceMethod(new TridiagonalMatrixAlgorithm()))
                .viewDataOn(new ConsoleDataPrinter())
                .done();
    }

    @Test
    void testCrankNicolsonMethodWithGauss() throws IOException {
        double length = 1.0d;
        double time = 5.0d;
        int lengthSteps = 15;
        int timeSteps = 15;

        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition((x, t) -> {
                    if (x == 0) {
                        return Constants.U0;
                    }
                    if (x == length) {
                        return 50;
                    }
                    if (t == 0) {
                        return 5;
                    }

                    return 0;
                })
                .applyDifferenceMethod(new CrankNicolsonFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .viewDataOn(new ConsoleDataPrinter())
                .done();
    }
}