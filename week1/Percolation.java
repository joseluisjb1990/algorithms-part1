import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Scanner;

public class Percolation {
    WeightedQuickUnionUF uf;
    boolean[] opens;
    int start;
    int end;
    int n;

    private int xyTo1D(int i, int j) {
        i -= 1;
        j -= 1;
        return n * i + j;
    }

    public Percolation(int m) {
        n = m;
        int size = n * n + 2;
        uf = new WeightedQuickUnionUF(size);
        this.opens = new boolean[size];
        start = size - 2;
        end = size - 1;

        opens[start] = true;
        opens[end] = true;

        for (int i = 0; i < n; i += 1) {
            uf.union(i, start);
        }

        for (int i = size - 3; i > size - 3 - n; i -= 1) {
            uf.union(i, end);
        }
    }

    public boolean isOpen(int row, int col) {
        return opens[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        return !isOpen(row, col);
    }

    public void open(int row, int col) {
        if (isFull(row, col)) {
            int index = xyTo1D(row, col);
            opens[index] = true;

            int down = row - 1;
            if (down > 0 && isOpen(down, col)) {
                uf.union(index, xyTo1D(down, col));
            }

            int upper = row + 1;
            if (upper <= n && isOpen(upper, col)) {
                uf.union(index, xyTo1D(upper, col));
            }

            int right = col + 1;
            if (right <= n && isOpen(row, right)) {
                uf.union(index, xyTo1D(row, right));
            }

            int left = col - 1;
            if (left > 0 && isOpen(row, left)) {
                uf.union(index, xyTo1D(row, left));
            }
        }
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < n * n; i += 1) {
            if (opens[i]) {
                count += 1;
            }
        }
        return count;
    }

    public boolean percolates() {
        return uf.connected(start, end);
    }
}
