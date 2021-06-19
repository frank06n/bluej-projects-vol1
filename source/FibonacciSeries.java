public class FibonacciSeries {
    public static void main(String args[]) {
        int a = 0, b = 1, s;
        while (a <= 100) {
            SO.Pln(a);
            s = a;
            a += b;
            b = s;
        }
    }
}
