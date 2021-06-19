public class BlueAlienSeries {
    /**
     * { x | x = n * (n + 1) + 1 and n <- W } 
     */
    public static void main(String args[]) {
        int i = 0;
        while (i <= 20) SO.Pln(i * ++i + 1);
    }
}
