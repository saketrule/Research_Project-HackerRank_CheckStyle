import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        while (g-- > 0){
            int n = in.nextInt();
            int bigs = 0;
            int xor = 0;
            for (int i = 0; i < n; i++){
                int s = in.nextInt();
                xor ^= s;
                if (s > 1) bigs++;
            }
            if (bigs == 0) System.out.println(xor == 0 ? "First" : "Second");
            else if (bigs == 1) System.out.println("First");
            else System.out.println(xor == 0 ? "Second" : "First");
        }
    }
}