import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
    while(t-->0)
    {
        int n=sc.nextInt();
        int k=sc.nextInt();
        int b=0;
        while(n-->0)
     {
            b^=sc.nextInt();
      }

        if(b==0)
            System.out.println("Second");
        else
            System.out.println("First");
    }
        }
    
}