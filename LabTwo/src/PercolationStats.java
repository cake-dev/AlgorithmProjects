import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Percolation[] percolations;
    private int numExperiments;

    public PercolationStats(int N, int T) {
        // perform T independent experiments on an N-by-N grid
        percolations = new Percolation[T];
        numExperiments = T;
        for (int i = 0; i < T; i++) {
            // System.out.println("Running experiment " + i);
            Percolation p = new Percolation(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p.open(row, col);
            }
            percolations[i] = p;
        }
    }

    public double mean() {
        // return the mean of the percolation thresholds

        double[] thresholds = new double[percolations.length];
        for (int i = 0; i < percolations.length; i++) {
            thresholds[i] = (double) percolations[i].numberOfOpenSites()
                    / ((double) percolations[i].gSize * (double) percolations[i].gSize);
        }
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        // return the standard deviation of the percolation thresholds
        double[] thresholds = new double[percolations.length];
        for (int i = 0; i < percolations.length; i++) {
            thresholds[i] = (double) percolations[i].numberOfOpenSites()
                    / ((double) percolations[i].gSize * (double) percolations[i].gSize);
        }
        return StdStats.stddev(thresholds);
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        double sigma = this.stddev();
        double mu = this.mean();
        return mu - (1.96 * sigma / Math.sqrt(this.numExperiments));
    }

    public double confidenceHigh() {
        // low endpoint of 95% confidence interval
        double sigma = this.stddev();
        double mu = this.mean();
        return mu + (1.96 * sigma / Math.sqrt(this.numExperiments));
    }

    public static void main(String[] args) {
        // Not required for the API, but useful to test if
        // your code is doing reasonable things
        PercolationStats st = new PercolationStats(10, 100);
        System.out.println(st.percolations.length + " experiments run on " + st.percolations[0].gSize + "x"
                + st.percolations[0].gSize + " grid");
        System.out.println("Sample mean of percolation threshold:       " + st.mean());
        System.out.println("Sample std dev of percolation threshold:    " + st.stddev());
        System.out.println("95% confidence low:                         " + st.confidenceLow());
        System.out.println("95% confidence high:                        " + st.confidenceHigh());

        // ...
    }
}
