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
        in.nextLine();
        for(int i = 0; i < tests; i++){
            String nk = in.nextLine();
            String chipsStr = in.nextLine();
            int n = Integer.valueOf(nk.split(" ")[0]);
            int k = Integer.valueOf(nk.split(" ")[1]);
            int nimSum = 0;
            for(int j = 0; j < n; j++){
                nimSum ^= Integer.valueOf(chipsStr.split(" ")[j]);
            }
            System.out.println((nimSum==0) ? "Second" : "First");
        }
    }
}