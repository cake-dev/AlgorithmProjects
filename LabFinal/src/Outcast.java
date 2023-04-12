import java.io.IOException;
import edu.princeton.cs.algs4.*;

public class Outcast {
    WordNet wordnet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        // initialize
        this.wordnet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int outcast_id = -999;
        int max_dist = -999;
        for (int i = 0; i < nouns.length; i++) {
            int dist = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (i == j) continue;
                dist += wordnet.distance(nouns[i], nouns[j]);
            }
            if (dist > max_dist) {
                max_dist = dist;
                outcast_id = i;
            }
        }

        return nouns[outcast_id];
    }

    // Unit Test client
    public static void main(String[] args) throws IOException { // throw because WordNet throws
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}