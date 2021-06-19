package handcricket;

public class Innings {
    public boolean userIsBatting;
    public int runs, wickets, balls;
    public Innings() {
        this(true);
    }
    public Innings(boolean userIsBstting) {
        this.userIsBatting = userIsBatting;
        this.runs = 0;
        this.wickets = 0;
        this.balls = 0;
    }
    public String get(String name) {
        name = name.trim().toLowerCase();
        if (name.equals("bat"))
            return userIsBatting ? "user" : "cpu";
        else if (name.equals("ball"))
            return userIsBatting ? "cpu"  : "user";
        else
            throw new RuntimeException("Not a position: " + name);
    }
}