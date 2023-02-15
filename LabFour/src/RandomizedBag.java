
/******************************************************************************
 *  Compilation:  javac RandomizedBag.java
 *  Execution:    java RandomizedBag < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  
 *  Stack implementation with a resizing array.
 *
 *  % java ResizingArrayStack < tobe.txt
 *  to be not that or be (2 left on stack)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedBag<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // number of elements on stack
    Random rng;

    /**
     * Initializes an empty stack.
     */
    public RandomizedBag() {
        a = (Item[]) new Object[2];
        n = 0;
        rng = new Random();
    }

    /**
     * Is this stack empty?
     */
    public boolean isEmpty() {
        return n == 0; // returns number of values in the array (not the size of the array)
    }

    /**
     * Returns the number of items in the stack.
     */
    public int size() {
        return n; // return number of values in array, not array size
    }

    /*
     * Resize the underlying array holding the elements
     */
    private void resize(int capacity) {
        assert capacity >= n;

        // thanks Sedgewick (resizing array stack)
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    /**
     * Adds the item to this bag (which is an array).
     */
    public void put(Item item) {
        if (n == a.length)
            resize(2 * a.length); // double size of array if necessary
        a[n++] = item; // place item and incriment num items
    }

    /**
     * Removes and returns a random item from the bag
     */
    public Item get() {
        Item item = null;
        if (isEmpty()) // if the bag is empty, throw an exception
            throw new NoSuchElementException("out of bounds (underflow)");
        int r = rng.nextInt(n); // returns a uniform random value between [0 and n)
        item = a[r]; // grabs the item at the random index
        a[r] = a[n - 1]; // move the last element into position where random was (overwrites the value we
                         // grabbed)
        a[n - 1] = null; // remove the final element (because it was copied to a[r])
        n--; // reduce num items
        if (n > 0 && n == a.length / 4)
            resize(a.length / 2); // shrink size of array if necessary
        return item;
    }

    // returns a random item and does NOT remove it
    public Item sample() {
        Item item = null; // instantiate item
        if (isEmpty()) // if the bag is empty, throw an exception
            throw new NoSuchElementException("out of bounds (underflow)");
        int r = rng.nextInt(n); // returns a uniform random value between [0 and n)
        item = a[r]; // grabs the item at the random index
        return item;
    }

    /**
     * Returns an iterator to this bag that iterates through the items in random
     * order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedBagIterator();
    }

    // an iterator; ours doesn't implement remove() since it's optional
    private class RandomizedBagIterator implements Iterator<Item> {
        private int i; // index of next element to return
        private Item itArr[]; // array of items

        public RandomizedBagIterator() {
            /*
             * do the work here to support
             * (i) multiple independent iterators (i.e. each one initializes its own itArr);
             * - in this constructor, this can take time linear in the size of the bag
             * (ii) constant time next() and hasnext() calls.
             */
            i = 0; // start at 0
            itArr = (Item[]) new Object[n]; // create an array of size n
            for (int j = 0; j < n; j++) { // copy the values from the bag to the iterator array
                itArr[j] = a[j];
            }
            StdRandom.shuffle(itArr); // randomize that bag <- unique random order for each iterator
            // if i didnt do this, the iterator would return the same order every time
            // (confirmed by trying it out)
        }

        public void remove() { // dont need this for this data type
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            if (i < n) // if the iterator is not at the end of the amount of items, there is a next
                       // item that exists
                return true;
            else
                return false;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = null;

            // FIXME return the next entry from THIS iterator's random order
            // just had to return the next item in the iterator array, then incriment i
            item = this.itArr[i];
            i++;
            return item;
        }
    }

    /**
     * Unit tests the RandomizeBag data type.
     */
    public static void main(String[] args) {
        RandomizedBag<String> bag = new RandomizedBag<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("q!"))
                break;
            if (!item.equals("-"))
                bag.put(item);
            else if (!bag.isEmpty())
                StdOut.print(bag.get() + " ");
        }
        StdOut.println("(" + bag.size() + " left on bag)");

        Iterator<String> itr1 = bag.iterator();
        if (!bag.isEmpty())
            bag.get(); // test removal of one
        Iterator<String> itr2 = bag.iterator();

        StdOut.println("Here's what was left before removing one (in random order):");
        while (itr1.hasNext()) {
            String s = itr1.next();
            StdOut.println(s + " ");
        }
        StdOut.println("");

        StdOut.println("Here's what was left after removing one (in random order):");
        while (itr2.hasNext()) {
            String s = itr2.next();
            StdOut.println(s + " ");
        }
        StdOut.println("");

        StdOut.println("I sure hope the second one is missing one entry, and in a different order.");

    }

}
