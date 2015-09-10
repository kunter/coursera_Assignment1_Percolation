package org.hakb.perlocation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static  int[] openedSitesBeforePerlocation;
    private static  int countExperiments;

    /**
     * perform T independent experiments on an N-by-N grid
     *
     * @param N
     * @param T
     */
    public PercolationStats(int N, int T) {
        countExperiments = T;
        openedSitesBeforePerlocation = new int[T];
        if (N < 0 || T < 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < countExperiments; i++) {
            Percolation percolation = new Percolation(N);
            int countOpenedSites = 0;

            while (!percolation.percolates()) {
                int row;
                int column;
                do {
                    row = (int) StdRandom.uniform(0, N);

                    column = (int) StdRandom.uniform(0, N);
                } while (percolation.isOpen(row, column));

                percolation.open(row, column);
                countOpenedSites++;

            }
            openedSitesBeforePerlocation[i] = countOpenedSites;
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

        return (m - (1.96 * d)) / (findSquareRoot((double)countExperiments ));
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

        return (m + (1.96 * d)) / (findSquareRoot((double)countExperiments ));
    }

    /**
     * test client (described below)
     *
     * @param args
     */
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(10, 10);
        System.out.println(String.format("mean is : %b, a:%f , e:%f",
                percolationStats.mean() == StdStats.mean(openedSitesBeforePerlocation),
                percolationStats.mean(),
                StdStats.mean(openedSitesBeforePerlocation)
        ));


    }

    public static double findSquareRoot(double number)
    {

        boolean isPositiveNumber = true;
        double g1;

        //if the number given is a 0
        if(number==0)
        {
            System.out.println("Square root of "+number+" = "+0);
        }

        //If the number given is a -ve number
        else if(number<0)
        {
            number=-number;
            isPositiveNumber = false;
        }

        //Proceeding to find out square root of the number
        double squareRoot = number/2;
        do
        {
            g1=squareRoot;
            squareRoot = (g1 + (number/g1))/2;
        }
        while((g1-squareRoot)!=0);

        //Displays square root in the case of a positive number
        if(isPositiveNumber)
        {
            System.out.println("Square roots of "+number+" are ");
            System.out.println("+"+squareRoot);
            System.out.println("-"+squareRoot);
            return  squareRoot;
        }
        //Displays square root in the case of a -ve number
        else
        {
            System.out.println("Square roots of -"+number+" are ");
            System.out.println("+"+squareRoot+" i");
            System.out.println("-"+squareRoot+" i");
            return squareRoot;
        }

    }
}
