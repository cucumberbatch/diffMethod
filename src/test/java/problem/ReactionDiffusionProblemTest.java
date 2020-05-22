package problem;

import org.junit.jupiter.api.Test;
import problem.models.ReactionDiffusionProblem;
import problem.utils.ReactionDiffusionProblemBuilder;

import java.io.IOException;

class ReactionDiffusionProblemTest {

    @Test
    void testProblem1() throws IOException {
        double A = 1d;
        double B = 1d;

        ReactionDiffusionProblemBuilder builder = new ReactionDiffusionProblemBuilder();
        ReactionDiffusionProblem problem = builder
                .system( /* where u1 is a[0] and u2 is a[1] */
                        (x, a) -> A - (B + 1) * a[0] + a[0] * a[0] * a[1],
                        (x, a) -> B * a[0] - a[0] * a[0] * a[1])
                .initial(
                        (x, a) -> 0, // initial distribution of U1(x)
                        (x, a) -> 0) // initial distribution of U2(x)
                .calculate()
                .display()
                .build();

//        problem.display();
    }

}