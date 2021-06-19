public class DigitsDisplay {
    public static void main(String args[]) {
        SO.Pln("Program to display all the digits of the numbers\n");
        for (int i = 1; i <= 100; i++) {
            SO.Pf("% 4d: ", i);
            int[] digits = Functions.digits(i);
            SO.Parr("", ", ", "\n", digits);
        }
    }
}
