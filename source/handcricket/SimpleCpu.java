package handcricket;

import util.*;

public class SimpleCpu implements CpuProcessor {
    public void process(Game game, int userCall) {
        int cpuCall = (int)(Math.random() * (game.maxCall + 1));
        SO.Pf("User: %d x %d :Cpu\n", userCall, cpuCall);
        game.umpire.process(game, userCall, cpuCall);
    }
}
