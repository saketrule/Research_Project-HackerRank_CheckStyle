import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        for(int i=0;i<n;i++)
        { int m=in.nextInt();
          int c=m%7;
         if(c==0||c==1)
             {System.out.println("Second");}
         else 
             {System.out.println("First");}
        }
    }
}