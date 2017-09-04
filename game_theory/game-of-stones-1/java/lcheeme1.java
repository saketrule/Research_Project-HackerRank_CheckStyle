import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner input = new Scanner(System.in);
        int numCases = input.nextInt();
        for (int i = 0; i < numCases; i++)  {
            int test = input.nextInt();
            int remainder = test % 7;
            if ((remainder == 0) || (remainder == 1))  {
                System.out.println("Second");
            }
            else  {
                System.out.println("First");
            }
        }
    } 
}