import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

     public static void main(String[] args) {
         Scanner in = new Scanner(System.in);
         int t = in.nextInt();
         while (t-- > 0) {
             int n = in.nextInt();
             int r = 0;
             for (int i = 0; i < n; i++) 
                 if (in.nextInt() % 2 == 1)
                     r ^= i;
             if (r == 0)
                 System.out.println("Second");
             else
                 System.out.println("First");
         }
         in.close();
     }
}