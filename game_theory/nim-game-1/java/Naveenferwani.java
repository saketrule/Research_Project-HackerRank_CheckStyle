import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t!=0)
            {
            int n=sc.nextInt();
            int a=0;
            for(int i=0;i<n;i++)
                a^=sc.nextInt();
            if(a==0)
                System.out.println("Second");
            else
                System.out.println("First");
            t--;
        }
    }
}