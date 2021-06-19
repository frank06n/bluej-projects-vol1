import java.io.PrintStream;
import java.util.Scanner;

/*
 * STATUS: UNFINISHED
 * 
 * To do:
 * > Make a list to hold all variables
 * > Implement commmented functions
 * 
 */

interface Util {
    PrintStream out();
    Scanner scanner();
    
    default void print(Object o) {
        out().print(o);
    }
    default void printf(String f, Object... o) {
        out().printf(f, o);
    }
    default void line() {
        out().println();
    }
    default void dash(int n) {
        while (n-- > 0) print(n);
    }
    
    default String zword() {
        return scanner().next();
    }
    default String zline() {
        return scanner().nextLine();
    }
    default int zint() {
        return scanner().nextInt();
    }
    default float zfloat() {
        return scanner().nextFloat();
    }
}

interface MenuUtil extends Util {
    default int showMenu(String... options) {
        return showMenu(false, options);
    }
    default int showMenu(boolean allowIllegal, String... options) {
        line();
        dash(8);
        print("MENU");
        dash(8);
        line();
        
        for (int i = 0; i < options.length; i++)
            printf("%d. %s\n", i+1, options[i]);
        
        dash(20);
        line();
        
        return takeChoice(allowIllegal, options.length);
    }
    default int takeChoice(boolean allowIllegal, int optionsLength) {
        int choice;
        try {
            print("Enter your choice: ");
            choice = zint();
            if (choice > 0 && choice <= optionsLength)
                return choice;
        } catch (NumberFormatException e) {
            choice = -1;
        }
        if (allowIllegal)
            return choice;
        print("Please enter a number between 1 and " + optionsLength); 
        line();
        return takeChoice(false, optionsLength);
    }
}

enum DataType {
    INT, FLOAT, BOOL, TEXT;
}
enum NameType {
    CONST, VAR; 
}
class Variable {
    DataType type;
    Object value;
}

public class Evaluator implements MenuUtil {
    Scanner mScanner = new Scanner(System.in);
    
    public PrintStream out() {
        return System.out;
    }
    public Scanner scanner() {
        return mScanner;
    }
    
    private void showMainMenu() {
        int choice = showMenu(
            "Perform unary operation",
            "Perform binary operation",
            "Perform ternary operation",
            "Assign a value to variable",
            "Inspect a variable",
            "Quit the evaluator");
        switch (choice) {
            /*
            case 1:
                showUnaryOpMenu();
                break;
            case 2:
                showBinaryOpMenu();
                break;
            case 3:
                showTernaryOpMenu();
                break;
            case 4:
                doAssignVariable();
                break;*/
            case 5:
                doInspectVariable();
                break;
            case 6:
                doQuit();
                break;
        }
    }
    private Variable getVariable(String variableName) {
        // search and return variable from the list
        return null;
    }
    private void doInspectVariable() {
        print("Enter the variable name: ");
        String variableName = zword();
        Variable variable = getVariable(variableName);
        printf("Value of variable %s type %s is %s", variableName, variable.type, variable.value);
        showMainMenu();
    }
    private void doQuit() {
        print("Thanks for using my Evaluator!");
        print("Bye, have a good day...");
        line();
    }
}
