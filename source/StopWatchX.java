import java.util.Scanner;

class StopWatch implements Runnable {
    private final int update_time;
    private final int display_time;
    
    private int time_ms = 0;
    private volatile boolean stopped = false;
    
    public StopWatch(int update_time, int display_time) {
        this.update_time = update_time;
        this.display_time = display_time;
    }
    
    public void stop() {
        stopped = true;
    }
    public void run() {
        System.out.println();
        while (!stopped) {
            if (time_ms % display_time == 0)
                System.out.println(formatTime());
            
            update();
            time_ms += update_time;
        }
        System.out.println("Stopped watch at " + formatTime());
    }
    private void update() {
        try {
            Thread.sleep(update_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    private String formatTime() {
        int min = time_ms / (60 * 1000);
        int ms = time_ms % (60 * 1000);
        double sec = ms / 1000.0;
        return String.format("%02d:%02f", min, sec);
    }
}
public class StopWatchX {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        StopWatch watch = new StopWatch(1000, 1000);
        Thread thread = new Thread(watch);
        long time;
        
        System.out.println("Press enter to start the stopwatch");
        sc.nextLine();
        System.out.println("Press enter when you want to stop it");
        time = System.currentTimeMillis();
        thread.start();
        
        sc.nextLine();
        watch.stop();
        time = System.currentTimeMillis() - time;
        
        System.out.printf("Time taken: %f\n", time / 1000.0);
    }
}