import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static String winner(int startingNumber) {
     if (startingNumber % 7 == 0 || startingNumber % 7 == 1) {
      return "Second";
     }
     return "First";
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int testCaseCount = in.nextInt();
        for(int i = 0; i < testCaseCount; i++) {
            System.out.println(winner(in.nextInt()));
        }
    }
}