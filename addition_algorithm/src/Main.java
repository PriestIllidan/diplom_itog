public class Main {
    public static void main(String[] args) {
        long[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // Example array
        long sum = ParallelSum.parallelSum(numbers);
        System.out.println("Sum: " + sum);
    }
}