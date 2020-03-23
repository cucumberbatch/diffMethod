package problem.models;

public class Matrix {

    private double[][] table;
    private int n;
    private int m;

    public Matrix(int n, int m) {
        table = new double[n][m];
        this.n = n;
        this.m = m;
    }

    public Matrix(Matrix matrix) {
        this.table = new double[matrix.getN()][matrix.getM()];
        for (int n = 0; n < matrix.getN(); n++) {
            for (int m = 0; m < matrix.getM(); m++) {
                this.table[n][m] = matrix.value(n, m);
            }
        }
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public double value(int n, int m) {
        return table[n][m];
    }

    public void value(int n, int m, double value) {
        table[n][m] = value;
    }

    public double[][] getTable() {
        return table;
    }
}
