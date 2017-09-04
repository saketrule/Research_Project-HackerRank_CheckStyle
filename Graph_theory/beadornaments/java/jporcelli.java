import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
           Scanner stdin = new Scanner(System.in);

        int T, N;

        T = stdin.nextInt();

        while (T-- > 0) {

            N = stdin.nextInt();

            int[] colors = new int[N];
            BigInteger[] trees = new BigInteger[N];

            BigInteger ornaments;

            for (int i = 0; i < N; i++) {
                colors[i] = stdin.nextInt();
            }

            BigInteger t = BigInteger.valueOf(1);
            for (int i = 0; i < N; i++) {
                trees[i] = numberOfTrees(colors[i]);
                t = t.multiply(trees[i]);
            }

            BigInteger sp = BigInteger.valueOf(1);
            BigInteger ss = BigInteger.valueOf(0);
            BigInteger tot = BigInteger.valueOf(0);

            for (int i = 0; i < N; i++) {
                sp = sp.multiply(BigInteger.valueOf(colors[i]));
                ss = ss.add(BigInteger.valueOf(colors[i]));
            }

            if (N > 2) {
                ss = ss.pow(colors.length - 2);
                ss = ss.multiply(sp);
                tot = ss;
                ornaments = tot.multiply(t);
            } else if (N == 2) {
                tot = sp;
                ornaments = tot.multiply(t);
            } else {
                ornaments = t;
            }

            System.out.println(ornaments.mod(BigInteger.valueOf(1000000007)));
        }
    }

    public static BigInteger numberOfTrees(int beadCount) {
        if (beadCount <= 2) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(beadCount).pow(beadCount - 2);
    }
}