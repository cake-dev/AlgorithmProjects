import edu.princeton.cs.algs4.StdOut;
import java.util.Random;

public class HashProbing {

    public static void main(String[] args) {
        Random random = new Random();

        for (int N = 1000; N <= 1000000; N *= 10) {
            double mean = -999.99; // FIXME

            // do all the things

            Boolean A[] = new Boolean[2 * N];

            StdOut.println("Average probes for a miss with N=" + N + " is: " + mean);
        }
    }
}