import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();
        int[] pile = new int[tests];
        String[] stonesArr = new String[tests];
        for (int i = 0; i < tests; i++) {
            pile[i] = in.nextInt();
            in.nextLine();
            stonesArr[i] = in.nextLine();
        }
        for(int i = 0; i < tests; i++){
            String[] stonesStr = stonesArr[i].split(" ");
            int res = 0;
            for(int j = 0; j < stonesStr.length; j++)
                res ^= Integer.valueOf(stonesStr[j]);
            System.out.println((res == 0 ? "Second" : "First"));
        }
    }
}