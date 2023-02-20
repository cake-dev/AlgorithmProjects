public class Sandbox {

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        System.out.println("a: " + a + " b: " + b);
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(gcd(216, 192));
    }
}
