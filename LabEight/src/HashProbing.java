import java.util.Random;

public class HashProbing {

    public static void main(String[] args) {
        Random random = new Random();
        double trials = 10000;

        for (int N = 1000; N <= 1000000; N *= 10) {
            double mean = -999.99;
            double misses = 0.0;
            double nval = (double) N;
            double a_len = nval * 1.25;

            // do all the things

            boolean A[] = new boolean[(int) a_len];

            for (int i = 0; i < N; i++) {
                int insertKey = random.nextInt((int) a_len);
                // inserting N keys using linear probing
                // creates a random key and moves it to the right until an open spot is found
                // increment key by 1 and then take modulo of array length to prevent out of
                // bounds
                while (A[insertKey]) {
                    insertKey = (insertKey + 1) % ((int) a_len);
                }
                A[insertKey] = true;
            }
            for (int i = 0; i < trials; i++) {
                // increment misses at the start of each trial. This is because the search key
                // is not in the table
                misses++;
                int searchKey = random.nextInt((int) a_len);
                // searching for keys using linear probing
                // a hit in this case is meant to be a miss (assumption is that none of these
                // search keys are
                // in the table)
                while (A[searchKey]) {
                    misses++;
                    searchKey = (searchKey + 1) % ((int) a_len);
                }
            }
            mean = misses / trials;

            StdOut.println("Average probes for a miss with N=" + N + " is: " + mean);
        }
    }
}