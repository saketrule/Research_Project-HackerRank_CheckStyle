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
        for (int i=0; i<t; i++)
            {
            int n = sc.nextInt();
            int m = sc.nextInt();
           if (m == 1 || n % 2 == 0 )
               System.out.println(2);
            else
               System.out.println(1) ;
        }
        
    }
}