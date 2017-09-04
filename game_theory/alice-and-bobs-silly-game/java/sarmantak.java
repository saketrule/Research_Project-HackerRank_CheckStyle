import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        int cnt = 0;
        int max = 100001;
        int[] p = new int[100001];
        for (int i = 1; i < max; i++) {
            boolean isPrimeNumber = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }
            if (i > 1 && isPrimeNumber) cnt++;
            p[i] = cnt;
        }
        for(int a0 = 0; a0 < g; a0++){
            int n = in.nextInt();
            if (p[n] % 2 == 1) {
                System.out.println("Alice");
            } else {
                System.out.println("Bob");
            }
        }
    }
}