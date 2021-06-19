public class CheckArmstrong {
    public static void main(String args[]) {
        SO.Pln("Checking if input is an Armstrong number");
        int i = SI.nInt();
        String msg = Functions.armstrong(i) ? "Yes, it is" : "No, it is not";
        SO.Pln(msg + " an Armstrong Number.");
    }
}
