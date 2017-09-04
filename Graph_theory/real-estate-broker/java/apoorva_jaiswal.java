import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int n,m,i,j,ans[];
        int a[],p[],x[],y[];
        Scanner sc= new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        a=new int[n];
        p=new int[n];
        x=new int[m];
        y=new int[m];
        ans=new int[m];
        for(i=0;i<n;i++)
            {
            a[i]=sc.nextInt();
            p[i]=sc.nextInt();
        }
        for(j=0;j<m;j++)
            {
            x[j]=sc.nextInt();
            y[j]=sc.nextInt();
        }
        for(i=0;i<n;i++)
            {
            for(j=0;j<m;j++)
                {
                if(a[i]<=x[j] && p[i]>=y[j])
                    ans[j]=1;
            }
        }
        int c=0;
        for(i=0;i<m;i++)
            {
            if(ans[i]==1)
                ++c;
        }
        System.out.println(c);
    }
}