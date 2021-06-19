import java.util.ArrayList;
import java.util.Collections;

public class Functions {
    public static int[] digits(int i) {
        ArrayList<Integer> digits_list = new ArrayList<Integer>();
        while (i > 0) {
            digits_list.add(i % 10);
            i /= 10;
        }
        Collections.reverse(digits_list);
        int size = digits_list.size();
        int[] digits = new int[size];
        for (int j = 0; j < size; j++)
            digits[j] = digits_list.get(j);
        return digits;
    }
    public static int factorial(int i) {
        int factorial = 1;
        while (i != 0) {
            factorial *= i;
            i += i > 0 ? -1 : 1;
        }
        return factorial;
    }
    public static boolean prime(int i) {
        if (i <= 1) return false;
        if (i == 2) return true;
        if (i % 2 == 0) return false;
        final int hfac = (int)Math.sqrt(i) + 1;
        for (int j = 3; j <= hfac; j++)
            if (i % j == 0) return false;
        return true;
    }
    public static boolean armstrong(int i) {
        int[] digits = digits(i);
        int j = 0, k, l;
        for (k = 0; k < digits.length; k++) {
            l = digits[k];
            l *= l * l;
            j += l;
        }
        return i == j;
    }
}
