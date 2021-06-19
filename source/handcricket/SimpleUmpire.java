package handcricket;

import util.*;

public class SimpleUmpire implements UmpireProcessor {
    public void process(Game game, int userCall, int cpuCall) {
        Innings innings = game.innings[game.currentInnings];
        
        int bat = 0, ball = 0;
        if (innings.userIsBatting) {
            bat = userCall;
            ball = cpuCall;
        } else {
            bat = cpuCall;
            ball = userCall;
        }
        
        if (bat == ball) {
            innings.wickets ++;
            SO.Pln(innings.get("bat") + " got out!");
        } else {
            innings.runs += bat;
            SO.Pln(innings.get("bat") + " scored " + bat + " runs");
        }
        innings.balls ++;
        
        game.hasInningsEnded();
    }
}