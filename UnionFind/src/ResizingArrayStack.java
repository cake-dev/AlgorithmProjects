import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    public Item[] a = (Item[]) new String[1]; // stack items
    private int N = 0;

    // number of items
    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) { // Move stack to a new array of size max.
        System.out.println("resizing to " + max);
        Item[] temp = (Item[]) new String[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void push(Item item) { // Add item to top of stack.
        if (N == a.length)
            resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() { // Remove item from top of stack.
        Item item = a[--N];
        a[N] = null; // Avoid loitering (see text).
        if (N > 0 && N == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> { // Support LIFO iteration.
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[--i];
        }

        public void remove() {
        }
    }

    public static void main(String[] args) { // Create a stack and push/pop strings as directed on StdIn, print the
                                             // stack size and number of elements after each operation.
        ResizingArrayStack<String> s = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {

                s.push(item);
                // System.out.println("N: " + s.size() + " a_size: " + s.a.length);
            } else if (!s.isEmpty()) {
                StdOut.print(s.pop() + " ");
                // System.out.println("N: " + s.size() + " a_size: " + s.a.length);
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}