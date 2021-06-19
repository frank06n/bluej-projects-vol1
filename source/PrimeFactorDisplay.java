public class PrimeFactorDisplay {
    public static void main(String args[]) {
        SO.Pln("Program to display all the prime factors of the numbers\n");
        for (int i = 1; i <= 20; i++) {
            SO.Pf("% 3d: ", i);
            int j = i, k = 2;
            while (j > 1) {
                if (j % k == 0) {
                    j /= k;
                    SO.Pf("%d ", k);
                } else k++;
            }
            SO.Pln();
        }
    }
}
