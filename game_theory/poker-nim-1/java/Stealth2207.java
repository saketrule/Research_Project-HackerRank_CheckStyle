import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for(int i=0;i<t;i++)
            {
            int n = scan.nextInt();
            int k = scan.nextInt();
            
            int b = 0;
            for(int j=0;j<n;j++)
            {b^=scan.nextInt();
            
            }
            
            if(b==0)
                System.out.println("Second");
            else
                System.out.println("First");
       
        }
            
    }
}