package handcricket;

import util.*;

public class Game {
    Innings[] innings;
    int currentInnings;
    boolean gameOver;
    
    int maxBalls, maxWickets, maxCall;
    int totalUserRuns, totalCpuRuns;
    
    CpuProcessor cpu;
    UmpireProcessor umpire;
    
    public Game() {
        reset(true, 1);
    }
    public Game(boolean userBatsFirst, int inningsPerTeam) {
        reset(userBatsFirst, inningsPerTeam);
    }
    
    public Game reset(boolean userBatsFirst, int inningsPerTeam) {
        this.innings = new Innings[inningsPerTeam * 2];
        for (int i = 0; i < this.innings.length; i++)
            this.innings[i] = new Innings(i % 2 == 0 ? userBatsFirst : !userBatsFirst);
        this.currentInnings = 0;
        this.gameOver = false;
        
        this.maxBalls = -1;
        this.maxWickets = 2;
        this.maxCall = 10;
        
        this.cpu = new SimpleCpu();
        this.umpire = new SimpleUmpire();
        return this;
    }
    
    public void start() {
        if (this.gameOver) {
            SO.Pln("Game is already over, reset the game or create a new one");
            return;
        }
        SO.Pf("Enter any number (n) for (0 <= n <= %d) as a call\n", this.maxCall);
        SO.Pln("Enter (quit) to quit the game");
        SO.Pln("Enter (total) to show your and cpu's total runs.");
        SO.Pln("\t(Updated after the end of each innings.)");
        SO.Pln("Enter (stats) to view the status of the game");
        SO.Pln("Any other inputs will be ignored");
        SO.Pln();
        SO.Pln("Innings 1 starts: " + this.innings[0].get("bat") + " will bat now");
        
        while(!this.gameOver) update();
    }
    
    private void update() {
        SO.P("Enter input: ");
        String input = SI.nLine().replaceAll("[()]", "").toLowerCase();
        if (inputIsCall(input))
            this.cpu.process(Game.this, Integer.parseInt(input));
        SO.Pln();
    }
    
    private boolean inputIsCall(String input) {
        if (input.matches("[0-9]*")) {
            int call = Integer.parseInt(input);
            if (call >= 0 && call <= this.maxCall) return true;
            SO.Pln("Type (quit) to quit the game");
            return false;
        }
        switch (input) {
            case "quit":
                gameOver();
                break;
            case "total":
                SO.Pln();
                SO.Pf("User's total runs: %d\n", this.totalUserRuns);
                SO.Pf("Cpu's  total runs: %d\n", this.totalCpuRuns);
                break;
            case "stats":
                Innings innings = this.innings[this.currentInnings];
                SO.Pln();
                SO.Pln(innings.get("bat") + " is batting");
                SO.Pf("Score is %d / %d\n", innings.runs, innings.wickets);
                if (this.maxBalls != -1)
                    SO.Pf("Balls left: %d\n", this.maxBalls - innings.balls);
        }
        return false;
    }
    
    void hasInningsEnded() {
        Innings innings = this.innings[this.currentInnings];
        
        if ( (this.maxWickets != -1 && innings.wickets == this.maxWickets)
            || (this.maxBalls != -1 && innings.balls == this.maxBalls) )
            nextInnings();
            
        if (this.innings.length - 1 == this.currentInnings)
            if ( (innings.userIsBatting && this.totalUserRuns + innings.runs > this.totalCpuRuns)
                || (!innings.userIsBatting && this.totalUserRuns < this.totalCpuRuns + innings.runs) ) 
                nextInnings();
    }
    
    private void nextInnings() {
        Innings innings = this.innings[this.currentInnings ++];
        
        if (innings.userIsBatting)
            this.totalUserRuns += innings.runs;
        else
            this.totalCpuRuns += innings.runs;
            
        SO.Pln();
        SO.Pf("Innings %d is over\n", this.currentInnings);
        SO.Pf("User's total runs: %d\n", this.totalUserRuns);
        SO.Pf("Cpu's  total runs: %d\n", this.totalCpuRuns);
        
        if (hasGameEnded(innings)) return;
        
        SO.Pln();
        SO.Pf("Innings %d starts: ", this.currentInnings + 1);
        SO.Pln(this.innings[this.currentInnings].get("bat") + " will bat now");
    }
    
    private boolean hasGameEnded(Innings lastInnings) {
        int runGap = this.totalUserRuns - this.totalCpuRuns;
        
        if (this.innings.length - 1 == this.currentInnings) {
            if ( lastInnings.userIsBatting && runGap < 0) {
                SO.Pln("Cpu wins as user has got no lead to be chased");
                gameOver();
                return true;
            }
            if (!lastInnings.userIsBatting && runGap > 0) {
                SO.Pln("User wins as cpu has got no lead to be chased");
                gameOver();
                return true;
            }
        }
        
        if (this.innings.length == this.currentInnings) {
            SO.Pln();
            SO.Pln("the last innings has been played");
            
            if (runGap > 0) {
                SO.Pln("User wins" + "\n" + "Brainless cpu has been defeated");
            } else if (runGap < 0) {
                SO.Pln("Cpu wins" + "\n" + "Brainful user has been defeated");
            } else {
                SO.Pln("The game has been a draw");
            }
            
            gameOver();
            return true;
        }
        
        return false;
    }
    
    private void gameOver() {
        this.gameOver = true;
        SO.Pln("GAME OVER");
    }
}