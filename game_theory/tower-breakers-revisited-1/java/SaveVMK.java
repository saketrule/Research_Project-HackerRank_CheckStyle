import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        int[] conv = new int[1000001];
        for (int i = 1; i <= 1000000; i++) {
            for (int j = i*2; j <= 1000000; j+=i) {
                conv[j] = conv[i]+1;
            }
        }
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int z = 0; z < t; z++) {
            int n = sc.nextInt();
            int x = 0;
            for (int j = 0; j < n; j++) {
                x ^= conv[sc.nextInt()];
            }
            if (x == 0)
                System.out.println(2);
            else
                System.out.println(1);
        }
    }
}