import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
        for(int i=0;i<t;i++)
        {
            int n = in.nextInt();
            int x = in.nextInt();
            for(int j=1;j<n;j++)
            {
                x = x^in.nextInt();   
            }
            
            if(x==0)
                System.out.println("Second");
            else
                System.out.println("First");
        }
    }
}