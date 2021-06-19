public class TripleFibonacciSeries {
    public static void main(String args[]) {
        int a = 0, b = 1, c = 2;
        SO.Pf("%d\n%d\n%d\n", a, b, c);
        int s1, s2;
        while (true) {
            s1 = b;
            s2 = c;
            c = a + b + c;
            if (c > 1000) break;
            a = s1;
            b = s2;
            SO.Pln(c);
        }
    }
}
