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

    // the set for the nouns has to be a set of integers which 
    private ST<String, SET<Integer>> nounsMap = new ST<String, SET<Integer>>();
    private ST<Integer, String> idsMap = new ST<Integer, String>();
    private Digraph wordnetDigraph;
    private ShortestCommonAncestor sca;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) throws IOException {

        // Read in all synsets (and do something with them)
        BufferedReader input = new BufferedReader(new FileReader(synsets));
        String line = input.readLine();
        System.out.println("Reading in synsets...");
        while (line != null) {
            String parts[] = line.split(",");
            int synID = Integer.parseInt(parts[0]);
            String synStr = parts[1];
            String[] synset = synStr.split(" ");
            // notice: the definitions are in parts[2]; we're ignoring those

            // need to do more here (and elsewhere, too)
            // add the nouns to the nounsMap
            for (String s : synset) {
                if (nounsMap.contains(s)) {
                    nounsMap.get(s).add(synID);
                } else {
                    SET<Integer> set2 = new SET<Integer>();
                    set2.add(synID);
                    nounsMap.put(s, set2);
                }
            }
            
            // add the synset to the idsMap
            idsMap.put(synID, synStr);

            // Read next line from file and ..
            line = input.readLine();
        }
        input.close();

        // instantiate the digraph
        wordnetDigraph = new Digraph(idsMap.size());

        BufferedReader input2 = new BufferedReader(new FileReader(hypernyms));
        String line2 = input2.readLine();
        // read in all hypernyms
        System.out.println("Reading in hypernyms...");
        while (line2 != null) {
            String parts[] = line2.split(",");
            int synID = Integer.parseInt(parts[0]);

            // add the hypernyms to the digraph, start at 1 bc synId is at 0
            for (int i = 1; i < parts.length; i++) {
                wordnetDigraph.addEdge(synID, Integer.parseInt(parts[i]));
            }

            // Read next line from file and ..
            line2 = input2.readLine();
        }
        input2.close();

        // instantiate the sca
        System.out.println("Instantiating the SCA...");
        sca = new ShortestCommonAncestor(wordnetDigraph);
    }

    // all WordNet nouns
    public Iterable<String> nouns() {
        return nounsMap.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return nounsMap.contains(word);
    }

    // a synset (second field of synsets.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {

        // get the ids for the nouns
        SET<Integer> noun1IDs = nounsMap.get(noun1);
        SET<Integer> noun2IDs = nounsMap.get(noun2);
        // get the sca of the nouns
        int scaID = sca.ancestor(noun1IDs, noun2IDs);
        // get the synset for the sca and return it
        String synset = idsMap.get(scaID);

        return synset;
    }

    // distance between noun1 and noun2 (defined below)
    public int distance(String noun1, String noun2) {

        SET<Integer> noun1IDs = nounsMap.get(noun1);
        SET<Integer> noun2IDs = nounsMap.get(noun2);
        int distance = sca.length(noun1IDs, noun2IDs);
        return distance; // fixme
    }

    // do unit testing of this class
    public static void main(String[] args) throws IOException { // "throw" because the constructor throws.
        WordNet wnet = new WordNet("synsets.txt", "hypernyms.txt");
        // how to test?
    }
}