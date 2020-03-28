package problem.calculation;

import org.junit.jupiter.api.Test;
import problem.calculation.impl.ExplicitFiniteDifferenceMethod;
import problem.models.Field;
import problem.utils.FieldManager;
import problem.utils.view.impl.ConsoleDataPrinter;

import java.io.IOException;

class ImplicitFiniteDifferenceMethodTest {

    private static final double DELTA = 1E-5;

    @Test
    void testImplicitFiniteDifferenceMethod1() throws IOException {
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
                .applyDifferenceMethod(new ExplicitFiniteDifferenceMethod())
                .viewDataOn(new ConsoleDataPrinter())
                .done();
    }

}