import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import edu.princeton.cs.algs4.*;
import java.util.HashMap;

public class WordNet {

    // data structures to hold the synsets and hypernyms
    // go here
    // 2 symbol tables (or hashmaps) for lookup by term and lookup by index
    // a digraph for the edge connections from hypernyms.txt
    // an sca object for finding the shortest common ancestor

    private HashMap<String, Integer> nounsMap = new HashMap<String, Integer>();
    private HashMap<Integer, String> idsMap = new HashMap<Integer, String>();
    private Digraph wordnetDigraph;
    private ShortestCommonAncestor sca;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) throws IOException /* "throw" required for FileReader */ {

        // Read in all synsets (and do something with them)
        BufferedReader input = new BufferedReader(new FileReader(synsets));
        String line = input.readLine();
        while (line != null) {
            String parts[] = line.split(",");
            int synId = Integer.parseInt(parts[0]);
            String synStr = parts[1];
            String[] synset = synStr.split(" ");
            // notice: the definitions are in parts[2]; we're ignoring those

            // need to do more here (and elsewhere, too)
            // add the nouns to the nounsMap
            for (String s : synset) {
                nounsMap.put(s, synId);
            }
            // add the synset to the idsMap
            idsMap.put(synId, synStr);

            // Read next line from file and ..
            line = input.readLine();
        }
        input.close();

        // instantiate the digraph
        wordnetDigraph = new Digraph(idsMap.size());

        BufferedReader input2 = new BufferedReader(new FileReader(hypernyms));
        String line2 = input2.readLine();
        // read in all hypernyms
        while (line2 != null) {
            String parts[] = line2.split(",");
            int synId = Integer.parseInt(parts[0]);

            // add the hypernyms to the digraph, start at 1 bc synId is at 0
            for (int i = 1; i < parts.length; i++) {
                wordnetDigraph.addEdge(synId, Integer.parseInt(parts[i]));
            }

            // Read next line from file and ..
            line = input2.readLine();
        }
        input2.close();

        // instantiate the sca
        sca = new ShortestCommonAncestor(wordnetDigraph);
    }

    // all WordNet nouns
    public Iterable<String> nouns() {
        return null; // fixme
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return nounsMap.containsKey(word);
    }

    // a synset (second field of synsets.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {
        
        return null; // fixme
    }

    // distance between noun1 and noun2 (defined below)
    public int distance(String noun1, String noun2) {
        return -999; // fixme
    }

    // do unit testing of this class
    public static void main(String[] args) throws IOException { // "throw" because the constructor throws.
        WordNet wnet = new WordNet("../wordnet/synsets.txt", "../wordnet/hypernyms.txt");
        // how to test?
    }
}