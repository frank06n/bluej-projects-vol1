import java.util.*;

public class minecraft
{
    enum Item
    {
        wood, log, stone, stick, pick, axe, hoe;
    }
    
    static HashMap<Item, Integer> items = new HashMap<Item, Integer>();
    static boolean shouldRun = true;
    
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        for (Item it : Item.values())
        {
            items.put(it, 0);
        }
        while (shouldRun)
        {
            System.out.print("\n> > ");
            gameLoop(sc.nextLine().toLowerCase().split(" "));
        }
    }
    
    static void gameLoop(String input[])
    {
        if (input.length==0)
        {
            
        }
        else if (input[0].equals("quit"))
        {
            System.out.println("Bye! Have a great time!");
            shouldRun = false;
        }
        else if (input[0].equals("break_wood"))
        {
            int amt = input.length==1 ? 1 : Integer.parseInt(input[1]);
            breakWood(amt);
        }
        else if (input[0].equals("mine_stone"))
        {
            int amt = input.length==1 ? 1 : Integer.parseInt(input[1]);
            mineStone(amt);
        }
    }
    
    static boolean hasItem(Item item)
    {
        return items.get(item) >= 1;
    }
    
    static int putItem(Item item, int amt)
    {
        if (amt<0) return -1;
        int present = items.get(item);
        int toput = present + amt;
        int put = toput>16 ? 16-present : amt;
        int excess = toput-put;
        items.put(item, put);
        
        System.out.printf("%d %s" + (put!=1?"s were":" was") + " put in inventory. ", put, item);
        System.out.println("Present = " + (present+put));
        
        return excess;
    }
    
    static int breakWood(int amt)
    {
        //if (hasItem(Item.axe))
        putItem(Item.wood, amt);
        return 0;
    }
    
    static int mineStone(int amt)
    {
        //if (hasItem(Item.pick))
        putItem(Item.stone, amt);
        return 0;
    }
    
    class Job {
        
    }
}
