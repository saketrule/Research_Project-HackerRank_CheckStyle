import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int test=in.nextInt();
        for(int i=0;i<test;i++)
            {
            
            int ans=in.nextInt();
            int flag=0;
            int dummy=in.nextInt();
            if (dummy==1)
                {
                System.out.println(2);
                continue;
            }
            if (ans%2==0)
                flag=2;
            else 
                flag=1;
            System.out.println(flag);
        }
        
    }
}