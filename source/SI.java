import java.util.Scanner;

public class SI {
    private static Scanner scanner;
    public static Scanner newSc() {
        if (scanner != null) scanner.close();
        return scanner = new Scanner(System.in);
    }
    public static Scanner getSc() {
        if (scanner == null) newSc();
        return scanner;
    }
    public static String nLine() {
        return getSc().nextLine();
    }
    public static int nInt() {
        return getSc().nextInt();
    }
    public static long nLong() {
        return getSc().nextLong();
    }
    public static float nFloat() {
        return getSc().nextFloat();
    }
    public static double nDouble() {
        return getSc().nextDouble();
    }
}
