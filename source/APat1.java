public class APat1 {
    public static void main(String args[]) {
        // 14-02-2020 (class 8 end)
        for(int i = 1, j, k; i < 5; i++) {
            k = (i + 1) + (j = i * (i + 1) / 2);
            for (j = j + 2; j <= k; j++)
                System.out.print(j + " ");
            System.out.println();
        }
        
        System.out.println("----------");
        // 17-06-2021 (class 10 half)
        for(int i=1, k=3; i<=4; i++, k++)
        {
            for (int j=1; j<=i; j++, k++)
            {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }
}

/*
 * 3 
   5 6 
   8 9 10 
   12 13 14 15
 */