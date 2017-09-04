import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ts = in.nextInt();
        for(int t = 0; t < ts ; ts++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int result = 0;
            for(int i = 0; i < n; i++){
                result ^= in.nextInt();
            }
            System.out.println(result == 0? "Second": "First");
        }
    }
}