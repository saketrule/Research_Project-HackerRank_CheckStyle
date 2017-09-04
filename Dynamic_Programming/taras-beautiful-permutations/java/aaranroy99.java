import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static long Fact(int k)
    {
        if(k<1)
            return 0;
        long fr=1;
        for(int i=1;i<=k;i++)
            fr=fr*i;
        return fr;
    }
     static long Fac(int k)
    {
        if(k<2)
            return 0;
        long fr=1;
        for(int i=1;i<=k;i++)
            fr=fr*i;
        return fr;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int q=sc.nextInt();
        for(int k=0;k<q;k++)
        {
            int n=sc.nextInt();
            int i,j,ar[]=new int[n],d[]=new int[n],count=0,l=0;
            long fl1,fl2=0,ab;
            for(i=0;i<n;i++)
                ar[i]=sc.nextInt();
            for(i=0;i<n;i++)
            {
                count=1;
                for(j=i+1;j<n;j++)
                {
                    if(ar[i]==ar[j])
                        count++;
                }
                if(count>1)
                {
                    d[l]=count;
                    l++;
                }
            }
            fl1=Fact(n);
            //System.out.println("l="+l);
            for(i=l-1;i>=0;i--)
            {
                ab=Fact(d[i]);
                //System.out.println("ab="+ab);
                fl1=fl1/ab;
                fl2=fl2+Fact(n-d[i]+1)/ab;
                if(n-d[i]+1==2)
                    fl2++;
            }
            //System.out.println("fl1="+fl1+"fl2="+fl2);
            fl1=fl1-fl2;
            //System.out.println("fl1="+fl1);
            fl1+=Fac(l);
            //System.out.println("fl1="+fl1);         
            System.out.println(fl1);
        }
    }
}