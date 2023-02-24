import java.util.Arrays;
import java.util.Comparator;

public class Term implements Comparable<Term> {

    private String query;
    private long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        this.query = query;
        this.weight = weight;
    }

    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            public int compare(Term v, Term w) {
                if (v.weight > w.weight)
                    return -1;
                else if (v.weight < w.weight)
                    return 1;
                else
                    return 0;

            }

        };
    }

    // Compares the two terms in lexicographic order but using only the first r
    // characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new Comparator<Term>() {
            public int compare(Term v, Term w) {
                // check to see if r is greater than the length of either query to prevent out
                // of bounds error
                if (r > v.query.length() || r > w.query.length()) {
                    if (v.query.length() > w.query.length())
                        return 1;
                    else if (v.query.length() < w.query.length())
                        return -1;
                    else
                        return 0;
                }
                String sub_v = v.query.substring(0, r);
                String sub_w = w.query.substring(0, r);
                return sub_v.compareTo(sub_w);
            }
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        String s = this.weight + "\t" + this.query;
        return s;
    }

    // unit testing (you should have some Unit Testing here to confirm that your
    // methods work); for example...
    public static void main(String[] args) {
        Term[] terms = new Term[6];
        terms[0] = new Term("Trevor", 45);
        terms[1] = new Term("Kathy", 43);
        terms[2] = new Term("Ellie", 11);
        terms[3] = new Term("Allen", 9);
        terms[4] = new Term("Alien", 5);
        terms[5] = new Term("Eva", 1);

        Arrays.sort(terms);
        StdOut.println("");

        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (Term t : terms) {
            StdOut.println(t);
        }
        StdOut.println("");

        Arrays.sort(terms, Term.byPrefixOrder(3));
        for (Term t : terms) {
            StdOut.println(t);
        }
    }
}