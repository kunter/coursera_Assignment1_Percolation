package org.hakb.perlocation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int sideSize;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean bottomRowIsOpened;


    /**
     * Create N-by-N grid, with all sites blocked
     *
     * @param sideSize is a size of array side.
     * @throws IllegalArgumentException if  sideSize <= 0
     */

    public Percolation(int sideSize) {
        if (sideSize <= 0) {
            throw new IllegalArgumentException("Wrong N: " + sideSize);
        }
        this.sideSize = sideSize;
        grid = new boolean[sideSize][sideSize];
        weightedQuickUnionUF = new WeightedQuickUnionUF(grid.length * grid.length);

    }

    /**
     * Open site (row i, column j) if it is not open already. Each site is either open or blocked
     *
     * @param i row number
     * @param j column number
     * @throws IndexOutOfBoundsException if  0< i <= N  or 0< j <= N
     */
    public void open(int i, int j) {
        validateBounds(i, j);
        checkBottomRow(i);
        grid[i - 1][j - 1] = true;

        int offsetCol = j - 1;
        int offsetRow = i - 1;

        if (j < (sideSize) && isRightOpen(i, j)) {
            weightedQuickUnionUF.union((offsetRow * sideSize) + offsetCol, ((offsetRow) * sideSize) + (offsetCol + 1));
        }
        if (j > 1 && isLeftOpen(i, j)) {
            weightedQuickUnionUF.union((offsetRow * sideSize) + offsetCol, ((offsetRow) * sideSize) + (offsetCol - 1));
        }
        if (i > 1 && isTopOpen(i, j)) {
            weightedQuickUnionUF.union((offsetRow * sideSize) + offsetCol, ((offsetRow - 1) * sideSize) + (offsetCol));
        }
        if (i < sideSize && isBottomOpen(i, j)) {
            weightedQuickUnionUF.union((offsetRow * sideSize) + offsetCol, ((1 + offsetRow) * sideSize) + (offsetCol));
        }
    }

    private void checkBottomRow(int row) {
        if(row==sideSize){
            bottomRowIsOpened=true;
        }
    }

    private void validateBounds(int row, int column) {
        validateBound(row);
        validateBound(column);
    }

    private void validateBound(int value) {
        if (!((value >= 1) && (value <= sideSize))) {
            throw new IndexOutOfBoundsException("valus is: " + value);
        }
    }


    /**
     * @param i row array number
     * @param j column array number
     * @return is site (row i, column j) open?
     * @throws IndexOutOfBoundsException if  0<= i < N  or 0<= j < N
     */
    public boolean isOpen(int i, int j) {
        validateBounds(i, j);
        return grid[i - 1][j - 1];


    }

    /**
     * A full site is an open site that can be connected to an open site in the top row
     * via a chain of neighboring (left, right, up, down) open sites
     *
     * @param i row number
     * @param j column number
     * @return is site (row i, column j) full
     * @throws IndexOutOfBoundsException if  0<= i < N  or 0<= j < N
     */
    public boolean isFull(int i, int j) {
        validateBounds(i, j);
        if (!isOpen(i, j)) {
            return false;
        }
        int offsetCol = j - 1;
        int offsetRow = i - 1;
        if (i == 1) {
            return true;
        } else {
            for (int topColumn = 0; topColumn < (sideSize); topColumn++) {

                if (isOpen(1, topColumn + 1) && weightedQuickUnionUF.connected(topColumn, (offsetRow * sideSize) + offsetCol)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRightOpen(int row, int col) {
        return isOpen(row, col + 1);
    }

    private boolean isLeftOpen(int row, int col) {
        return isOpen(row, col - 1);
    }

    private boolean isTopOpen(int row, int col) {
        return isOpen(row - 1, col);
    }

    private boolean isBottomOpen(int row, int col) {
        return isOpen(row + 1, col);
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
        if (!bottomRowIsOpened) return false;
        for (int j = 1; j <= sideSize; j++) {
            if (isOpen(sideSize, j) && isFull(sideSize, j)) {
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
