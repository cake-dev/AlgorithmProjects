import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;


public class ShortestCommonAncestor {

   // constructor takes a rooted DAG as argument
   public ShortestCommonAncestor(Digraph G) {

   }

   // length of shortest ancestral path between v and w
   public int length(int v, int w) {
       return -999; //fixme
   }

   // a shortest common ancestor of vertices v and w
   public int ancestor(int v, int w) {
       return -999; //fixme
   }
   


   // length of shortest ancestral path of vertex subsets A and B
   public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
       // Output shortest length of all pairs
       return -999;
   }

   // a shortest common ancestor of vertex subsets A and B
   public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
       // Output shortest common ancestor of all pairs
       return -999;
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
               int length   = sca.length(v, w);
               int ancestor = sca.ancestor(v, w);
               StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
           }
       }
   }
   
   // Unit test made by me
   public static void manualUnitTest() {
    // Basic tree test
       int numVertices = 6;// or whatever
       Digraph d1 = new Digraph(numVertices);
       d1.addEdge(1, 0); // add a bunch of these, to form some tree-like shape, e.g.:
       /*
        *             0
        *          /      \
        *         1        2
        *        / \      / \
        *       3   4    5 
        */
       
       ShortestCommonAncestor sca = new ShortestCommonAncestor(d1);
       int w = -1; // fixme
       int x = -1; // fixme
       int y = -1; // fixme
       int z = -1; // fixme

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