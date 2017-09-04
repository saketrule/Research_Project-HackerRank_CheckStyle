import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
   Scanner in =new Scanner(System.in);
         int t =in.nextInt();       
        

        for(int i=0;i<t;i++)
   { int n =in.nextInt();
   int m =in.nextInt();
        if(n%2==0||m==1)
            {
            System.out.println("2");
        }
   else
       System.out.println("1");
   
   
   } 
    
    
    }
}