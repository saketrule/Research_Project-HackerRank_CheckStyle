import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int i1,i2;
        Scanner sc=new Scanner(System.in);
        i1=sc.nextInt();
        while(i1-->0)
            {
            i2=sc.nextInt();
            if(i2%7==0 || i2%7==1)
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}