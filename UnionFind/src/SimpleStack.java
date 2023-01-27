public class SimpleStack {
    private int[] stack;
    private int N;

    public SimpleStack(int capacity) {
        stack = new int[capacity];
    }

    public void push(int item) {
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
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.push(10);
        while (!stack.isEmpty()) {
            StdOut.println(stack.pop());
        }
    }
}
