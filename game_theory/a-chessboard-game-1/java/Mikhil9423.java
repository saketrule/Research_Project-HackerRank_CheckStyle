import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner (System.in);
        int test =sc.nextInt();
        for(int i=0;i<test;i++)
            {
                int a=sc.nextInt();
                int b=sc.nextInt();
                a=a%4;
                b=b%4;
                if(a==0||a==3||b==0||b==3)
                    {
                        System.out.println("First");
                    }
            else
                System.out.println("Second");
            }
    }
}