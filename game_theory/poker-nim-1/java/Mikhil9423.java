import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for(int i=0;i<test;i++)
            {
                int bucket = sc.nextInt();
                int player = sc.nextInt();
                int[] stack = new int[bucket];
                int ans = 0;
                for(int j=0;j<bucket;j++)
                    {
                        stack[j]=sc.nextInt();
                    }
                for(int j=0;j<bucket;j++)
                    {
                        ans=stack[j]^ans;
                    }
            if(ans==0)
                {
                System.out.println("Second");}
            else
                {System.out.println("First");}
            
            }
         
    }
}