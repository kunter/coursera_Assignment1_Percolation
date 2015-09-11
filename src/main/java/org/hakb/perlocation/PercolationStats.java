package org.hakb.perlocation;

import edu.princeton.cs.algs4.StdRandom;

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
            System.out.println("percolation = " + percolation.percolates() + "opened: " + countOpenedSites);
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

        return findSquareRoot(sum/(countExperiments-1));
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
        PercolationStats percolationStats = new PercolationStats(100, 100);





    }

    public static double findSquareRoot(double number)
    {

        boolean isPositiveNumber = true;
        double g1;

        //if the number given is a 0
        if(number==0)
        {
            return 0;
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

            return  squareRoot;
        }
        //Displays square root in the case of a -ve number
        else
        {

            return squareRoot;
        }

    }
}
