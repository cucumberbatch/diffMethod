package problem;

import org.junit.jupiter.api.Test;
import problem.models.ReactionDiffusionProblem;
import problem.utils.ReactionDiffusionProblemBuilder;

import java.io.IOException;

class ReactionDiffusionProblemTest {

    @Test
    void testProblem1() throws IOException {
        final double A = 2.1d;
        final double B = 5.2d;
        final double Length = 1d;
        final double Time = 20d;
        final int xPoints = 10;
        final int tPoints = 10;
        final int targetDistribution = 0;

        ReactionDiffusionProblemBuilder builder = new ReactionDiffusionProblemBuilder();
        ReactionDiffusionProblem problem = builder
                .grid(Length, xPoints, Time, tPoints)
                .system( /* where u1 is a[0] and u2 is a[1] */
                        (x, a) -> A - (B + 1) * a[0] + a[0] * a[0] * a[1],
                        (x, a) -> B * a[0] - a[0] * a[0] * a[1])
                .initial(
                        (x, a) -> 2.0d,     // initial distribution of U1(x)
                        (x, a) -> 2.3d)     // initial distribution of U2(x)
                .diffusion(
                        4.4E-3d,            // D1
                        2.2E-3d)            // D2
                .calculate(targetDistribution)
                .display()
//                .display(new FileDataPrinter("ridiculous_test"))
                .build();
    }

}