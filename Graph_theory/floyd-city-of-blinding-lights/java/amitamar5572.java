import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution
{
    final static int MAX=9999999;
    public static void main(String[] args)
    {
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      int m=sc.nextInt();
      int mtr[][]=new int[n+1][n+1];
      for(int i=0;i<=n;i++)
      {
        for(int j=0;j<=n;j++)
          mtr[i][j]=MAX;
        mtr[i][i]=0;
      }
      for(int i=0;i<m;i++)
        mtr[sc.nextInt()][sc.nextInt()]=sc.nextInt();
      int q=sc.nextInt();
      //use floyd warshal algo here
      for(int k=1;k<=n;k++)
        for(int i=1;i<=n;i++)
          for(int j=1;j<=n;j++)
            if(mtr[i][k]+mtr[k][j]<mtr[i][j])
              mtr[i][j]=mtr[i][k]+mtr[k][j];
      //end floyd warshals algo here
      int a,b;
      while(q!=0)
      {
        a=sc.nextInt();
        b=sc.nextInt();
        if(mtr[a][b]==MAX)
          System.out.println(-1);
        else
          System.out.println(mtr[a][b]);
        q--;
      }
    }
}