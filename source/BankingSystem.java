import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

/*
 * STATUS: UNFINISHED
 * 
 * Tasks done:
 * > Mainloop
 * > Create Account
 * > Read, Write to database
 * 
 * Tasks left:
 * > Login + Loginloop
 * > Transactions (withraw, deposit, transfer)
 * > Change pin
 * > Delete account
 * 
 * Tasks to be thought:
 * > Some kind of interest system
 * 
 */




public class BankingSystem
{
    static Scanner sc;
    static String db = null;
    static ArrayList<Account> accounts = new ArrayList<Account>();
    
    /**
     * Driver code.
     * @param args[0] Filepath to the databse
     */
    public static void main(String args[])
    {
        if (args.length == 1) db = args[0];
        
        if (db!=null) readFromDatabase();
        
        sc = new Scanner(System.in);
        mainloop();
        sc.close();
        
        if (db!=null) writeToDatabase();
    }
    
    static void readFromDatabase() {
        String data = "";
        try
        {
            File myObj = new File(db);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data += myReader.nextLine() + "\n";
            }
            myReader.close();
        }
        catch(FileNotFoundException fnfe) { fnfe.printStackTrace(); }
        
        try
        {
            JSONArray jarr = new JSONArray(data);
            for (int i=0; i<jarr.length(); i++) accounts.add(new Account(jarr.getJSONObject(i)));
        }
        catch (JSONException jsone) { jsone.printStackTrace(); }
    }
    
    static void writeToDatabase() {
        String data = "";
        try
        {
            JSONArray jarr = new JSONArray();
            for (Account acc : accounts) jarr.put(acc.pack());
            data = jarr.toString(2);
        }
        catch (JSONException jsone) { jsone.printStackTrace(); }
        try
        {
            FileWriter myWriter = new FileWriter(db);
            myWriter.write(data);
            myWriter.close();
        }
        catch(IOException ioe) { ioe.printStackTrace(); }
    }
    
    static void mainloop() {
        p("\n");
        p("==============\n");
        p("1. Log In\n");
        p("2. Create Account\n");
        p("3. Quit\n");
        p("==============\n");
        p("> > ");
        
        int n = inum();
        if (n==1) LogIn();
        else if (n==2) CreateAccount();
        else if (n==3) Quit();
        else {
            p("Please enter a valid choice\n");
            mainloop();
        }
    }
    
    static void LogIn() {
        p("Not created!!!\n");
        mainloop();
    }
    
    static void CreateAccount() {
        p("Enter your name:\n> > ");
        String name = line();
        
        p("Enter your phone number:\n> > ");
        String phnno = line();
        
        String accno = genAccNo();
        p("Your account number: " + accno + "\n");
        
        p("Type in a PIN for your account:\n> > ");
        String pin = line();
        
        double initBalance = 10.0 + (int)(Math.random()*90*100)/100.0;
        accounts.add(new Account(name, phnno, accno, pin, initBalance));
        
        p("Your account was succesfully created!\n");
        p("You are given a reward of Rs. " + initBalance + "\n");
        mainloop();
    }
    
    static void Quit() {
        p("Thanks for using the bank! Now get lost");
    }
    
    static String genAccNo() {
        String bankId = "1097";
        String branchId = "0082";
        String randId = "";
        for (int i=0; i<9; i++) randId += (i==4) ? (" ") : ("" + (int)(Math.random()*10));
        return bankId + " " + branchId + " " + randId;
    }
    
    static void p(Object o) {
        System.out.print(o);
    }
    static int inum() {
        return Integer.parseInt(sc.nextLine().trim());
    }
    static String line() {
        return sc.nextLine();
    }
}

class Account {
    final String name;
    final String phnno;
    final String accno;
    final String pin;
    double balance;
    
    public Account(String name, String phnno, String accno, String pin, double balance)
    {
        this.name = name;
        this.phnno = phnno;
        this.accno = accno;
        this.pin = pin;
        this.balance = balance;
    }
    
    public Account(JSONObject obj) throws JSONException {
        this(obj.getString("name"),
            obj.getString("phnno"),
            obj.getString("accno"),
            obj.getString("pin"),
            obj.getDouble("balance"));
    }
    
    public JSONObject pack() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name"   , this.name);
        obj.put("phnno"  , this.phnno);
        obj.put("accno"  , this.accno);
        obj.put("pin"    , this.pin);
        obj.put("balance", this.balance);
        return obj;
    }
}
