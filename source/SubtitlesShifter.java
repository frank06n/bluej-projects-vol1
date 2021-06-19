import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;
/**
 * Shift the subtitles by specific milliseconds to fix them
 * 
 * @author frank06n 
 * @version 2
 */
public class SubtitlesShifter {
    public static void main(String args[]) {
        //C:\Users\User\Eclipse-Workspace-Export\Desktop\Logs\javatest\subtt
        
        /*Scanner sc = new Scanner(System.in);
        System.out.println("Enter file path for the original .SRT file (with extension)");
        String srcPath = sc.nextLine();
        System.out.println("Enter file path where the editied .SRT file will be saved (with extension)");
        String dstPath = sc.nextLine();
        System.out.println("Enter the amount of shift (in milliseconds)");
        int shift = sc.nextInt();*/
        String srcPath = "E:\\NewMovies\\Pirates of the Caribbean 5 - Dead Men Tell No Tales (2017)\\capt";
        String dstPath = srcPath + "33.srt";
        srcPath += ".srt";
        int shift = -33000;
        //shift(srcPath, dstPath, shift);
        String a = FileCompat.readFileAsText(srcPath);
        System.out.println(a.substring(0, 100));
        a = getShifted(a, shift);
        System.out.println("#####################################");
        System.out.println(a.substring(0, 100));
        FileCompat.writeFileAsText(dstPath, a);
    }
    public static void shift(String srcPath, String dstPath, int shift) {
        FileInputStream in;
        FileOutputStream out;
        String subtitles = "";
        try {
            in = new FileInputStream(new File(srcPath));
            int c;
            while ((c = in.read()) != -1) subtitles += "" + (char)c;
        } catch (FileNotFoundException e) {
            System.out.printf("File not found \"%s\"\n", srcPath);
            e.printStackTrace(); exit(); return;
        } catch (IOException e) {
            System.out.printf("File cannot be read \"%s\"\n", srcPath);
            e.printStackTrace(); exit(); return;
        }
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Unexpected error occured!");
            e.printStackTrace(); exit(); return;
        }
        System.out.println("Subtitles:\n" + subtitles); 
        subtitles = getShifted(subtitles, shift);
        try {
            out = new FileOutputStream(new File(srcPath));
            out.write(subtitles.getBytes());
        } catch (FileNotFoundException e) {
            System.out.printf("Error creating file \"%s\"\n", dstPath);
            e.printStackTrace(); exit(); return;
        } catch (IOException e) {
            System.out.printf("Cannot write to file \"%s\"\n", dstPath);
            e.printStackTrace(); exit(); return;
        }
        try {
            out.close();
        } catch (IOException e) {
            System.out.println("Unexpected error occured!");
            e.printStackTrace(); exit(); return;
        }
        System.out.println("Success!");
    }
    public static String getShifted(String subtitles, int shift) {
        subtitles = subtitles.replaceAll("[^\\p{Print}\\n]","").trim();
        String[] subts = subtitles.split("\n\n");
        
        String out = "";
        for (String subt : subts) {
            Subtitle sub = Subtitle.fromString(subt);
            sub.shift(shift);
            out += sub.toString() + "\n\n";
        }
        return out;
    }
    private static void exit() {
        System.err.println("Error occurred! Force exitting application to free resources...");
        System.exit(1);
    }
}
class Subtitle {
    int index;
    int startMillis, endMillis;
    String text;
    private Subtitle() {}
    public void shift(int millis) {
        startMillis += millis;
        endMillis += millis;
    }
    public String toString() {
        String out = index + "\n";
        out += textMillis(startMillis);
        out += " --> ";
        out += textMillis(endMillis);
        out += "\n" + text;
        return out;
    }
    public static Subtitle fromString(String text) {
        Subtitle sub = new Subtitle();
        sub.index = Integer.parseInt(text.substring(0, text.indexOf("\n")));
        text = text.substring(text.indexOf("\n") + 1);
        sub.startMillis = parseMillis(text.substring(0, text.indexOf(" ")));
        text = text.substring(text.indexOf("--> ") + 4);
        sub.endMillis = parseMillis(text.substring(0, text.indexOf("\n")));
        sub.text = text.substring(text.indexOf("\n")+1);
        return sub;
    }
    private static int parseMillis(String textMillis) {
        int _a = textMillis.indexOf(":");
        int _b = _a + 1 + textMillis.substring(_a+1).indexOf(":");
        int _c = textMillis.indexOf(",");
        int millis = Integer.parseInt(textMillis.substring(0,_a));
        millis = millis*60 + Integer.parseInt(textMillis.substring(_a+1,_b));
        millis = millis*60 + Integer.parseInt(textMillis.substring(_b+1,_c));
        millis = millis*1000 + Integer.parseInt(textMillis.substring(_c+1));
        return millis;
    }
    private static String textMillis(int millis) {
        int mil = millis % 1000;
        int sec = (millis /= 1000) % 60;
        int min = (millis /= 60) % 60;
        int hrs = millis /= 60;
        return String.format("%02d:%02d:%02d,%03d", hrs, min, sec, mil);
    }
}