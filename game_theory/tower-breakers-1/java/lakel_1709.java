import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for(int t = 0 ; t < T; t++){
            int n = in.nextInt();
            int m = in.nextInt();
            System.out.println( (m == 1) || (n % 2 == 0) ? 2 : 1 );
        }
    }
}