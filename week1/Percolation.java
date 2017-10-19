import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF onlyTop;
    private final boolean[] opens;
    private final int start;
    private final int end;
    private final int n;
    private int openSites;

    public Percolation(int m) {
        if (m <= 0)
            throw new IllegalArgumentException("m out of bounds");
        n = m;
        int size = n * n + 2;
        uf = new WeightedQuickUnionUF(size);
        onlyTop = new WeightedQuickUnionUF(size - 1);
        this.opens = new boolean[size];
        start = size - 2;
        end = size - 1;

        opens[start] = true;
        opens[end] = true;

        for (int i = 0; i < n; i += 1) {
            uf.union(i, start);
            onlyTop.union(i, start);
        }

        for (int i = size - 3; i > size - 3 - n; i -= 1) {
            uf.union(i, end);
        }
        openSites = 0;
    }

    private int xyTo1D(int i, int j) {
        i -= 1;
        j -= 1;
        return n * i + j;
    }

    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n)
            throw new IllegalArgumentException("row out of bounds");
        if (col <= 0 || col > n)
            throw new IllegalArgumentException("col out of bounds");
        return opens[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n)
            throw new IllegalArgumentException("row out of bounds");
        if (col <= 0 || col > n)
            throw new IllegalArgumentException("col out of bounds");
        return isOpen(row, col) && onlyTop.connected(start, xyTo1D(row, col));
    }

    public void open(int row, int col) {
        if (row <= 0 || row > n)
            throw new IllegalArgumentException("row out of bounds");
        if (col <= 0 || col > n)
            throw new IllegalArgumentException("col out of bounds");
        if (!isOpen(row, col)) {
            openSites += 1;
            int index = xyTo1D(row, col);
            opens[index] = true;

            int down = row - 1;
            if (down > 0 && isOpen(down, col)) {
                uf.union(index, xyTo1D(down, col));
                onlyTop.union(index, xyTo1D(down, col));
            }

            int upper = row + 1;
            if (upper <= n && isOpen(upper, col)) {
                uf.union(index, xyTo1D(upper, col));
                onlyTop.union(index, xyTo1D(upper, col));
            }

            int right = col + 1;
            if (right <= n && isOpen(row, right)) {
                uf.union(index, xyTo1D(row, right));
                onlyTop.union(index, xyTo1D(row, right));
            }

            int left = col - 1;
            if (left > 0 && isOpen(row, left)) {
                uf.union(index, xyTo1D(row, left));
                onlyTop.union(index, xyTo1D(row, left));
            }
        }
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(start, end) && numberOfOpenSites() > 0;
    }
}