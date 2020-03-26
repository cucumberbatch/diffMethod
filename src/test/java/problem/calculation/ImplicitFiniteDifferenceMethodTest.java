package problem.calculation;

import org.junit.jupiter.api.Test;
import problem.calculation.impl.ExplicitFiniteDifferenceMethod;
import problem.conditions.BoundaryCondition;
import problem.models.Field;
import problem.utils.FieldManager;
import problem.utils.view.impl.ConsoleDataViewer;

import java.io.IOException;

class ImplicitFiniteDifferenceMethodTest {

    private static final double DELTA = 1E-5;

    @Test
    void testImplicitFiniteDifferenceMethod1() throws IOException {
        FieldManager manager = new FieldManager();
        Field field1 = manager
                .init(1.0, 0.1, 2, 1)
                .applyBoundaryCondition((x, t) -> {
                    if (x == 0) {
                        return 1;
                    }
                    if (x == 0.5) {
                        return 1;
                    }
                    if (t == 0) {
                        return 5;
                    }

                    return 0;
                })
                .applyDifferenceMethod(new ExplicitFiniteDifferenceMethod())
                .viewDataOn(new ConsoleDataViewer())
                .done();
    }

}