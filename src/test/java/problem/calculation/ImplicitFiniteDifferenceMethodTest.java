package problem.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import problem.Constants;
import problem.calculation.impl.CrankNicolsonFiniteDifferenceMethod;
import problem.calculation.impl.ExplicitFiniteDifferenceMethod;
import problem.calculation.impl.GeneralFiniteDifferenceMethod;
import problem.calculation.impl.ImplicitFiniteDifferenceMethod;
import problem.conditions.BoundaryCondition;
import problem.conditions.impl.OneDimensionHeatConductionBoundaryCondition;
import problem.models.Field;
import problem.utils.FieldManager;
import problem.utils.matrix.solver.impl.GaussMatrixAlgorithm;
import problem.utils.matrix.solver.impl.TridiagonalMatrixAlgorithm;
import problem.utils.view.impl.ConsoleDataPrinter;

import java.io.IOException;
import java.util.Arrays;

class ImplicitFiniteDifferenceMethodTest {
    private static final double DELTA = 1E-5;


    @Test
    void testFieldManager() throws IOException {
        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(1, 1, 5, 5)
                .done();

        Assertions.assertNotNull(field1.getDataTable());
    }

    @Test
    void testImplicitMethodWithTridiagonalAlgorithm() throws IOException {
        double length = 1.0d;
        double time = 5.0d;
        int lengthSteps = 25;
        int timeSteps = 50;

        BoundaryCondition condition = (x, t) -> {
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
        };
        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(condition)
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new TridiagonalMatrixAlgorithm()))
                .viewDataOn(new ConsoleDataPrinter())
                .done();

        Assertions.assertNotNull(field1.getDataTable());
    }

    @Test
    void testImplicitMethodWithGauss() throws IOException {
        double length = 1.0d;
        double time = 5.0d;
        int lengthSteps = 25;
        int timeSteps = 50;

        BoundaryCondition condition = (x, t) -> {
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
        };
        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(length, time, lengthSteps, timeSteps)
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
        int lengthSteps = 250;
        int timeSteps = 500;

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

    @Test
    void testGeneralMethodWithGauss() throws IOException {
        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(1.0, 0.1, 20, 25)
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
                .applyDifferenceMethod(new GeneralFiniteDifferenceMethod(1.0d, new GaussMatrixAlgorithm()))
                .viewDataOn(new ConsoleDataPrinter())
                .done();

        double[][] table = field1.getDataTable();
        boolean hasNaN = false;
        int index = 0;
        while (!hasNaN || index < table.length) {
            hasNaN = Arrays.stream(table[index++]).filter(Double::isNaN).toArray().length > 0;
        }

        Assertions.assertFalse(hasNaN);
    }


    @Test
    void testSpeedImplicitGauss() throws IOException {
        double length = 1.0d;
        double time = 5.0d;
        int lengthSteps = 300;
        int timeSteps = 500;

        long m = System.currentTimeMillis();

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
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .done();

        System.out.println("Elapsed time: " + (double) (System.currentTimeMillis() - m));
    }

    @Test
    void testSpeedImplicitTridiagonal() throws IOException {
        double length = 1.0d;
        double time = 5.0d;
        int lengthSteps = 300;
        int timeSteps = 500;

        long m = System.currentTimeMillis();

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
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new TridiagonalMatrixAlgorithm()))
                .done();

        System.out.println("Elapsed time: " + (double) (System.currentTimeMillis() - m));
    }

    @Test
    void testSpeedGaussAndTridiagonal() throws IOException {
        double length = 1.0d;
        double time = 5.0d;
        int lengthSteps = 400;
        int timeSteps = 1000;

        BoundaryCondition condition = (x, t) -> {
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
        };

        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(condition)
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .done();

        Field field2 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(condition)
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new TridiagonalMatrixAlgorithm()))
                .done();

        Assertions.assertArrayEquals(field1.getDataTable()[1], field2.getDataTable()[1], DELTA);
        Assertions.assertArrayEquals(field1.getDataTable()[(int) (timeSteps * 0.5)], field2.getDataTable()[(int) (timeSteps * 0.5)], DELTA);
        Assertions.assertArrayEquals(field1.getDataTable()[lengthSteps], field2.getDataTable()[lengthSteps], DELTA);
    }

    @Test
    void testGeneralAndCrankNicolsonMethodEquality() throws IOException {
        double length = 0.75d;
        double time = 1.0d;
        int lengthSteps = 20;
        int timeSteps = 20;

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
                .applyDifferenceMethod(new GeneralFiniteDifferenceMethod(0.5d, new GaussMatrixAlgorithm()))
                .viewDataOn(new ConsoleDataPrinter())
                .done();

        Field field2 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(condition)
                .applyDifferenceMethod(new CrankNicolsonFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .done();

        Assertions.assertArrayEquals(field1.getDataTable()[1], field2.getDataTable()[1], DELTA);
        Assertions.assertArrayEquals(field1.getDataTable()[(int) (timeSteps * 0.5)], field2.getDataTable()[(int) (timeSteps * 0.5)], DELTA);
        Assertions.assertArrayEquals(field1.getDataTable()[lengthSteps], field2.getDataTable()[lengthSteps], DELTA);
    }

    @Test
    void testGeneralAndImplicitMethodEquality() throws IOException {
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
                .applyDifferenceMethod(new GeneralFiniteDifferenceMethod(1.0d, new GaussMatrixAlgorithm()))
                .done();

        Field field2 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(condition)
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .viewDataOn(new ConsoleDataPrinter())
                .done();

        Assertions.assertArrayEquals(field1.getDataTable()[1], field2.getDataTable()[1], DELTA);
        Assertions.assertArrayEquals(field1.getDataTable()[(int) (timeSteps * 0.5)], field2.getDataTable()[(int) (timeSteps * 0.5)], DELTA);
        Assertions.assertArrayEquals(field1.getDataTable()[lengthSteps], field2.getDataTable()[lengthSteps], DELTA);
    }

    @Test
    void testMethodsChainOverlay() throws IOException {
        double length = 1.0d;
        double time = 0.1d;
        int lengthSteps = 6;
        int timeSteps = 10;

        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(new OneDimensionHeatConductionBoundaryCondition())
                .applyDifferenceMethod(new CrankNicolsonFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .applyDifferenceMethod(new ImplicitFiniteDifferenceMethod(new GaussMatrixAlgorithm()))
                .applyDifferenceMethod(new ExplicitFiniteDifferenceMethod())
                .done();

        Field field2 = manager
                .init(length, time, lengthSteps, timeSteps)
                .applyBoundaryCondition(new OneDimensionHeatConductionBoundaryCondition())
                .applyDifferenceMethod(new ExplicitFiniteDifferenceMethod())
                .done();


        Assertions.assertArrayEquals(field1.getDataTable()[1], field2.getDataTable()[1], DELTA);
        Assertions.assertArrayEquals(field1.getDataTable()[(int) (timeSteps * 0.5)], field2.getDataTable()[(int) (timeSteps * 0.5)], DELTA);
        Assertions.assertArrayEquals(field1.getDataTable()[lengthSteps], field2.getDataTable()[lengthSteps], DELTA);
    }

}