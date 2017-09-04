import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        long c[]=new long[n];
        for(int i=0;i<n;i++)
            c[i]=sc.nextLong();
        long min=c[0];
        for(int j=1;j<n;j++)
            {
            if(min>c[j])
                min=c[j];
            }
            
        
        System.out.println(min);
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}