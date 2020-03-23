package problem.utils;

import problem.calculation.FiniteDifferenceMethod;
import problem.conditions.BoundaryCondition;
import problem.models.Field;
import problem.utils.view.DataViewer;

import java.io.IOException;

public class FieldManager {
    private Field field;

    public FieldManager init(double length, double time,int n, int m) {
        this.field = new Field(length, time, n, m);
        return this;
    }

    public FieldManager applyBoundaryCondition(BoundaryCondition condition) {
        field.applyBoundaryCondition(condition);
        return this;
    }

    public FieldManager applyDifferenceMethod(FiniteDifferenceMethod diffMethod) {
        field.applyDifferenceMethod(diffMethod);
        return this;
    }

    public FieldManager viewDataOn(DataViewer viewer) throws IOException {
        field.viewDataOn(viewer);
        return this;
    }

    public Field done() {
        return field;
    }
}
