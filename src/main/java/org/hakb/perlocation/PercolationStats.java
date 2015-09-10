package org.hakb.perlocation;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    int[] openedSitesBeforePerlocation;
    int countExperiments;

    /**
     * perform T independent experiments on an N-by-N grid
     *
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        countExperiments = T;
        int m;

        openedSitesBeforePerlocation = new int[T];
        if (N < 0 || T < 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < countExperiments; i++) {
            Percolation percolation = new Percolation(N);
            int countOpendSites = 0;

            while (percolation.percolates()) {
                int row;
                int column;
                do {
                    row = (int) StdRandom.gaussian(0, N);
                    column = (int) StdRandom.gaussian(0, N);
                } while (!percolation.isOpen(row, column));

                percolation.open(row, column);
                countOpendSites++;

            }
            openedSitesBeforePerlocation[i] = countOpendSites;
        }
    }


    /**
     * sample mean of percolation threshold
     *
     * @return
     */
    public double mean() {
        int sum = 0;
        for (int i = 0; i < countExperiments; i++) {
            sum = sum + openedSitesBeforePerlocation[i];
        }


        return ((double) sum / countExperiments);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev() {
        double m = mean();
        double sum = 0;
        for (int i = 0; i < countExperiments; i++) {
            double part = ((openedSitesBeforePerlocation[i] - m));
            sum = sum + (part * part);
        }
        return sum;
    }

    /**
     * low  endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        double m = mean();
        double d = stddev();
        d = d / d;

        return (m - (1.96 * d)) / (countExperiments ^ (1 / 2));
    }

    /**
     * high endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        double m = mean();
        double d = stddev();
        d = d / d;

        return (m + (1.96 * d)) / (countExperiments ^ (1 / 2));
    }

    /**
     * test client (described below)
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
