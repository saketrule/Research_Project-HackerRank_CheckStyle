import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int n,m,count=0;
  Scanner sc=new Scanner(System.in);
  n=sc.nextInt();
  m=sc.nextInt();
  int client=n+1;
  int a[]=new int[n];
  int p[]=new int[n];
  int x[]=new int[m];
  int y[]=new int[m];
  for(int i=0;i<n;i++) {
   a[i]=sc.nextInt();
   p[i]=sc.nextInt();
  }
  for(int j=0;j<m;j++) {
   x[j]=sc.nextInt();
   y[j]=sc.nextInt();
  }
  sc.close();
  for(int j=0;j<m;j++) {
   for(int i=0;i<n;i++) {
    if(x[j]>a[i] && y[j]<=p[i]) {
     if(client!=i) {
      count++;
      client=i;
      break;
     }
    }
   }
  }
  System.out.print(count);
    }
}