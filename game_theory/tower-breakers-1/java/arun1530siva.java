import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i=0;i<t;i++)
            {
            int n = sc.nextInt();
            int m = sc.nextInt();
            
            findWin(n,m);
        }
    }
    
    public static void findWin(int n,int m)
        {
        if(m==1)
            {
            System.out.println("2");
            return;
        }
        if(n==1)
            {
            System.out.println("1");
            return;
        }
        if(n%2==0 )
            {
            System.out.println("2");
            return;
        }
       
        else
            {
            System.out.println("1");
            return;
        }
    }
}