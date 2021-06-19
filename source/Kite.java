public class Kite
{
    public static void main(String args[]) {
        int n = 5;
        for (int x = -n-1; x<=n+1; x++) {
            int y = x>0 ? x : -x;
            p(' ', y);
            p('~', 1);
            if (y < n+1) {
                p(' ', (n-y)*2+1);
                p('~', 1);
            }
            p('\n', 1);
        }
    }
    private static void p(char c, int times) {
        while (times-- > 0) System.out.print(c);
    }
}
/**
n = 2 (x =n+1 =3)
|||~       -x   = -3
||~|~      -x+1 = -2 y=2 (0)
|~|||~     -x+2 = -1 y=1 (1)
~|||||~    -x+3 = -0 y=0 (2)
|~|||~     ...
||~|~      ...
|||~       x

n = 1
||~
|~|~
~|||~
|~|~
||~

n = 0
|~
~|~
|~

0, 1, 2, 3, 4
x2 +1
1, 3, 5, 7, 9
*/