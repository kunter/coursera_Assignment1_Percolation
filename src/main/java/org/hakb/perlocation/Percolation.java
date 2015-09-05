package org.hakb.perlocation;

public class Percolation {
    boolean[][] grid;

    /**
     * Create N-by-N grid, with all sites blocked
     *
     * @param sizeOfSide is a size of array side.
     */

    public Percolation(int sizeOfSide) {
        grid = new boolean[sizeOfSide][sizeOfSide];
    }

    /**
     * Open site (row i, column j) if it is not open already. Each site is either open or blocked
     *
     * @param i row number
     * @param j column number
     */
    public void open(int i, int j) {
        grid[i][j] = true;
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

        throw new UnsupportedOperationException("Not ready yet");
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
        throw new UnsupportedOperationException("Not ready yet");
    }

    // test client (optional)
    public static void main(String[] args) {
        throw new UnsupportedOperationException("Not ready yet");
    }
}
