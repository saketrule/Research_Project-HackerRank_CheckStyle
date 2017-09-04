import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int t;
        Scanner in= new Scanner (System.in);
        t=in.nextInt();
        for(int i=0;i<t;i++)
            {
                int n=in.nextInt();
                int xo=0;
                int arr[]=new int [n];
                for(int p=0;p<n;p++)
                    {
                        arr[p]=in.nextInt();
                        xo=xo^arr[p];
                    
                    }
            if(xo==0)
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}