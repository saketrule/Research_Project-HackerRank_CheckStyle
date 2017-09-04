import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int games = in.nextInt();
        for (int game = 0; game < games; ++game) {
            int n = in.nextInt();
            int xor = 0;
            for (int i = 0; i < n; ++i) {
                xor ^= in.nextInt();
            }
            System.out.println(xor > 0 ? "First" : "Second");
        }
    }
}