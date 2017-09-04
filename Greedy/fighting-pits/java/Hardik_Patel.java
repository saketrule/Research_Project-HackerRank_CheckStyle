import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int q=sc.nextInt();
        //int s[]= new int[n];
        ArrayList <Integer> s= new ArrayList <Integer>();
        //int t[]= new int[n];
        ArrayList <Integer> t= new ArrayList <Integer>();
        for(int i=0;i<n;i++)
            {
            s.add(sc.nextInt());
            t.add(sc.nextInt());
        }
        int ab[]= new int[n];
        int index=0;
        for(int i=0;i<q;i++)
            {
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            if(a==1)
                {
                s.add(b);
                t.add(c);
            }
            else
                {
                int j=0;
                int max=0;
                while(j<s.size())
                    {
                           ab[t.get(j)]=ab[t.get(j)]+s.get(j);
                            if(max<ab[t.get(j)])
                                {
                                max=ab[t.get(j)];
                                index=t.get(j);
                            }
                    j++;
                }
            }
            System.out.println(index);
            
        }
        
        
    }
}