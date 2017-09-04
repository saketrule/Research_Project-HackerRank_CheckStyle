import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan= new Scanner(System.in);
        
        int t= scan.nextInt();
        
        for(int i=0; i<t; i++)
            {
            int n= scan.nextInt();
            int[] s= new int[n];
            int k=0;
            
            for(int j=0; j<n; j++)
                {
                s[j]= scan.nextInt();
                if(s[j]!=1)
                    k=1;
                
            }
            int c=s[0];
            
            for(int j=1; j<n; j++)
                {
                c = c^s[j];
            }
            
            if((k==0 && c!=0) || (k==1 && c==0))
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}