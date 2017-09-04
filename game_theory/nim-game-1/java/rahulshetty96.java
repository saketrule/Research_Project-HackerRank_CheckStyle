import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int n,r,sum=0;
        while(t-->0)
        {
            n = sc.nextInt();
            sum = 0;
            while(n-->0)
            {
                r = sc.nextInt();
                sum = sum^r;
            }
            if(sum==0)
            System.out.println("Second");
            else
            System.out.println("First");
        }
    }
}