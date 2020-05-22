package problem.utils;

import math.Function;
import problem.models.ReactionDiffusionProblem;
import problem.utils.view.DataPrinter;

import java.io.IOException;

public class ReactionDiffusionProblemBuilder {
    private ReactionDiffusionProblem problem;

    public ReactionDiffusionProblemBuilder() {
        problem = new ReactionDiffusionProblem();
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
