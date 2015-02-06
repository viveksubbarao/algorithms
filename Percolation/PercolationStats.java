/****************************************************************************
 *  @author Vivek Subbarao
 *  Date: 01/27/2015
 *  Compilation:  javac PercolationStats.java
 *  Execution:  java PercolationStats 100 200
 *  Dependencies: Percolation.java StdRandom.java StdStats.java StdOut.java
 *                  Math.java
 *
 *  Various statistical measurements of Percolation threshold
 *
 ****************************************************************************/

/**
 *  The <tt>PercolationStats</tt> class encapsulates methods to find the mean
 *  standard deviation and high low confidence interval of the percolation
 *  threshold for a given grid size N and trial T.
 *
 *  This class makes use of Percolation class which implements the main
 *  percolation methods and uses Weighted Quick Union-find algorithm to
 *  find whether a system percolates or not.
 */
public class PercolationStats {
    private Percolation [] percolationObjArray;
    private int numTrials, gridDimension;
    private double[] percolationThresholds; // Holds the percolation threshold
                                            // for each trial.

    public PercolationStats(int N, int T)
    {
        percolationObjArray = new Percolation[T];
        for (int i = 0; i < T; i++) {
            percolationObjArray[i] = new Percolation(N);
        }
        gridDimension = N;
        numTrials = T;
        percolationThresholds = new double[T];
    }

    /**
     * Carries out T experiments on a grid of size N. Fills up
     * the percolationThresholds array after each trial.
     */
    private void calculatePercolationThresholds()
    {
        int i = 0, j = 0, numOpenSites = 0;

        for (int k = 0; k < numTrials; k++) {
            while (!percolationObjArray[k].percolates()) {
                /*
                 * The random numbers for use as rows and columns must
                 * be in the range of 1 and N. The uniform method used
                 * below returns a random number in the interval
                 * [1, gridDimension + 1)
                 */
                i = StdRandom.uniform(1, gridDimension + 1);
                j = StdRandom.uniform(1, gridDimension + 1);
                if (!percolationObjArray[k].isOpen(i, j)) {
                    percolationObjArray[k].open(i, j);
                    numOpenSites++;
                }
            }
            percolationThresholds[k] =
                    numOpenSites / (double) (gridDimension * gridDimension);
            numOpenSites = 0;
        }
    }

    /**
     * Calculate mean of the T percolation thresholds
     * @return mean of all T percolation thresholds
     */
    public double mean()
    {
        return StdStats.sum(percolationThresholds) / numTrials;
    }

    /**
     * Calculate sample standard deviation of the T percolation thresholds
     * @return sample standard deviation of the percolation thresholds
     */
    public double stddev()
    {
        return StdStats.stddev(percolationThresholds);
    }

    /**
     * Calculate confidence low value for the T percolation thresholds
     * @return confidence low value
     */
    public double confidenceLo()
    {
        double thresholdMean = mean();
        double thresholdStdDev = stddev();

        return (thresholdMean - ((1.96 * thresholdStdDev) / Math.sqrt(numTrials)));
    }

    /**
     * Calculate confidence high value for the T percolation thresholds
     * @return confidence high value
     */
    public double confidenceHi()
    {
        double thresholdMean = mean();
        double thresholdStdDev = stddev();

        return (thresholdMean + ((1.96 * thresholdStdDev) / Math.sqrt(numTrials)));
    }

    public static void main(String[] args)
    {
        double mean, stdDev, confLo, confHi;

        if (args.length != 2)
            throw new IllegalArgumentException("Invalid number of arguments");

        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));

        p.calculatePercolationThresholds();
        mean = p.mean();
        stdDev = p.stddev();
        confLo = p.confidenceLo();
        confHi = p.confidenceHi();

        StdOut.println("mean = " + mean);
        StdOut.println("stddev = " + stdDev);
        StdOut.println("95% confidence interval = " + confLo + ", " + confHi);
    }
}
