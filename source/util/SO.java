package util;

public class SO {
    public static void P(Object o) {
        System.out.print(o);
    }
    public static void Pln(Object o) {
        System.out.println(o);
    }
    public static void Pln() {
        System.out.println();
    }
    public static void Pf(String format, Object... args) {
        System.out.printf(format, args);
    }
    public static void Parr(String prefix, String delimiter, String suffix, String[] args) {
        P(prefix);
        int length = args.length;
        if (length > 0) P(args[0]);
        for (int i = 1; i < length; i++)
        P(delimiter + args[i]);
        P(suffix);
    }
    public static void Parr(String prefix, String delimiter, String suffix, int[] args) {
        P(prefix);
        int length = args.length;
        if (length > 0) P(args[0]);
        for (int i = 1; i < length; i++)
        P(delimiter + args[i]);
        P(suffix);
    }
    public static void Parr(String prefix, String delimiter, String suffix, long[] args) {
        P(prefix);
        int length = args.length;
        if (length > 0) P(args[0]);
        for (int i = 1; i < length; i++)
        P(delimiter + args[i]);
        P(suffix);
    }
    public static void Parr(String prefix, String delimiter, String suffix, float[] args) {
        P(prefix);
        int length = args.length;
        if (length > 0) P(args[0]);
        for (int i = 1; i < length; i++)
        P(delimiter + args[i]);
        P(suffix);
    }
    public static void Parr(String prefix, String delimiter, String suffix, double[] args) {
        P(prefix);
        int length = args.length;
        if (length > 0) P(args[0]);
        for (int i = 1; i < length; i++)
        P(delimiter + args[i]);
        P(suffix);
    }
}
