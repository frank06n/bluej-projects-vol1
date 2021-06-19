package guessinggame;

import util.*;

public class Level_OnlySame implements Level {
    final int rounds, numbers, qualify;
    public Level_OnlySame(int rounds,  int qualify, int numbers) {
        this.rounds = rounds;
        this.qualify = qualify;
        this.numbers = numbers;
    }
    public Level_OnlySame() {
        this(15, 5, 100);
    }
    public void print_rules() {
        SO.Pf("You have to guess a number from 1 to %d\n", this.numbers);
        SO.Pf("And you will have to guess %d times (rounds)\n", this.rounds);
        SO.Pln("Each time you get a point only if:");
        SO.Pln("\tBoth your and Cpu's guesses are SAME");
        SO.Pln("Or else the Cpu will get a point");
        SO.Pf("At the end of %d rounds, if your points are\n", this.rounds);
        SO.Pf("more than or equal to %d, then you will qualify\n", this.qualify);
    }
    public int rounds() {
        return this.rounds;
    }
    public int cpu_random() {
        return 1 + (int)(Math.random() * this.numbers);
    }
    public boolean guessed_correct(int player_input, int cpu_random) {
        return player_input == cpu_random;
    }
    public boolean qualified(int player_score, int cpu_score) {
        return player_score >= this.qualify;
    }
}
