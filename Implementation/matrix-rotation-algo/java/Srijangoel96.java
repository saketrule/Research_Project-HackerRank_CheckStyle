import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int a,b;
        long e;
    a=in.nextInt();
          b=in.nextInt();
          e=in.nextLong();
   long d[][]=new long[a][b];
    int t=0;
    for(int i=0;i<a;i++)
        {
        for(int j=0;j<b;j++)
            {
            if(i!=0&&j!=0)
                {
                if(d[i][j]!=d[0][0])
                 t=1;
              
            }
            d[i][j]=in.nextLong();
        }
    }
    long v[][]=new long[a][b];
    if(t==0)
        {
      for(int i=0;i<a;i++)
        {
        for(int j=0;j<b;j++)
            {  
         System.out.print(d[i][j]);
        }
    System.out.println();
      }
       System.exit(0);
    }
    int c=0;
    int m,n;
        long z;
    int p=a,q=b;
    int r=0,s=0;
        long vv=e;
        int gg=a,hh=b;
    while(r<p&&s<q)
        {
        e=vv;
        int ss;
       if(e>=(ss=gg*2+(hh-2)*2))
           e=e%ss;
    for(int i=r;i<p;i++)
        {
        for(int j=s;j<q;j++)
            {  
            z=d[i][j];
            c=0;
            m=i;
            n=j;
            while((c<e)&&((m==r||m==p-1)||(n==s||n==q-1)))
                {
                if(m<p-1&&n==s)
                    {
                    m++;
                    c++;
                    continue;
                }
               if(m==p-1&&n<q-1)
                   {
                   n++;
                   c++;
                   continue;
               }
                   if(n==q-1&&m>r)
                    { 
                       m--;
               c++;
                     continue;
                    }
               if(m==r&&n<=q-1)
                   {
                   n--;
                   c++;
                   continue;
               }
               }
             v[m][n]=z;
            
            }
        
    }
         gg=gg-2;
            hh=hh-2;
     r++;s++;p--;q--;
}
         for(int i=0;i<a;i++)
        {
        for(int j=0;j<b;j++)
            {  
            if(j!=b-1)
             System.out.print(v[i][j]+" ");
            else
            System.out.print(v[i][j]);
        }
             System.out.println();
      }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}