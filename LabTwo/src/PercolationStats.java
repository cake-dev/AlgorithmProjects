import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Percolation[] percolations;

    public PercolationStats(int N, int T) {
        // perform T independent experiments on an N-by-N grid
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(N);
        }
    }

    public double mean() {
        // sample mean of percolation threshold
        return -9999.9999; // FIXME
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return -9999.9999; // FIXME
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return -9999.9999; // FIXME
    }

    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return -9999.9999; // FIXME
    }

    public static void main(String[] args) {
        // Not required for the API, but useful to test if
        // your code is doing reasonable things
        PercolationStats st = new PercolationStats(10, 10);
        // ...
    }
}
