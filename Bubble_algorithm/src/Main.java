import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 1, 2, 7, 4, 6};
        System.out.println("Original array: " + Arrays.toString(arr));
        ParallelBubbleSort.parallelBubbleSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}