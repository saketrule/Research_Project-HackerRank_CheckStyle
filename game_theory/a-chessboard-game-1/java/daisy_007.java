import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int n=0,x,y=0;
        Scanner sc=new Scanner(System.in);
        int [][]chess=new int[17][17];
        for(int i=1;i<=15;i++)
        {
            for(int j=1;j<=15;j++)
            {
                if((i%4==1 || i%4==2) && (j%4==1 || j%4==2) )
                    chess[i][j]=2;
                else
                         chess[i][j]=1;
            }
        }
        n=sc.nextInt();
        while(n--!=0)
        {
            x=sc.nextInt();
            y=sc.nextInt();
            if(chess[x][y]==1)
                    System.out.println("First");
            else
                    System.out.println("Second");
        }
    }
}