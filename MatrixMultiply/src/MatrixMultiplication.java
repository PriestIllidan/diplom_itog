import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class MatrixMultiplication {

    private static class MatrixMultiplyTask extends RecursiveAction {
        private final double[][] A;
        private final double[][] B;
        private final double[][] C;
        private final int rowStart;
        private final int colStart;
        private final int size;

        private static final int THRESHOLD = 100;

        public MatrixMultiplyTask(double[][] A, double[][] B, double[][] C, int rowStart, int colStart, int size) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.rowStart = rowStart;
            this.colStart = colStart;
            this.size = size;
        }

        @Override
        protected void compute() {
            if (size <= THRESHOLD) {
                multiplySequentially();
            } else {
                int newSize = size / 2;
                invokeAll(
                        new MatrixMultiplyTask(A, B, C, rowStart, colStart, newSize),
                        new MatrixMultiplyTask(A, B, C, rowStart, colStart + newSize, newSize),
                        new MatrixMultiplyTask(A, B, C, rowStart + newSize, colStart, newSize),
                        new MatrixMultiplyTask(A, B, C, rowStart + newSize, colStart + newSize, newSize)
                );
            }
        }

        private void multiplySequentially() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < size; k++) {
                        C[rowStart + i][colStart + j] += A[rowStart + i][colStart + k] * B[rowStart + k][colStart + j];
                    }
                }
            }
        }
    }

    public static double[][] multiply(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        MatrixMultiplyTask task = new MatrixMultiplyTask(A, B, C, 0, 0, n);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
        return C;
    }
}