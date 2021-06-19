package guessinggame;

import java.util.*;
import util.*;

public class Guessing_Game {
    public static void main() {
        SO.Pln("Welcome to the Guessing Game v-1.5.0, a game by Rajarshi Roy");
        SO.P  ("Please enter your name: ");
        String name = SI.nLine();
        SO.Pln("Hello, " + name +". How are you?");
        SO.Pln("Enter 1 to read the instructions, 2 to play the game and anything else to quit");
        int choice = SI.nInt();
        switch (choice) {
            case 1:
                SO.Pln("There are several levels with varying difficulty to test your luck.");
                SO.Pln("So have faith in your luck (sounds a bit weird), and play the game");
                SO.Pln("The instructions and rules to play each level will be provided before the level starts.");
                SO.Pln();
                SO.Pln("Enter 1 to play the game or anything else to quit");
                if (SI.nInt() != 1)
                    break;
            case 2:
                start_game(create_levels());
                return;
        }
        SO.Pln("If you do want to quit the game. Well, have a nice day!");
    }
    
    private static Level[] create_levels() {
        return new Level[] {
            new Level_OddEven(20, 5, 100),
            new Level_OddEven(15, 5, 100),
            new Level_OddEven(10, 5, 100),
            
            new Level_OnlyEven(20, 4, 100),
            new Level_OnlyEven(15, 4, 100),
            new Level_OnlyEven(15, 6, 100),
            
            new Level_OnlySame(20, 2, 100),
            new Level_OnlySame(20, 4, 100),
            new Level_OnlySame(20, 6, 100),
        };
    }
    
    private static void start_game(final Level[] levels) {
        for (int i = 0; i < levels.length; i++) {
            final Level level = levels[i];
            final int rounds = level.rounds();
            
            SO.Pln("Welcome to level " + (i + 1));
            SO.Pln("Enter 1 to read rules of this level or anything else to play it.");
            if (SI.nInt() == 1)
                level.print_rules();
            
            SO.Pln();
            SO.Pln("Level starts >>>");
            int player_score = 0, cpu_score = 0;
            
            for (int r = 1; r <= rounds; r++) {
                SO.Pln();
                SO.Pf("Round (dd of dd), Enter the number: ".replace("dd", "%02d"), r, rounds);
                int player_input = SI.nInt();
                int cpu_random = level.cpu_random();
                
                SO.Pf("                       Cpu guessed: %d -- ", cpu_random);
                if ( level.guessed_correct(player_input, cpu_random) ) {
                    SO.Pln("||_RIGHT_||");
                    player_score++;
                } else {
                    SO.Pln("xx_WRONG_xx");
                    cpu_score++;
                }
            }
            
            SO.Pln();
            SO.Pln("*** Level Ended ***");
            SO.Pln("Scores:");
            SO.Pln("\tYour  score: " + player_score);
            SO.Pln("\tCPU's score: " +    cpu_score);
            SO.Pln();
            
            if ( level.qualified(player_score, cpu_score) ) {
                if ( (i + 1) == levels.length ) {
                    SO.Pln("Whoa! you completed the game. Congratulations!!!");
                    SO.Pln();
                    print_won();
                    break;
                }
                SO.Pln("Good Job, you qualified to the next level");
            } else {
                SO.Pln("Well.. sorry, you didn't meet the requirements to qualify this level");
                SO.Pln("Better luck next time, and thank you for playing");
                break;
            }
        }
    }
    
    private static void print_won() {
        SO.Pln("|--------------------------------------------|");    
        SO.Pln("|                                            |");
        SO.Pln("|  |   | |---| |   |    |    | |---| |   |   |");
        SO.Pln("|   ---  |   | |   |    |    | |   | |xx |   |".replace("x", "\\") );
        SO.Pln("|    |   |   | |   |    | /x | |   | | xx|   |".replace("x", "\\") );
        SO.Pln("|    |   |---| |---|    |/  x| |---| |   |   |".replace("x", "\\") );
        SO.Pln("|                                            |");
        SO.Pln("|--------------------------------------------|");
    }
}