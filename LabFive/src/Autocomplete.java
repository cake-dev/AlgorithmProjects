import java.util.Arrays;

public class Autocomplete {

    private Term[] autcomplete_terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        this.autcomplete_terms = terms;
        Arrays.sort(this.autcomplete_terms);
    }

    // Returns all terms that start with the given prefix, in descending order of
    // weight.
    public Term[] allMatches(String prefix) {

        /**
         * we can do this by creating a search term from the prefix.
         * the search term is used in the deluxe binary search to find
         * the first and last indices of the matched terms from the prefix-sorted
         * autcomplete_terms.
         * The range of values between first and last index will be the indexes of the
         * matched values. We use this range and the first index to extract the matched
         * terms from all terms and return them
         **/

        // find first and last indexes of search term in sorted terms
        Term search_term = new Term(prefix, 0);
        int first_index = BinarySearchDeluxe.firstIndexOf(this.autcomplete_terms, search_term,
                Term.byPrefixOrder(prefix.length()));

        int last_index = BinarySearchDeluxe.lastIndexOf(this.autcomplete_terms, search_term,
                Term.byPrefixOrder(prefix.length()));

        int term_range = last_index - first_index + 1;
        Term[] term_matches = new Term[term_range];

        // if no matches, return an empty array
        if (first_index == -1 || last_index == -1)
            return new Term[0];

        for (int i = 0; i < term_range; i++) {
            term_matches[i] = autcomplete_terms[first_index + i];
        }

        Arrays.sort(term_matches, Term.byReverseWeightOrder());
        return term_matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        // find first and last indexes of search term in sorted terms
        Term search_term = new Term(prefix, 0);
        int first_index = BinarySearchDeluxe.firstIndexOf(this.autcomplete_terms, search_term,
                Term.byPrefixOrder(prefix.length()));

        int last_index = BinarySearchDeluxe.lastIndexOf(this.autcomplete_terms, search_term,
                Term.byPrefixOrder(prefix.length()));

        int term_range = last_index - first_index + 1;
        return term_range;
    }

    // A sample client for unit testing
    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        int i;
        for (i = 0; i < N; i++) {
            long weight = in.readLong(); // read the next weight
            in.readChar(); // scan past the tab
            String query = in.readLine(); // read the next query
            terms[i] = new Term(query, weight); // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}