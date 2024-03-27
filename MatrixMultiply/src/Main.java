public class Main {
    public static void main(String[] args) {
        double[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] B = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
        double[][] result = MatrixMultiplication.multiply(A, B);

        // Print result
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}