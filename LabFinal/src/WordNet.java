import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordNet {

   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) throws IOException /* "throw" required for FileReader*/ {

       // Read in all synsets (and do something with them)
       BufferedReader input = new BufferedReader(new FileReader(synsets));
       String line = input.readLine();
       while (line != null) {
           String parts[] = line.split(",");
           int synId = Integer.parseInt(parts[0]);
           String synStr = parts[1];
           String[] synset = synStr.split(" ");
           //notice: the definitions are in parts[2];  we're ignoring those

           // need to do more here (and elsewhere, too)

           // Read next line from file and ..
           line = input.readLine();
       }
       input.close();

       // Read in all hypernyms with some similar code

   }

   // all WordNet nouns
   public Iterable<String> nouns(){
       return null; //fixme
   }


   // is the word a WordNet noun?
   public boolean isNoun(String word) {
       return false; //fixme
   }


   // a synset (second field of synsets.txt) that is a shortest common ancestor
   // of noun1 and noun2 (defined below)
   public String sca(String noun1, String noun2) {

        return null; //fixme
   }

   // distance between noun1 and noun2 (defined below)
   public int distance(String noun1, String noun2) {
       return -999; // fixme
   }
   

   // do unit testing of this class
   public static void main(String[] args) throws IOException{ //"throw" because the constructor throws.
        WordNet wnet = new WordNet("synsets.txt", "hypernyms.txt");
        //how to test
   }
}