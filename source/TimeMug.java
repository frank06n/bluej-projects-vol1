import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

/**
 * Using Timer and TimerTask for explanations
 * 
 * @author Pritam 
 * @version alpha
 */
public class TimeMug {
    static Timer timer;
    static Scanner sc;
    public static void main(String args[]) {
        sc = new Scanner(System.in);
        timer = new Timer();
        showMainMenu();
    }
    static void showMainMenu() {
        System.out.println();
        System.out.println("--------MENU---------");
        System.out.println("| 1. Loader         |");
        System.out.println("| 2. Timer          |");
        System.out.println("| 3. Stopwatch      |");
        System.out.println("| 4. Quit           |");
        System.out.println("---------------------");
        System.out.print("\nEnter your choice: ");
        int choice = TimeMug.num();
        switch(choice) {
            case 1:
                LoaderSim.start();
                break;
            case 2:
                TimerSim.start();
                break;
            case 3:
                StopwatchSim.start();
                break;
            case 4:
                System.out.println("Thanks for using TimeMug! Have a good day...");
                //br.close();
                break;
            default:
                System.out.println("Please enter a valid choice (1 - 4)");
                showMainMenu();
        }
    }
    static int num() {
        try {
            return Integer.parseInt(TimeMug.line());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer!");
            return num();
        }
    }
    static String line() {
        return sc.nextLine();
    }
}
class LoaderSim {
    static final int max_load = 25;
    static final int load_delay = 1000;
    static final int load_interval = 60;
    static final int stop_delay = 800;
    static int load;
    static TimerTask load_task;
    static TimerTask stop_task;
    static void start() {
        load = max_load;
        
        load_task = new TimerTask() {
            public void run() {
                if (--load == 0) {
                    System.out.println("]");
                    TimeMug.timer.schedule(stop_task, stop_delay);
                    load_task.cancel();
                } else {
                    System.out.print("|");
                }
            }
        };
        stop_task = new TimerTask() {
            public void run() {
                System.out.println("Loading finished");
                TimeMug.showMainMenu();
            }
        };
        
        System.out.println("Enter anything to start loading");
        TimeMug.line();
        System.out.print("LOADING: [");
        TimeMug.timer.scheduleAtFixedRate(load_task, load_delay, load_interval);
    }
}
class TimerSim {
    static void start() {
        System.out.println("Enter timer alert text");
        final String alert_text = TimeMug.line();
        System.out.println("Enter timeout seconds: ");
        long timeout = TimeMug.num() * 1000;
        
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("TIMEOUT ALERT: " + alert_text);
                TimeMug.showMainMenu();
            }
        };
        
        System.out.println("Enter anything to start timer");
        TimeMug.line();
        TimeMug.timer.schedule(task, timeout);
    }
}
class StopwatchSim {
    static TimerTask task;
    static int stop_at;
    static int current;
    static void start() {
        while (true) {
            System.out.println("Enter stopwatch time-limit: ");
            stop_at = TimeMug.num() * 2;
            
            if (stop_at > 0) break;
            System.out.println("Invalid limit! Please enter a natural number");
        }
        current = 0;
        task = new TimerTask() {
            public void run() {
                System.out.println(current++ / 2f);
                if (current == stop_at) {
                    System.out.println("Reached time-limit!");
                    TimeMug.showMainMenu();
                }
            }
        };
        
        System.out.println("Enter anything to start stopwatch");
        TimeMug.line();
        TimeMug.timer.schedule(task, 0, 500);
    }
}