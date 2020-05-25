package problem.utils;

import math.Function;
import problem.models.ReactionDiffusionProblem;
import problem.utils.view.DataPrinter;

import java.io.IOException;

public class ReactionDiffusionProblemBuilder {
    private ReactionDiffusionProblem problem;


    public ReactionDiffusionProblemBuilder grid(double length, int lengthPoints, double time, int timePoints) {
        double dx = length / lengthPoints;
        double dt = time / timePoints;
        this.grid(new FieldConfiguration(new double[lengthPoints + 1][timePoints + 1], dx, dt));
        return this;
    }

    public ReactionDiffusionProblemBuilder grid(FieldConfiguration configuration) {
        problem = new ReactionDiffusionProblem(configuration);
        return this;
    }

    public ReactionDiffusionProblemBuilder system(Function... functions) {
        this.problem.setFunctions(functions);
        return this;
    }

    public ReactionDiffusionProblemBuilder initial(Function... conditions) {
        this.problem.setConditions(conditions);
        return this;
    }

    public ReactionDiffusionProblemBuilder calculate() {
        this.problem.calculate();
        return this;
    }

    public ReactionDiffusionProblemBuilder display() throws IOException {
        this.problem.display();
        return this;
    }

    public ReactionDiffusionProblemBuilder display(DataPrinter printer) throws IOException {
        this.problem.display(printer);
        return this;
    }

    public ReactionDiffusionProblem build() {
        return problem;
    }
}
