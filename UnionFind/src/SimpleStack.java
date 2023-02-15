public class SimpleStack {
    private char[] stack;
    private int N;

    public SimpleStack(int capacity) {
        stack = new char[capacity];
    }

    public void push(char item) {
        stack[N++] = item;
    }

    public int pop() {
        return stack[--N];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public static void main(String[] args) {
        SimpleStack stack = new SimpleStack(10);
        // accept user input to fill stack
        while (!StdIn.isEmpty()) {
            if (StdIn.readChar() == 'q')
                break;
            if (StdIn.readChar() == 'p')
                StdOut.println(stack.pop());
            if (StdIn.readChar() != 'p')
                stack.push(StdIn.readChar());
        }
    }
}
