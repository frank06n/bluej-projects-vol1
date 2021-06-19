import java.util.*;

public class FibonacciPatternPeriod {
    
    /**
     * Each term of the fibonacci series is taken to be the remainder
     * of a certain number (taken as input).
     * 
     * This creates a new series, and this continues until the
     * sequence 0, 1, 1 is again seen. Thus it marks the end of
     * the series.
     * 
     * This length of series of numbers (excluding the last 0, 1, 1)
     * is said to be the "Pattern Period" of that certain number.
     */
    public static void main(String args[])
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        int a = 0, b = 1, div = 0, size, x, y;
        
        while (true) {
            System.out.print("Enter the divisor: ");
            div = sc.nextInt();
            if (div <= 1)
                System.out.println("Divisor must be more than 1");
            else
                break;
        }
        
        while (true) {
            list.add(a);
            size = list.size();
            if (size >= 6 && a == 1) {
                x = list.get(size - 3);
                y = list.get(size - 2);
                if (x == 0 && y == 1) {
                    // removing last 3 elements
                    list.remove(size - 1); // element is 1
                    list.remove(size - 2); // element is 1
                    list.remove(size - 3); // element is 0
                    size = list.size();
                    break;
                }
            }
            
            int temp = a;
            a = (a + b) % div;
            b = temp;
        }
        
        System.out.println("Pattern Period is " + size);
        System.out.println("Do you want to print them? (Enter 0 for yes)");
        
        if (sc.nextInt() == 0) {
            System.out.println();
            for (int i : list) System.out.print(i + " ");
        }
    }
}