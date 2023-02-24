import java.util.Arrays;
import java.util.Comparator;

public class BinarySearchDeluxe {

    // Returns the index of the first key in a[] that equals the search key, or -1
    // if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        /***
         * inspired by Sedgewicks implementation of BinarySearch.java
         * the firstIndexOf function finds a match and sets the mid index to first,
         * rather than returning mid
         * it cuts the bounds down when a match is found. This ensures that the values
         * to the "left" of the most recent
         * match are less than it, which allows the search to go to the first occurence
         * of the search key
         **/

        // set lo and hi to first and last terms respectively
        int lo = 0;
        int hi = a.length - 1;
        int mid;
        int first = -1; // set at -1 to return if no match found
        int compare;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            compare = comparator.compare(a[mid], key);
            if (compare == 0) { // if mid key and search key are equal
                first = mid; // set first index to mid index and continue searching. normal binary search
                             // returns here
                if (first == 0 || comparator.compare(key, a[first - 1]) != 0)
                    return first; // stop search when match is first item in array or the item before it does not
                                  // equal it
                hi = mid - 1; // adjust hi bounds down now that we know our mid is the index of an nth
                              // occurence of key
            } else if (compare > 0) { // if mid key is greater than search key
                hi = mid - 1;
            } else { // if mid key is less than search key
                lo = mid + 1;
            }
        }
        return first;
    }

    // Returns the index of the last key in a[] that equals the search key, or -1 if
    // no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        int lo = 0;
        int hi = a.length - 1;
        int mid;
        int last = -1;
        int compare;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            compare = comparator.compare(a[mid], key);
            if (compare == 0) {
                last = mid;
                lo = mid + 1; // adjust lo bounds up now that we know our mid is the index of an nth occurence
                              // of key
            } else if (compare > 0) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return last;
    }

    // unit testing (you should have some Unit Testing here to confirm that your
    // methods work); for example...
    public static void main(String[] args) {

        Term[] terms = new Term[6];
        terms[0] = new Term("Trevor", 45);
        terms[1] = new Term("Kathy", 43);
        terms[2] = new Term("Ellie", 11);
        terms[3] = new Term("Allen", 9);
        terms[4] = new Term("Jack", 1);
        terms[5] = new Term("Tanner", 17);
        Arrays.sort(terms);

        Term searchme3 = new Term("J", 0);
        int first3 = BinarySearchDeluxe.firstIndexOf(terms, searchme3, Term.byPrefixOrder(1));
        StdOut.println("J: " + first3);

        Term searchme = new Term("J", 0);
        int first = BinarySearchDeluxe.firstIndexOf(terms, searchme,
                Term.byPrefixOrder(1));
        int last = BinarySearchDeluxe.lastIndexOf(terms, searchme,
                Term.byPrefixOrder(1));
        StdOut.println("J: " + first + " to " + last);

        searchme = new Term("A", 0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme,
                Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme,
                Term.byPrefixOrder(1));
        StdOut.println("A: " + first + " to " + last);

        searchme = new Term("E", 0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme,
                Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme,
                Term.byPrefixOrder(1));
        StdOut.println("E: " + first + " to " + last);

        searchme = new Term("T", 0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme,
                Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme,
                Term.byPrefixOrder(1));
        StdOut.println("T: " + first + " to " + last);
    }
}