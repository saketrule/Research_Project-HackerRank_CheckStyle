import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    int win;
    
    void check(int n,int m)
        {
        if(m!=1)
            {
        if(n%2==0)
        {
            win=2;
            
        }
        else {
            win=1;
          }
        }
        else
            win=2;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
      

        int t =sc.nextInt();
        int n,m;
          Solution so[]= new Solution[t];
            for(int i=0;i<t;i++)
                {
                so[i]=new Solution();
                n=sc.nextInt();
                m=sc.nextInt();
                so[i].check(n,m);
            }
        for(int i=0;i<t;i++)
            {
            System.out.println(so[i].win);
        }
    }
}