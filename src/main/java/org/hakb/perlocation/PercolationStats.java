package org.hakb.perlocation;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private static double[] openedSitesBeforePerlocation;
    private static int countExperiments;

    /**
     * Perform T independent experiments on an N-by-N grid
     *
     * @param N - size of side
     * @param T - count of experiments
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("wrong args N:" + N + "  T:" + T);
        }
        countExperiments = T;
        openedSitesBeforePerlocation = new double[T];
        for (int i = 0; i < countExperiments; i++) {
            Percolation percolation = new Percolation(N);
            int countOpenedSites = 0;

            while (countOpenedSites < N || !percolation.percolates()) {

                int row;
                int column;
                do {
                    row = StdRandom.uniform(1, N + 1);
                    column = StdRandom.uniform(1, N + 1);
                } while (percolation.isOpen(row, column));
                percolation.open(row, column);
                countOpenedSites++;
            }
            openedSitesBeforePerlocation[i] = (double) countOpenedSites / (N * N);
        }
    }


    /**
     * sample mean of percolation threshold
     *
     * @return
     */
    public double mean() {
        double sum = 0;
        for (int i = 0; i < countExperiments; i++) {
            sum = sum + (openedSitesBeforePerlocation[i]);
        }
        return (sum / countExperiments);
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

        return Math.sqrt(sum / (countExperiments - 1));
    }

    /**
     * low  endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();

        return (mean - ((1.96 * stddev)) / (Math.sqrt((double) countExperiments)));
    }

    /**
     * high endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        double m = mean();
        double d = stddev();


        return (m + ((1.96 * d)) / (Math.sqrt((double) countExperiments)));
    }

    /**
     * test client (described below)
     *
     * @param args
     */
    public static void main(String[] args) {
//        Percolation percolation=new Percolation(2);
//        percolation.open(0,0);
//        percolation.open(0,1);
//        percolation.open(1,1);
//
//
//        System.out.println("percolation.percolates() = " + percolation.percolates());
//
//
//        System.out.println(percolation.isOpen(0, 0));
//        System.out.println(percolation.isOpen(0,1));
//        System.out.println(percolation.isOpen(1,1));
//        System.out.println("is connected : "+  percolation.getWeightedQuickUnionUF().connected(0,3));;
        PercolationStats percolationStats = new PercolationStats(20, 10);
        System.out.println("percolationStats.mean() = " + percolationStats.mean());
        System.out.println("percolationStats.stddev() = " + percolationStats.stddev());
        System.out.println("percolationStats.confidenceLo() = " + percolationStats.confidenceLo());
        System.out.println("percolationStats.confidenceHi() = " + percolationStats.confidenceHi());

//        System.out.println("percolationStats = " + StdStats.mean(openedSitesBeforePerlocation));
        ;


    }
}
