package problem;

import org.junit.jupiter.api.Test;
import problem.models.ReactionDiffusionProblem;
import problem.utils.ReactionDiffusionProblemBuilder;
import problem.utils.view.impl.FileDataPrinter;

import java.io.IOException;

import static java.lang.Math.sin;

class ReactionDiffusionProblemTest {

    @Test
    void testProblem1() throws IOException {
        final double A = 1d;
        final double B = 1d;
        final double Length = 5d;
        final double Time = 1d;
        final int xPoints = 20;
        final int tPoints = 20;

        ReactionDiffusionProblemBuilder builder = new ReactionDiffusionProblemBuilder();
        ReactionDiffusionProblem problem = builder
                .grid(Length, xPoints, Time, tPoints)
                .system( /* where u1 is a[0] and u2 is a[1] */
                        (x, a) -> A - (B + 1) * a[0] + a[0] * a[0] * a[1],
                        (x, a) -> B * a[0] - a[0] * a[0] * a[1])
                .initial(
                        (x, a) -> sin(x), // initial distribution of U1(x)
                        (x, a) -> x) // initial distribution of U2(x)
                .calculate()
                .display(new FileDataPrinter("fourier_test"))
                .build();

//        problem.display();
    }

}