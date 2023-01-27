public class WeightedQuickUnion {
    private int[] id;
    private int[] sz;

    public WeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) {
            return;
        }
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        System.out.println(uf.connected(8, 9));
        System.out.println(uf.connected(5, 0));
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(7, 3);
        System.out.println(uf.connected(5, 4));
        StdOut.println(uf.connected(5, 4));
    }
}