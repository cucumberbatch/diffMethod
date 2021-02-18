package problem;

import org.junit.jupiter.api.Test;
import problem.models.ReactionDiffusionProblem;
import problem.utils.ReactionDiffusionProblemBuilder;
import problem.utils.view.impl.FileDataPrinter;

import java.io.IOException;

class ReactionDiffusionProblemTest {

    @Test
    void testProblem1() throws IOException {
        final double A = 1.0d;
        final double B = 1.97d;
        final double Length = 1d;
        final double Time = 4.0d;
        final int xPoints = 10;
        final int tPoints = 150;
        final int targetDistribution = 0;

        ReactionDiffusionProblemBuilder builder = new ReactionDiffusionProblemBuilder();
        ReactionDiffusionProblem problem = builder
                .grid(Length, xPoints, Time, tPoints)
                .system(
                        (x, u) -> A - (B + 1) * u[0] + u[0] * u[0] * u[1],
                        (x, u) -> B * u[0] - u[0] * u[0] * u[1])
                .initial(
                        (x, a) -> 0.0d,     // initial distribution of U1(x)
                        (x, a) -> 0.0d)     // initial distribution of U2(x)
                .diffusion(
                        0.1d, //4.4e-3d,            // D1
                        0.07d) //2.2e-3d)            // D2
                .calculate(0)
                .display(new FileDataPrinter("ridiculous_test_u1new2"))
                .calculate(1)
                .display(new FileDataPrinter("ridiculous_test_u2new2"))
                .display()
                .build();
    }

}