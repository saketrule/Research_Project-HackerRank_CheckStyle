import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int tests = scan.nextInt();
        while (tests-- > 0) {
            int moves = scan.nextInt();
            int r = 0;
            for (int i = 0; i < moves; i++) 
                if (scan.nextInt() % 2 == 1)
                    r ^= i;
            if (r == 0)
                System.out.println("Second");
            else
                System.out.println("First");
        }

    }
}