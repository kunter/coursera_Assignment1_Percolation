package org.hakb.perlocation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[][] grid;

    // create N-by-N grid, with all sites blocked
    public Percolation(int numberOfGrid) {
        grid = new boolean[numberOfGrid][numberOfGrid];
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        grid[i][j] = true;
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return grid[i][j];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
//        if (isOpen(i,j)){
//
//        }
        throw new UnsupportedOperationException("Not ready yet");
    }

    // does the system percolate?
    public boolean percolates() {
        throw new UnsupportedOperationException("Not ready yet");
    }

    // test client (optional)
    public static void main(String[] args) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}
