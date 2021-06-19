import java.util.ArrayList;
public class RandomStringGenerator {
    final byte bytes[];
    public RandomStringGenerator(String type) {
        ArrayList<Integer> bytes = new ArrayList<Integer>();
        
        if (type.contains("s")) bytes.add(32);
        if (type.contains("d")) range(bytes, 48, 57);
        if (type.contains("u")) range(bytes, 65, 90);
        if (type.contains("l")) range(bytes, 97, 122);
        if (type.contains("x")) {
            range(bytes, 33, 47);
            range(bytes, 58, 64);
            range(bytes, 91, 96);
            range(bytes, 123, 126);
        }
        
        this.bytes = new byte[bytes.size()];
        for (int i = 0; i < this.bytes.length; i++)
            this.bytes[i] = (byte)(int)bytes.get(i);
    }
    private static void range(ArrayList<Integer> list, int from, int to) {
        while (from <= to) list.add(from++);
    }
    
    public String generate(int length) {
        byte raw[] = new byte[length];
        for (int i = 0; i < length; i++)
            raw[i] = bytes[(int)(Math.random() * bytes.length)];
        return new String(raw);
    }
    
    public static void main(String args[]) {
        RandomStringGenerator gen = new RandomStringGenerator("sdulx");
        for (int i = 0; i < 10; i++) {
            System.out.println("\n" + gen.generate(30));
        }
    }
}
