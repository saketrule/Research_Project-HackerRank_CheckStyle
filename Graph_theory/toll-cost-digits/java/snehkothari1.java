import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();int[] x= new int[2*e];int[] y=new int[2*e];int[] r=new int[2*e]; int[] d=new int[10];int h=e;
        for(int a0 = 0; a0 < e; a0++){
            x[a0] = in.nextInt();
            y[a0] = in.nextInt();
            r[a0] = in.nextInt();
           d[(r[a0])%10]++;
           d[(1000-r[a0])%10]++;
            x[h] =y[a0];
            y[h] =x[a0];
            r[h] =1000-r[a0];
            h++;
         }
        int a,b,c,m,p,o=0,g=0;
      
           for(b=0;b<(2*e);b++)
            {
               m=x[b];
               p=y[b];
             g=1;
               for(o=0;o<(2*e);o++)
                   {
                   if(g<e)
                       {
                   if(o!=b && o!=b+3 && p==x[o] && m!=p && m!=y[o])
                       {
                       d[(r[b]+r[o])%10]++;
                       p=y[o];g++;
 break;
                   }
               }
           }
           }
       
    for(int i=0;i<10;i++)
        {
        System.out.println(d[i]);
    }
    }
}