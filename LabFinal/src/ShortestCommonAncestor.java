import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ShortestCommonAncestor {

    /* 
    the class ShortestCommonAncestor represents a data type for finding a shortest common ancestor of two vertices in a rooted DAG.
    A shortest common ancestor is a vertex v for which the sum of the length of the shortest ancestral path from v to each of the two vertices is minimized.
    The length of a path between two vertices is the number of edges in the path.
    */ 

    // Instance variables

    private Digraph G; // becomes a copy of the input digraph
    private BreadthFirstDirectedPaths[] bf_searches; // need multiple bfs objects, one for each vertex

    // private int counter;

    // constructor takes a rooted DAG as argument
    public ShortestCommonAncestor(Digraph G) {
        this.G = G;
        this.bf_searches = new BreadthFirstDirectedPaths[G.V()]; // create a bfs object for each vertex so all shortest common ancestors can be found
        // this.counter = 1;
    }

    // length of shortest ancestral path between v and w
    public int length(int v, int w) {
        // create bfs objects for each node if they don't exist yet
        if(bf_searches[v] == null) {
            bf_searches[v] = new BreadthFirstDirectedPaths(G, v);
        }
        if(bf_searches[w] == null) {
            bf_searches[w] = new BreadthFirstDirectedPaths(G, w);
        }
        int len = Integer.MAX_VALUE;
        // iterate through nodes and find the shortest BreadthFirstDirectedPath from v to all other vertices and w to all other vertices
        for(int i = 0; i < G.V(); i++) {
            if(bf_searches[v].hasPathTo(i) && bf_searches[w].hasPathTo(i)) {
                int temp_len = bf_searches[v].distTo(i) + bf_searches[w].distTo(i);
                if(temp_len < len) {
                    len = temp_len;
                }
            }
        }
        // System.out.println("length() calls counter: " + counter++);
        bf_searches[v] = null; // reset the bfs objects
        bf_searches[w] = null;
        return len == Integer.MAX_VALUE ? -999 : len; // return -999 if no path exists
    }

    // a shortest common ancestor of vertices v and w
    public int ancestor(int v, int w) {
        // basically the same code as length, but save the index at which the shortest path is found
        if(bf_searches[v] == null) {
            bf_searches[v] = new BreadthFirstDirectedPaths(G, v);
        }
        if(bf_searches[w] == null) {
            bf_searches[w] = new BreadthFirstDirectedPaths(G, w);
        }
        int len = Integer.MAX_VALUE;
        int ancestor = -999;
        for(int i = 0; i < G.V(); i++) {
            if(bf_searches[v].hasPathTo(i) && bf_searches[w].hasPathTo(i)) {
                int temp_len = bf_searches[v].distTo(i) + bf_searches[w].distTo(i);
                if(temp_len < len) {
                    len = temp_len;
                    ancestor = i;
                }
            }
        }
        bf_searches[v] = null; // reset the bfs objects
        bf_searches[w] = null;
        return ancestor; // return -999 if no ancestor exists
    }

    // length of shortest ancestral path of vertex subsets A and B
    public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        // Output shortest length of all pairs
        // iterate through all pairs of vertices in subsetA and subsetB
        // find the shortest path between each pair of vertices
        int len = Integer.MAX_VALUE;
        for(int v : subsetA) {
            for(int w : subsetB) {
                int temp_len = length(v, w);
                if(temp_len < len && temp_len != -999) {
                    len = temp_len;
                }
            }
        }
        return len == Integer.MAX_VALUE ? -999 : len; // return -999 if no path exists
    }

    // a shortest common ancestor of vertex subsets A and B
    public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        // Output shortest common ancestor of all pairs
        int len = Integer.MAX_VALUE;
        int ancestor = -999;
        for(int v : subsetA) {
            for(int w : subsetB) {
                int temp_len = length(v, w);
                if(temp_len < len && temp_len != -999) {
                    len = temp_len;
                    ancestor = ancestor(v, w);
                }
            }
        }
        return ancestor; // return -999 if no ancestor exists
    }

    // do unit testing of this class
    public static void main(String[] args) {

        // Build unit tests
        if (args.length < 1) {
            manualUnitTest();
        } else {
            In in = new In(args[0]);
            Digraph G = new Digraph(in);
            ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
            while (!StdIn.isEmpty()) {
                int v = StdIn.readInt();
                int w = StdIn.readInt();
                int length = sca.length(v, w);
                int ancestor = sca.ancestor(v, w);
                StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
            }
        }
    }

    // Unit test made by me
    public static void manualUnitTest() {
        // Basic tree test
        int numVertices = 12;// or whatever
        Digraph d1 = new Digraph(numVertices);
        // d1.addEdge(1, 0); // add a bunch of these, to form some tree-like shape, e.g.:
        /*
         * 0
         * / \
         * 1 2
         * / \ / \
         * 3 4 5
         */
        // d1.addEdge(2, 0);
        // d1.addEdge(3, 1);
        // d1.addEdge(4, 1);
        // d1.addEdge(5, 2);
       
        d1.addEdge(6, 3);
        d1.addEdge(7, 3);
        d1.addEdge(3, 1);
        d1.addEdge(4, 1);
        d1.addEdge(5, 1);
        d1.addEdge(8, 5);
        d1.addEdge(9, 5);
        d1.addEdge(10, 9);
        d1.addEdge(11, 9);
        d1.addEdge(1, 0);
        d1.addEdge(2, 0);


        ShortestCommonAncestor sca = new ShortestCommonAncestor(d1);
        int w = 4; // fixme
        int x = 10; // fixme
        int y = 8; // fixme
        int z = 7; // fixme

        StdOut.println("Testing Case: 1");
        StdOut.println("length: " + sca.length(x, y));
        StdOut.println("ancestor: " + sca.ancestor(x, y));

        // testing sets with some iterable type
        // ({1,2},{3,4})
        Bag<Integer> b1 = new Bag<Integer>();
        Bag<Integer> b2 = new Bag<Integer>();

        b1.add(x);
        b1.add(y);
        b2.add(w);
        b2.add(z);

        StdOut.println("Testing Case: 2");
        StdOut.println("length: " + sca.length(b1, b2));
        StdOut.println("ancestor: " + sca.ancestor(b1, b2));
    }
}