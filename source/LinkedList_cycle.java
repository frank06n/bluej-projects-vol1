import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;

public class LinkedList_cycle
{
    @SuppressWarnings ("unchecked")
    public static void main(String args[]) {
        // Working with user input
        with_input();
        
        /**
        // Creating LinkedList
        LinkedList list = new LinkedList();
        // Adding Elements
        for (int i = 1; i < 15; list.add(i++));
        
        // Printing Original Order
        System.out.print("Original: ");
        for (Object o : list) System.out.print(o + " ");
        
        cycler(list, 4); // Cycling the list
        
        // Printing cycled order
        System.out.print("\nCycled  : ");
        for (Object o : list) System.out.print(o + " ");
        */
    }
    
    @SuppressWarnings ("unchecked")
    /**
     * NOT - inserting at OR removing from MIDDLE
     * NOT - creating another LinkedList
     * NOT - using library utilities
     */
    private static void cycler(LinkedList list, int amt) {
        BufferNode buffer = null;
        int left = list.size(), i;
        while (left > 0) {
            i = (left < amt) ? left : amt;
            while (i-- > 0)
            buffer = new BufferNode(buffer, list.pop());
            while (buffer != null) {
                list.offer(buffer.data);
                left--;
                buffer = buffer.anchor;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    /**
     * Taking input as specified
     */
    private static void with_input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the elements:");
        LinkedList list = new LinkedList(Arrays.asList(sc.nextLine().split("->")));
        System.out.print("K=");
        int k = sc.nextInt();
        System.out.println("Elements in cyclic order:");
        cycler(list, k);
        for (Object o : list) System.out.print(o + "->");
    }
    
    private static class BufferNode {
        BufferNode anchor;
        Object data;
        public BufferNode(BufferNode anchor, Object data) {
            this.anchor = anchor;
            this.data = data;
        }
    }
}