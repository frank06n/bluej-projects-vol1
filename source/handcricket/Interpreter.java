package handcricket;

import util.*;

public class Interpreter {
    private static final String names[] = {
        "Hacker", "Gamer", "CoolGuy", "GodFather", "MasterMind",
        "Badass", "Debugger", "Mr.Awesome", "KillerFun", "Shoot"
    };
    private static final String patterns[] = {
        "'='", "Xyz", "Ili", "Q##", "$0~", "||-"
    };
    private static String generateDigit() {
        int digits = 1 + (int)(Math.random() * 3);
        int number = (int)(Math.random() * Math.pow(10, digits));
        return String.format("%0" + digits + "d", number);
    }
    private static String generateName() {
        String pattern = patterns[(int)(Math.random() * patterns.length)];
        String name = names[(int)(Math.random() * names.length)];
        String reverse = "";
        for (String p = pattern; p.length() > 0;
            reverse += p.substring(p.length() - 1),
            p = p.substring(0, p.length() - 1) );
        return pattern + "__" + name + "_" + generateDigit() + "__" + reverse;
    }
    public static void main(String args[]) {
        SO.Pln("Hi! I am HandCricket Interpreter 1.0");
        SO.Pln("Hope you are nice. Wanna play? Enter (yes) or (no)");
        if ( !SI.nLine().toLowerCase().replaceAll("[^yesno]", "").contains("yes") ) {
            SO.Pln("Well then, bye!!!");
            return;
        }
        SO.Pln();
        SO.Pln("Enter your game mode: (crazy) or (simple)");
        SO.Pln("Enter (rules) to learn about modes."); 
        String mode;
        do {
            mode = SI.nLine().toLowerCase().replaceAll("[^simplecrazyu]", "");
            switch (mode) {
                case "simple": case "crazy": break;
                case "rules":
                print_rules();
                break;
                default:
                SO.Pln("Enter a valid mode");
                mode = "";
            }
        } while ( mode.equals("") );
        SO.Pln();
        SO.Pln("This will be a game of innings");
        SO.Pln("But you get to choose how many...");
        SO.Pln("Enter the number of innings each side should have (1 to 10)");
        int innings;
        do {
            innings = SI.nInt();
            if (innings < 1 || innings > 10) {
                SO.Pln("Enter a valid number of innings");
                innings = -1;
            }
        } while ( innings == -1 );
        SO.Pln();
        SO.Pln("You have to set the maximum call");
        SO.Pln("Enter the maximum call (4 to 20)");
        int maxCalls;
        do {
            maxCalls = SI.nInt();
            if (maxCalls < 6 || maxCalls > 20) {
                SO.Pln("Enter a valid maximum call");
                maxCalls = -1;
            }
        } while ( maxCalls == -1 );
        SO.Pln();
        SO.Pln("Enter the maximum balls and wickets per innings");
        
        
        // Toss (diff. class)
    }
    private static void print_rules() {
        SO.Pln();
        SO.Pln("In HANDCRICKET game, both You and Cpu both provides a number in range of 0 to the maximum calls (set at the beginning of the game),");
        SO.Pln("each of which is known as a call.");
        SO.Pln();
        SO.Pln("Now in the [ SIMPLE Game Mode ], things are simple, if your call and Cpu's call is same then the person who is batting loses a wicket.");
        SO.Pln("Else the Batsman's call is added to his/her runs. Very easy? Play in this mode if you are beginning HandCricket.");
        SO.Pln();
        SO.Pln("But in the [ CRAZY Game Mode ], things are a bit weird or rather crazy if you like so. It is that if your call and Cpu's call are same");
        SO.Pln("then the Batsman gets the square of his call added to his run. So if both called 9 and the Cpu was batting then 81 runs will be awarded to it.");
        SO.Pln("Else the Batsman gets his/her call as runs. So how are wickets taken? That's the complexity of this mode. If the difference between");
        SO.Pln("your call and Cpu's call is one then the Batsman loses his/her wicket. Got it? No? Well for example if you called 9 and the Cpu called 8,");
        SO.Pln("then whoever is batting loses his wicket. Same if the calls are (7, 8), (3, 2), (0, 1). Well there is one more way to take a wicket and that");
        SO.Pln("is if one called 0 and the other called the maximum call. So if the maximum calls is 10, you called 0 and the Cpu called 10, then");
        SO.Pln("the Batsman is out for sure. May seem really crazy, but if you play a few times you will get habituated and after all, it's more fun.");
        SO.Pln();
    }
}
