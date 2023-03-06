import edu.princeton.cs.algs4.MinPQ;

public class MyHeapSort {

    public static void simpleSort(Integer[] A) {
        int N = A.length;
        // create a heap, insert N things
        StdOut.println("  Simple sort");
        StdOut.print("    Construct:");
        MinPQ<Integer> heap = new MinPQ<Integer>();

        long startTime = System.nanoTime();
        // insert n items into heap
        for (int i = 0; i < N; i++) {
            heap.insert(A[i]);
        }
        StdOut.println("\t\t" + (System.nanoTime() - startTime));

        StdOut.print("    Sort:");
        startTime = System.nanoTime();
        // delete n items from heap
        for (int i = 0; i < N; i++) {
            heap.delMin();
        }
        StdOut.println("\t\t" + (System.nanoTime() - startTime));
    }

    public static void heapSort(Integer[] A) {
        int n = A.length;

        StdOut.println("  Heap sort");
        StdOut.print("    Construct:");

        long startTime = System.nanoTime();
        // Add code to heapify. You can find this in Heap.class (sort function)
        MinPQ<Integer> heap2 = new MinPQ<Integer>(A);
        StdOut.println("\t\t" + (System.nanoTime() - startTime));

        StdOut.print("    Sort:");
        n = A.length;
        startTime = System.nanoTime();
        // Add code to sort from that heap. You can find this in Heap.class (sort
        // function)
        // delMin the top, then heapify
        for (int i = 0; i < n; i++) {
            heap2.delMin();
            for (int k = n / 2; k >= 1; k--)
                sink(A, k, n);
            while (n > 1) {
                exch(A, 1, n--);
                sink(A, 1, n);
            }
        }

        StdOut.println("\t\t" + (System.nanoTime() - startTime));
    }

    private static Integer[] randomArray(int size) {
        Integer[] result = new Integer[size];
        // fill result with random integers
        for (int i = 0; i < size; i++)
            result[i] = (StdRandom.uniform(size) * size);
        return result;
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }

    public static void main(String args[]) {

        Integer[] arr;

        StdOut.println("Input size\t\tTime total");

        arr = randomArray((int) Math.pow(10, 3));
        StdOut.println(arr.length);
        simpleSort(arr);
        heapSort(arr);

        arr = randomArray((int) Math.pow(10, 5));
        StdOut.println(arr.length);
        simpleSort(arr);
        heapSort(arr);

        arr = randomArray((int) Math.pow(10, 7));
        StdOut.println(arr.length);
        simpleSort(arr);
        heapSort(arr);

    }

    /* from Heap class */
    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = swap;
    }

    private static void sink(Comparable[] pq, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1))
                j++;
            if (!less(pq, k, j))
                break;
            exch(pq, k, j);
            k = j;
        }
    }
}