import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (n <= 0)
            throw new IllegalArgumentException("n < 0");
        if (trials <= 0)
            throw new IllegalArgumentException("trials < 0");
        int t = 0;
        double[] results = new double[trials];
        while (t < trials) {
            int[] sitesToOpen = new int[n * n];

            for (int i = 0; i < n * n; i += 1) {
                sitesToOpen[i] = i;
            }

            StdRandom.shuffle(sitesToOpen);

            Percolation p = new Percolation(n);
            int i = 0;
            while (i < n * n && !p.percolates()) {
                int[] indexes = oneDToxy(sitesToOpen[i], n);
                p.open(indexes[0], indexes[1]);
                i += 1;
            }
            results[t] = p.numberOfOpenSites() * 1.0 / (n * n);
            t += 1;
        }

        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);

        double sqrTrials = 1.96 * stddev / Math.sqrt(trials);

        confidenceLo = mean - sqrTrials;
        confidenceHi = mean + sqrTrials;
    }

    private int[] oneDToxy(int d, int n) {
        int i = d / n;
        int j = d % n;
        int[] resp = { i + 1, j + 1 };
        return resp;
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.print("mean                    = ");
        System.out.println(ps.mean());
        System.out.print("stddev                  = ");
        System.out.println(ps.stddev());
        System.out.print("95% confidence interval = [");
        System.out.print(ps.confidenceLo());
        System.out.print(", ");
        System.out.print(ps.confidenceHi());
        System.out.println("]");
    }
}
