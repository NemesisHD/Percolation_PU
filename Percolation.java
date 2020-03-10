/* *****************************************************************************
 *  Name:Prithvi Rao
 *  Date:19/05/2019
 *  Description:Percolation(Algorithms - I)
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF obj;
    private int gz;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        gz = N;
        grid = new boolean[N][N];
        obj = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        for (int i = 0; i < gz; i++) {
            obj.union(N * N + 1, N * (N - 1) + i);
            obj.union(N * N, i);
        }
    }

    public void open(int row, int col) {
        int r = row - 1;
        int c = col - 1;
        if (row <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (row > gz) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (col <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        if (col > gz) {
            throw new IllegalArgumentException("Invalid input");
        }
        grid[r][c] = true;
        //up
        if (r - 1 >= 0 && isOpen(row - 1, col)) {
            obj.union(convert(row, col), convert(row - 1, col));
        }
        //down
        if (r + 1 < gz && isOpen(row + 1, col)) {
            obj.union(convert(row, col), convert(row + 1, col));
        }
        //left
        if (c - 1 >= 0 && isOpen(row, col - 1)) {
            obj.union(convert(row, col), convert(row, col - 1));
        }
        //right
        if (c + 1 < gz && isOpen(row, col + 1)) {
            obj.union(convert(row, col), convert(row, col + 1));
        }


    }

    public boolean isOpen(int i, int j) {

        return grid[i - 1][j - 1];

    }

    private int convert(int i, int j) {
        return (i - 1) * gz + j - 1;
    }

    public boolean isFull(int row, int col) {
        if (isOpen(row, col)) {
            if (obj.connected(convert(row, col), gz * gz)) {
                return true;
            }
        }
        return false;
    }

    public boolean percolates() {

        if (obj.connected(gz * gz, gz * gz + 1)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < gz; i++) {
            for (int j = 0; j < gz; j++) {
                if (grid[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }


}
