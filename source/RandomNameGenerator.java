import java.util.Random;

public class RandomNameGenerator {
    private static final String[] names_female = {
        "Emma", "Olivia", "Ava", "Isabella", "Sophia", "Charlotte", "Mia", "Amelia",
        "Harper", "Evelyn", "Elizabeth", "Ella", "Emily", "Penelope", "Layla", "Riley",
        "Lily", "Elanor", "Hannah", "Lillan", "Addinson", "Aubrey", "Lucy", "Anna" 
    };
    private static final String[] names_male = {
        "Liam", "Noah", "Willam", "James", "Logan", "Benjamin", "Mason", "Elijah",
        "Jack", "Luke", "Jayden", "Dylon", "Grayson", "Levi", "Isaac", "Anthony",
        "Jackson", "Lincoln", "Cristopher", "Andrew", "Maverick", "Nicolas", "Dominic"
    };
    private static final String[] surnames = {
        "Adams", "Allen", "Dixon", "Elliot", "Evans", "Fletcher", "Hamilton", "Nutter",
        "Armstrong", "Atkinson", "Baker", "Bell", "Cole", "Collins", "Davies", "Dawson",
        "Edwards", "Fisher", "Ford", "Foster", "Fox", "Gibinson", "Gray", "Hall", "Harris",
        "Kelly", "Kennedy", "Johnson", "Jackson", "Marshall", "Martin", "Osborn", "Owen",
        "Quill", "Ramsey", "Richards", "Stewart", "Taylor", "Ward", "West", "Yates", "Young"
    };
    
    public static void main(String args[]) {
        RandomNameGenerator rng = new RandomNameGenerator(0);
        System.out.println("----Females----");
        for (int i = 0; i < 100; i++)
            System.out.println(rng.generate(true));
        System.out.println();
        System.out.println("-----Males-----");
        for (int i = 0; i < 100; i++)
            System.out.println(rng.generate(false));
    }
    
    public RandomNameGenerator(long seed) {
        r = new Random(seed);
    }
    private final Random r;
    public String generate(boolean female) {
        String name;
        if (female) {
            name = "Miss " + names_female[r.nextInt(names_female.length)];
        } else {
            name = "Master " + names_male[r.nextInt(names_male.length)];
        }
        name += " " + surnames[r.nextInt(surnames.length)];
        return name;
    }
}
