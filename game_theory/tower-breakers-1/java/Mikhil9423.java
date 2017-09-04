import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int test= sc.nextInt();
        for(int i=0;i<test;i++)
            {
            int n=sc.nextInt();
            int m=sc.nextInt();
            if(m==1)
                {System.out.println("2");}
            else  if(n%2==0)
                {System.out.println("2");}
            else
                {System.out.println("1");}
            
            }
    }
}