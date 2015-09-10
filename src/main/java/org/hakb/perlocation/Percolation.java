package org.hakb.perlocation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[][] grid;
    int sideSize;
    int openedSites;
    WeightedQuickUnionUF weightedQuickUnionUF;

    /**
     * Create N-by-N grid, with all sites blocked
     *
     * @param sideSize is a size of array side.
     */

    public Percolation(int sideSize) {
        this.sideSize = sideSize;
        grid = new boolean[sideSize][sideSize];
        weightedQuickUnionUF = new WeightedQuickUnionUF(grid.length*grid.length );

    }

    /**
     * Open site (row i, column j) if it is not open already. Each site is either open or blocked
     *
     * @param i row number
     * @param j column number
     */
    public void open(int i, int j) {

        System.out.println("openedSites = " + ++openedSites);
        grid[i][j] = true;
        if (checkBound(i) && checkBound(j) && isRightOpen(i, j)) {
            weightedQuickUnionUF.union(i * j, ((++i) * j));
        } else if (checkBound(i) && checkBound(j) && isLeftOpen(i, j)) {
            weightedQuickUnionUF.union(i * j, ((--i) * j));
        } else if (checkBound(j) && checkBound(i) && isTopOpen(i, j)) {
            weightedQuickUnionUF.union(i * j, ((i) * (++j)));
        } else if (checkBound(j) && checkBound(i) && isBottomOpen(i, j)) {
            weightedQuickUnionUF.union(i * j, ((i) * (--j)));
        }
    }

    private boolean checkBound(int point) {
        if (point > 0 && point < sideSize - 1) {
            return true;
        }
        return false;
    }

    /**
     * @param i row array number
     * @param j column array number
     * @return is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {

        return grid[i][j];


    }

    /**
     * A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites
     *
     * @param i row number
     * @param j column number
     * @return is site (row i, column j) full
     */
    public boolean isFull(int i, int j) {
        for (int arrayTopNumber = 0; arrayTopNumber < (sideSize ); --arrayTopNumber) {
            if (weightedQuickUnionUF.connected(i * j, arrayTopNumber)) {
                return true;
            }
        }

        return false;
    }

    private boolean isRightOpen(int i, int j) {
        return isOpen(++i, j);
    }

    private boolean isLeftOpen(int i, int j) {
        return isOpen(--i, j);
    }

    private boolean isTopOpen(int i, int j) {
        return isOpen(i, ++j);
    }

    private boolean isBottomOpen(int i, int j) {
        return isOpen(i, --j);
    }


    /**
     * We say the system percolates if there is a full site in the bottom row.
     * In other words, a system percolates if we fill all open sites connected to the top
     * row and that process fills some open site on the bottom row.
     * (For the insulating/metallic materials example, the open sites
     * correspond to metallic materials, so that a system that percolates
     * has a metallic path from top to bottom, with full sites conducting.
     *
     * @return does the system percolate?
     */
    public boolean percolates() {
        for (int i = 0; i < sideSize; i++) {
            if (isFull(sideSize, i)) {
                return true;
            }
        }
        return false;

    }

    // test client (optional)
    public static void main(String[] args) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}
