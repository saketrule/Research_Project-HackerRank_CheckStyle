import java.io.*;
import java.util.*;

public class Solution 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int matrix[][] = new int[sc.nextInt()][sc.nextInt()];
        int r = sc.nextInt();
        for(int  i = 0; i<matrix.length ; i++)
        {
            for(int j = 0; j<matrix[0].length ; j++)
                matrix[i][j] = sc.nextInt();
        }
        for(int i = 0;i<Math.min(matrix.length,matrix[0].length)/2;i++)
        {
            rotate(matrix,i,r);
        }
        
        for(int i = 0; i<matrix.length ; i++)
        {
            for(int j = 0 ; j<matrix[i].length ; j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void rotate(int matrix[][],int pos,int r)
    {
        int rowstart = pos;
        int colstart = pos;
        int rowend = matrix.length - pos;
        int colend = matrix[0].length - pos;
        r = r % (2*(rowend-rowstart)+2*(colend-colstart-2));
        int temp;
        while(r-->0)
        {
            temp = matrix[rowstart][colstart];
            int i = colstart+1;
            while(i<colend)
            {
                matrix[rowstart][i-1] = matrix[rowstart][i++];
            }          
            i--;
            int j = rowstart+1;
            while(j<rowend)
            {
                matrix[j-1][i] = matrix[j++][i];
            }
            j--;
            i = colend-1;
            while(i>colstart)
            {
                matrix[j][i] = matrix[j][--i];  
            }
            j = rowend-1;
            while(j>rowstart+1)
            {
                matrix[j][i] = matrix[--j][i];
            }
            matrix[j][i] = temp;            
        }
    }
    
    static void printMatrix(int matrix[][])
    {
        for(int i = 0; i<matrix.length ; i++)
            {
                for(int j = 0 ; j<matrix[i].length ; j++)
                {
                    System.out.print(matrix[i][j]+" ");
                }
                System.out.println();
            }
    }
    
}