package guessinggame;

public interface Level {
    public void print_rules();
    public int rounds();
    public int cpu_random();
    public boolean guessed_correct(int player_input, int cpu_random);
    public boolean qualified(int player_score, int cpu_score);
}