import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] gridSites;
    private int gSize;
    private int topNode;
    private int bottomNode;
    private int numOpenSites;
    private WeightedQuickUnionUF unionFind;
    private int ufSize;

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        this.gSize = N;
        gridSites = new boolean[gSize][gSize];
        unionFind = new WeightedQuickUnionUF(2 + gSize * gSize); // add 2 here to allow space for top and bottom nodes
                                                                 // to test for percolation (when top and bottom are
                                                                 // connected, we percolatin)
        ufSize = unionFind.count();
        topNode = gSize * gSize; // topNode set to size^2 to be the "virtual top site"
        bottomNode = gSize * gSize + 1; // bottomNode set to the size^2+1 to create a "virtual bottom site"

        // connect the top row to the topNode, bottom row to the bottomNode
        for (int i = 0; i < gSize; i++) { // loop from 0 to i, where max i is the length of the row
            unionFind.union(topNode, get1DFrom2D(0, i)); // union the topNode with each top row value in the UF object

            unionFind.union(bottomNode, get1DFrom2D(gSize - 1, i)); // union the bottomNode with each bottow row value
                                                                    // in the UF object (gSize-1 to avoid out of bounds)
        }

    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        gridSites[row][col] = true;
        numOpenSites++;

        // here we check the surrounding sites to see if they are open, and if so, union

        // check above and below cells
        if (row > 0 && isOpen(row - 1, col)) { // if the site above is open (row-1 moves up 1 row), union it with
                                               // the current site
            unionFind.union(get1DFrom2D(row, col), get1DFrom2D(row - 1, col));
        }
        if (row < gSize && isOpen(row + 1, col)) { // if the site below is open (row+1 moves down 1 row), union it
                                                   // with the current site
            unionFind.union(get1DFrom2D(row, col), get1DFrom2D(row + 1, col));
        }

        // check left and right cells
        if (col > 0 && isOpen(row, col - 1)) { // if the site to the left is open (col-1 moves left 1 column), union
                                               // it with the current site
            unionFind.union(get1DFrom2D(row, col), get1DFrom2D(row, col - 1));
        }
        if (col < gSize && isOpen(row, col + 1)) { // if the site to the right is open (col+1 moves right 1 column),
                                                   // union it with the current site
            unionFind.union(get1DFrom2D(row, col), get1DFrom2D(row, col + 1));
        }

        // cases for top and bottom node
        if (row == 0) {
            unionFind.union(topNode, get1DFrom2D(row, col));
        }
        if (row == gSize - 1) {
            unionFind.union(bottomNode, get1DFrom2D(row, col));
        }

    }

    public boolean isOpen(int row, int col) {
        // is the site (row, col) open?
        if (row < 0 || row > gSize || col < 0 || col > gSize)
            throw new IndexOutOfBoundsException("row or col out of bounds");
        return gridSites[row][col]; // just a simple return of the boolean value of the site
    }

    public boolean isFull(int row, int col) {
        // is the site (row, col) full?
        return unionFind.connected(topNode, get1DFrom2D(row, col)); // tests if the site is connected to the top,
                                                                    // returns that boolean
    }

    public int numberOfOpenSites() {
        // number of open sites
        return numOpenSites;
    }

    public boolean percolates() {
        // does the system percolate?
        return unionFind.connected(topNode, bottomNode); // returns true once the top and bottom nodes are connected
    }

    private int get1DFrom2D(int row, int col) {
        return (gSize * row) + col; // this translates the row and column values to a 1d index for the unionfind
        // object (i.e. row = 2, col = 1, gSize = 3 -> (3*2) + 1 = 7, which would be
        // the index position in the UF object that represents the 2D grid site)
    }

    public static void main(String[] args) {
        // unit testing (suggested)
        Percolation p = new Percolation(4);
        // System.out.println(p.ufSize);
        p.open(0, 0);
        p.open(1, 0);
        p.open(2, 0);
        p.open(3, 0); // THIS GOES OUT OF BOUNDS
        System.out.println(p.unionFind.connected(12, 17));
    }
}