import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelBubbleSort {

    private static class BubbleSortTask extends RecursiveAction {
        private final int[] array;
        private final int low;
        private final int high;

        private BubbleSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low >= high) {
                return;
            }

            for (int i = low; i < high; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }

            BubbleSortTask leftTask = new BubbleSortTask(array, low, high - 1);
            BubbleSortTask rightTask = new BubbleSortTask(array, low + 1, high);

            leftTask.fork();
            rightTask.compute();
            leftTask.join();
        }
    }

    public static void parallelBubbleSort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new BubbleSortTask(array, 0, array.length - 1));
        pool.shutdown();
    }
}