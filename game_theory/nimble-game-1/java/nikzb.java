import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        
       
        for (int i = 0; i < t; i++) {
            int numOdds = 0;
            int n = in.nextInt();
            //int[] numbers = new int[n];
            int first = in.nextInt();
            //numbers[0] = first;
            
            int nimSum = 0;

            for (int j = 1; j < n; j++) {
                int numPiles = in.nextInt();
                if (numPiles % 2 == 1)
                nimSum = nimSum ^ j;
                
            }

            if (nimSum == 0) {
                System.out.println("Second");
            }
            else {
                System.out.println("First");
            }
        
        }
    }
}