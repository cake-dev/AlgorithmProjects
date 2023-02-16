public class MaxPQ {
    private int[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = new int[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(int x) {
        pq[++N] = x;
        swim(N);
    }

    public int delMax() {
        int max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = 0;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i] < pq[j];
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public static void main(String[] args) {
        MaxPQ pq = new MaxPQ(10);
        pq.insert(1);
        pq.insert(2);
        pq.insert(3);
        pq.insert(4);
        pq.insert(5);
        pq.insert(6);
        pq.insert(7);
        pq.insert(8);
        pq.insert(9);
        pq.insert(10);
        // print a graphical representation of the heap
        for (int i = 1; i <= 10; i++) {
            System.out.print(pq.pq[i] + " ");
            // System.out.print(pq.delMax() + " ");
        }
        System.out.println();
    }
}
