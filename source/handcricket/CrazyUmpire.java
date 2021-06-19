package handcricket;

import util.*;

public class CrazyUmpire implements UmpireProcessor {
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
        
        if (bat + 1 == ball || bat - 1 == ball ||
            (bat == 0 && ball == game.maxCall) ||
            (ball == 0 && bat == game.maxCall) ) {
            innings.wickets ++;
            SO.Pln(innings.get("bat") + " got out!");
        } else if (bat == ball) {
            int multi = bat * bat;
            innings.runs += bat;
            SO.Pf("%s scored a multi (%d) of %d runs", (innings.get("bat")), bat, multi);
        } else {
            innings.runs += bat;
            SO.Pln(innings.get("bat") + " scored " + bat + " runs");
        }
        innings.balls ++;
        
        game.hasInningsEnded();
    }
}