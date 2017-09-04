import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        
 int n;
 Scanner input=new Scanner(System.in);
 n=input.nextInt();
 int a[][]=new int[n+1][n+1];
    boolean check[][]=new boolean[n+1][n+1];
 n++;
  int inf=99999999;
 for(int i=0;i<n;i++)
  for(int j=0;j<n;j++)
   a[i][j]=inf;
  int e=input.nextInt();
  while(e>0)
  {
   e--;
   int x,y,r;
   x=input.nextInt();
   y=input.nextInt();
   r=input.nextInt();
   a[x][y]=r;
   a[y][x]=1000-r;
   
  } 
  int count[]=new int[10];
  for(int k=0;k<n;k++)               
  {
  for(int i=0;i<n;i++)
  {
  for(int j=0;j<n;j++)
  {
  if(a[i][j]!=inf &&i!=j && check[i][j]!=true)
  {
   int val=a[i][j];
            //System.out.println(" val is "+val+" in first loop where  "+i+" -> "+j);
   int digit=val%10;
   count[digit]=count[digit]+1;
            check[i][j]=true;
            
  }
  if(a[i][k]!=inf && a[k][j]!=inf && i!=j  )
  {
   int val=a[i][k]+a[k][j];
            //System.out.println(" val is "+val+" in second loop where  "+i+" -> "+k+" -> "+j);
   int digit=val%10;
   count[digit]=count[digit]+1; 
  }
  }
  }
  }
  for(int val:count)
  {
   System.out.println(val);
  }
  
        }
    }