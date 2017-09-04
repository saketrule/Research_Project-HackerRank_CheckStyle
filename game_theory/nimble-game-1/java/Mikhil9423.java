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
        int element=0;
        for(int i=0;i<test;i++)
            {
                int bucket = sc.nextInt();
                int[] stack = new int[bucket];
                int ans = 0;
                for(int j=0;j<bucket;j++)
                    {
                        stack[j]=sc.nextInt();
                    }
                for(int j=0;j<bucket;j++)
                    {
                        if(stack[j]>0)
                            {
                            if(stack[j]%2==0)
                            {element=0;}
                        else
                            {element=j;}
                            
                        ans=element^ans;
                        }//if of stack greater than zero
                    }
            if(ans==0)
                {
                System.out.println("Second");}
            else
                {System.out.println("First");}
            
            }
         
    }
}