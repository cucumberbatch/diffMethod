package problem.models;

public class Matrix {

	public double[][] table;
	private int N;
	private int M;

	public Matrix(int N, int M) {
		table = new double[N][M];
		this.N = N;
		this.M = M;
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
		return M;
	}

	public int getN() {
		return N;
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
