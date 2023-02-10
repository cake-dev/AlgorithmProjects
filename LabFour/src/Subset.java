
import java.util.Iterator;

public class Subset {

    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedBag<String> bag = new RandomizedBag<String>();

        // fill the bag with words from StdIn; example of doing this in e.g. LinkedQueue
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("q!"))
                break;
            if (!item.equals("-"))
                bag.put(item);
            else if (!bag.isEmpty())
                StdOut.print(bag.get() + " ");
        }

        // pull k things from the bag, if possible.
        Iterator<String> itr = bag.iterator();
        for (int i = 0; i < k; i++) {
            if (itr.hasNext())
                StdOut.println(itr.next());
        }

    }

}