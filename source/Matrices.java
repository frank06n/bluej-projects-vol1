import java.util.Scanner;

public class Matrices
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter matrix 1:");
        double[][] mat_a = inputMat(sc);
        
        System.out.println("\nEnter matrix 2:");
        double[][] mat_b = inputMat(sc);
        
        double[][] mat_c = multMat(mat_a, mat_b);
        
        System.out.println("\nThe matrix A is: ");
        printMat(mat_a);
        System.out.println("\nThe matrix B is: ");
        printMat(mat_b);
        
        System.out.println("\nThe multiplied matrix is: ");
        printMat(mat_c);
    }
    
    
    
    private static double[][] inputMat(Scanner sc)
    {
        System.out.print("Enter matrix order: ");
        int mat_a_rows = sc.nextInt();
        int mat_a_cols = sc.nextInt();
        double[][] mat_a = new double[mat_a_rows][mat_a_cols];
        
        System.out.println("Enter all elements of matrix:");
        for (int i=0; i< mat_a_rows; i++)
        for (int j=0; j< mat_a_cols; j++)
        mat_a[i][j] = sc.nextDouble();
        
        return mat_a;
    }
    
    
    
    
    private static void printMat(double[][] mat_a)
    {
        int mat_a_rows = mat_a.length;
        int mat_a_cols = mat_a[0].length;
        
        for (int i=0; i< mat_a_rows; i++)
        {
            for (int j=0; j< mat_a_cols; j++)
                System.out.print(mat_a[i][j] + " ");
            System.out.println();
        }
    }
    
    
    
    private static double[][] copyMat(double[][] mat_a)
    {
        int mat_a_rows = mat_a.length;
        int mat_a_cols = mat_a[0].length;
        
        double[][] mat_b = new double[mat_a_rows][mat_a_cols];
        
        for (int i=0; i< mat_a_rows; i++)
        for (int j=0; j< mat_a_cols; j++)
        mat_b[i][j] = mat_a[i][j];
        
        return mat_b;
    }
    
    
    
    private static void addMat(double[][] mat_a, double[][] mat_b) {
        int mat_a_rows = mat_a.length;
        int mat_a_cols = mat_a[0].length;
        
        int mat_b_rows = mat_b.length;
        int mat_b_cols = mat_b[0].length;
        
        if (mat_a_rows != mat_b_rows || mat_a_cols != mat_b_cols)
        {
            throw new RuntimeException("Given matrices are nor compatible for matrix addition or subtraction");
        }
        
        for (int i=0; i< mat_a_rows; i++)
        for (int j=0; j< mat_a_cols; j++)
        mat_a[i][j] += mat_b[i][j];
    }
    
    
    
    private static void subMat(double[][] mat_a, double[][] mat_b) {
        mat_b = copyMat(mat_b);
        multMat(mat_b, -1);
        addMat(mat_a, mat_b);
    }
    
    
    
    private static void multMat(double[][] mat_a, double factor) {
        int mat_a_rows = mat_a.length;
        int mat_a_cols = mat_a[0].length;
        
        for (int i=0; i< mat_a_rows; i++)
        for (int j=0; j< mat_a_cols; j++)
        mat_a[i][j] *= factor;
    }
    
    
    
    private static double[][] multMat(double[][] mat_a, double[][] mat_b) {
        int mat_a_rows = mat_a.length;
        int mat_a_cols = mat_a[0].length;
        
        int mat_b_rows = mat_b.length;
        int mat_b_cols = mat_b[0].length;
        
        if (mat_a_cols != mat_b_rows)
        {
            throw new RuntimeException("Given matrices are nor compatible for matrix multiplication");
        }
        
        int mat_c_rows = mat_a_rows;
        int mat_c_cols = mat_b_cols;
        int the_j      = mat_a_cols; // or mat_b_rows
        
        double[][] mat_c = new double[mat_c_rows][mat_c_cols];
        
        for (int i=0; i< mat_c_rows; i++)
        for (int k=0; k< mat_c_cols; k++)
        for (int j=0; j< the_j     ; j++)
        mat_c[i][k] += mat_a[i][j] * mat_b[j][k];
        
        return mat_c;
    }
}
