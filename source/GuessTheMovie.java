import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

class Round {
    final int rewardPts, penaltyPts, guessChs, qualifyMark, newMovies;
    Round(int rewardPts, int penaltyPts, int guessChs, int qualifyMark, int newMovies) {
        this.rewardPts   = rewardPts;
        this.penaltyPts  = penaltyPts;
        this.guessChs    = guessChs;
        this.qualifyMark = qualifyMark;
        this.newMovies   = newMovies;
    }
}

public class GuessTheMovie {
    private static String acceptLineInput() {
        return mScanner.nextLine();
    }
    private static int acceptNumberInput() {
        return Integer.parseInt(acceptLineInput().trim());
    }
    private static String getRepeated(String what, int times) {
        String out = "";
        while (times-- > 0) out += what;
        return out;
    }
    private static void aPrint(String what) {
        System.out.print(what);
        waitFor(DELAY_NORMAL);
    }
    private static void bPrint(String what) {
        System.out.print(what);
        waitFor(DELAY_NORMAL * 10);
    }
    private static void cPrint(String what, float millis) {
        System.out.print(what);
        waitFor((int)millis);
    }
    private static void cPrintln(String what, float millis) {
        cPrint(what + "\n", millis);
    }
    private static void line() {
        System.out.println();
    }
    private static void showTitle(String title) {
        String pad = getRepeated("-", 8);
        title = String.format("\n%s %s %s\n", pad, title, pad);
        bPrint(title);
        line();
    }
    private static int showMenu(String... options) {
        if (options == null || options.length == 0) {
            System.err.println("Cannot display menu with zero options!!!");
            return -1;
        }
        int width = 0;
        String text = "\n";
        for (int i = 0; i < options.length; i++) {
            String option = options[i];
            text += String.format("%02d: %s\n", i+1, option);
            width = Math.max(width, option.length());
        }
        width += 10;
        String b = getRepeated("-", width);
        bPrint(b + text + b + "\n");
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                int input = acceptNumberInput();
                if (input >= 1 && input <= options.length)
                    return input;
                System.out.println("Please enter a number between 1 and " + options.length);
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number");
            }
        }
    }
    private static void waitFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted while waiting!!!");
            e.printStackTrace();
        }
    }
    private static void showLoading() {
        bPrint("LOADING: [");
        int left = LOADBAR_CAP;
        while (left-- > 0) aPrint("|");
        aPrint("]");
        line();
        bPrint("Loading finished!");
        line();
    }
    
    private static final int GAME_OVER = Integer.MIN_VALUE; 
    
    private static final int LOADBAR_CAP = 10;
    private static final int DELAY_NORMAL = 75;
    private static final String[] ALL_MOVIES = {
        "Interstellar", "Captain-Marvel", "Captain-America", "Iron-Man", "The-Avengers", "Spider-Man",
        "Jumanji", "Ant-Man", "Thor", "Hulk", "It", "Extraction", "Shooter", "Bat-Man", "Disturbia",
        "Super-Man", "Wonder-Woman", "Titanic", "Joker", "Cars", "Terminator", "Anabelle", "Conjuring"
    };
    
    private static Scanner mScanner;
    private static ArrayList<Round> mRounds;
    public static void main(String args[]) {
        bPrint("Welcome to Hangman Luck!!!"); line();
        
        mRounds = new ArrayList<Round>();
        /* RewardPoints, PenaltyPoints, GuessChances, QualifyMark, NewMovies */
        mRounds.add(new Round(4, 0, 10, 12, 5));
        mRounds.add(new Round(10, 1, 12, 30, 2));
        mRounds.add(new Round(16, 2, 15, 45, 3));
        mRounds.add(new Round(32, 4, 18, 80, 5));
        mRounds.add(new Round(64, 10, 20, -1, 5));
        
        mScanner = new Scanner(System.in);
        displayMainMenu();
    }
    
    private static void displayMainMenu() {
        line();
        showLoading();
        showTitle("MAIN MENU");
        int userChoice = showMenu(
            "Read intructions",
            "Play new game",
            "Quit the game"
        );
        line();
        showLoading();
        switch (userChoice) {
            case 1:
                displayInstructions();
                break;
            case 2:
                startNewGame();
                break;
            case 3:
                displayQuitMenu();
                break;
            default:
                System.err.printf("Encountered unexpected user choice %d, quiting the game!", userChoice);
                System.exit(-1);
        }
    }
    
    private static void displayInstructions() {
        showTitle("INSTRUCTIONS");
        
        final float d = DELAY_NORMAL * 40;
        
        cPrintln("There are several rounds in this game...", d);
        line();
        cPrintln("Each round, some movies will be added to your Guessing List", d *1.5f);
        cPrintln("And you will get certain \"Guess Chances\"", d *1.25f);
        line();
        cPrintln("In each of those chances, I will think of a movie (random each time)", d *1.75f);
        cPrintln("from that Guessing List, and you have to guess it.", d *1.5f);
        line();
        cPrintln("For each correct guess, you will get certain points.", d *1.5f);
        cPrintln("There will be negative marking too, in higher rounds.", d *1.5f);
        line();
        cPrintln("If you can cross a certain \"Qualify Mark\"", d *1.25f);
        cPrintln("You can play the next round!", d);
        line();
        cPrintln("Best of Luck!!!", d);
        bPrint("Redirecting to Main Menu\n");
        displayMainMenu();
    }
    
    private static String formatUserPts(int userPts) {
        return "[" + ( (userPts >= 0) ? ("+") : ("") ) + userPts + "]";
    }
    private static void startNewGame() {
        ArrayList<String> LEFT_LIST, GUESS_LIST;
        LEFT_LIST = new ArrayList<String>( Arrays.<String>asList(ALL_MOVIES) );
        GUESS_LIST = new ArrayList<String>();
        
        int userPts = 0;
        line();
        bPrint(getRepeated("*", 30)); line();
        line();
        
        for (int roundN = 0; roundN < mRounds.size(); roundN++) {
            Round round = mRounds.get(roundN);
            
            bPrint("Round " + (roundN+1) + " begins!"); line(); line();
            
            {
                bPrint(getRepeated("-", 30)); line();
                
                bPrint(String.format("Correct Guess  : +%02d points", round.rewardPts));    line();
                bPrint(String.format("Wrong Guess    : -%02d points", round.penaltyPts));   line();
                bPrint(String.format("Guess Chances  : % 3d times", round.guessChs));       line();
                if (roundN +1 < mRounds.size())
                bPrint(String.format("Qualify Mark   : % 3d points", round.qualifyMark));
                else
                bPrint("Qualify Mark   : X");
                line();
                
                bPrint(getRepeated("-", 30)); line();
                line();
            }
            
            waitFor(DELAY_NORMAL * 15);
            
            if (round.newMovies > LEFT_LIST.size()) {
                System.err.println("Not enough movies to supply in round index: " + roundN);
                System.err.println("Quiting the game...");
                System.exit(-1);
            }
            for (int n = round.newMovies; n > 0; n--) {
                int index = (int)(Math.random() * LEFT_LIST.size());
                GUESS_LIST.add(LEFT_LIST.get(index));
                LEFT_LIST.remove(index);
            }
            
            {
                bPrint(getRepeated("-", 7) + " GUESSING LIST " + getRepeated("-", 8));
                line();
                
                for (int movieN = 0; movieN < GUESS_LIST.size(); movieN++)
                    aPrint(String.format("%02d. %s\n", movieN+1, GUESS_LIST.get(movieN)) );
                
                bPrint(getRepeated("-", 30)); line(); line();
            }
            
            waitFor(DELAY_NORMAL * 15);
            
            {
                bPrint("Your points: " + formatUserPts(userPts)); line();
                bPrint("Start Guessing..."); line(); line();
            }
            
            int newUserPts = startRound(userPts, roundN, GUESS_LIST);
            
            if (newUserPts == GAME_OVER) {
                bPrint("Redirecting to Main Menu\n");
                displayMainMenu();
                return;
            }
            
            userPts = newUserPts;
        }
    }
    private static boolean confirmUser() {
        System.out.print("Enter 0 to continue: ");
        return acceptLineInput().trim().equals("0");
    }
    private static boolean isInvalidMovie(String input, ArrayList<String> guessList) {
        try {
            int inputN = Integer.parseInt(input);
            if (inputN >= 0 && inputN <= guessList.size())
                return false;
        } catch (NumberFormatException e) {}
        for (String movie : guessList)
            if (input.equals(movie.trim().toLowerCase()))
                return false;
        return true;
    }
    private static boolean isCorrectGuess(String input, int movieNum, String movieName) {
        if ( input.equals(movieName.trim().toLowerCase()) )
            return true;
        try {
            return movieNum == Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static int startRound(int userPts, int roundN, ArrayList<String> guessList) {
        int askNum = 0;
        Round round = mRounds.get(roundN);
        while (askNum < round.guessChs) {
            System.out.printf("%02d> Say it: ", askNum+1);
            int guessN = (int)(Math.random() * guessList.size());
            String input = acceptLineInput().trim().toLowerCase();
            
            waitFor(DELAY_NORMAL * 15);
            
            if (isInvalidMovie(input, guessList)) {
                bPrint("Oops! There's no such movie in the list");
                line();
                if (!confirmUser()) return GAME_OVER;
            } else {
                boolean correct = isCorrectGuess(input, guessN+1, guessList.get(guessN));
                userPts += correct ? round.rewardPts : -round.penaltyPts;
                
                System.out.print(correct ? "Correct" : "Wrong");
                System.out.printf("! It's (%02d.) '%s'", guessN+1, guessList.get(guessN));
                bPrint(""); line();
                
                System.out.print( correct ? ("+" + round.rewardPts) : ("-" + round.penaltyPts) );
                bPrint(" points! You have " + formatUserPts(userPts));
                line();
                
                askNum++;
            }
            line();
        }
        
        bPrint("Round " + (roundN + 1) + " completed!"); line();
        
        if (roundN+1 == guessList.size()) {
            bPrint("Congratulations! You completed the Game!!!");
            line();
            bPrint("Your score: " + formatUserPts(userPts)); line();
            line();
            System.out.print("Enter anything to continue: ");
            acceptLineInput();
            return GAME_OVER;
        } else if (userPts >= round.qualifyMark) {
            bPrint("Congratulations! You qualified for the next round...");
            line();
            if (!confirmUser()) return GAME_OVER;
            line();
            bPrint(getRepeated("-", 10) + " LEVEL UP " + getRepeated("-", 10)); line();
            line();
        } else {
            bPrint("Sorry you didn't qualify for the next round!");
            line();
            int lostBy = round.qualifyMark - userPts;
            bPrint("You needed " + lostBy + " more points, your score: " + formatUserPts(userPts));
            line();
            waitFor(DELAY_NORMAL * 5);
            return GAME_OVER;
        }
        
        return userPts;
    }
    
    private static void displayQuitMenu() {
        showTitle("QUIT MENU");
        
        System.out.println("Are you sure you want to quit?");
        int userChoice = showMenu("Yes", "No");
        switch (userChoice) {
            case 1:
                System.out.println("Bye! Have a good day and please play again later!");
                System.exit(0);
                break;
            case 2:
                bPrint("Redirecting to Main Menu\n");
                displayMainMenu();
                break;
            default:
                System.err.printf("Encountered unexpected user choice %d, quiting the game!", userChoice);
                System.exit(-1); 
        }
    }
}