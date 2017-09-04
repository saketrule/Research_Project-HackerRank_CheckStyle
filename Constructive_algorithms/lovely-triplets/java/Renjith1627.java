import java.io.*; 
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Triplet
{

    public static void main(String[] args) 
 {
        Scanner s=new Scanner(System.in);
  int p,q,i,j,n=0;
  int tri[][];
  p=s.nextInt();
  q=s.nextInt();
  tri=new int[p][3];
  for(i=0;i<p;i++)
  {
   for(j=0;j<3;j++)
   {
    tri[i][j]=j*q+(i+1);
    n=tri[i][j];
   }
  }
  System.out.println(n+" "+(n-1));
  for(j=0;j<3;j++)
  {
   for(i=0;i<p-1;i++)
   {
    System.out.println(tri[i][j]+" "+tri[i+1][j]);
   }
   if(q%2==0)
   {
    if(j!=2)
    {
     System.out.println(tri[i][j]+" "+tri[0][j+1]);
    }
   }
  }
    }
}