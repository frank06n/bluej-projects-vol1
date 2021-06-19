import java.io.RandomAccessFile;
import java.io.IOException;

public class IOUtil {
    public static byte[] read(String fileName) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "r");
            byte[] data = new byte[(int)raf.length()];
            raf.readFully(data);
            raf.close();
            return data;
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading file!", e);
        }
    }
    public static void write(String fileName, byte[] data) {
        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
            raf.write(data);
            raf.close();
        } catch (IOException e) {
            throw new RuntimeException("Exception while writing file!", e);
        }
    }
    public static void main(String args[]) {
        String parentFile = "C:\\Users\\User\\Eclipse-Workspace-Export\\Desktop\\MAIN\\HTML\\tetris.html";
        long startTime = System.currentTimeMillis();
        byte[] data = read(parentFile);
        write(parentFile + " - Copy", data);
        System.out.println("Time taken: " + (System.currentTimeMillis()-startTime) + " miliseconds");
    }
}