import java.math.BigInteger;
import java.util.Scanner;

public class Solution {

    private static BigInteger numTrees(int n) {
        if (n > 1) {
            return BigInteger.valueOf(n).pow(n - 2);
        } else {
            return BigInteger.ONE;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();

        for (int t = 0; t < testNum; t++) {
            int p = sc.nextInt();
            BigInteger module = BigInteger.valueOf(1000000007);
            int n = 0;
            int[] k = new int[p];
            for (int i = 0; i < p; i++) {
                int cur = sc.nextInt();
                n += cur;
                k[i] = cur;
            }
            BigInteger res;
            if (p > 1) {
                res = BigInteger.valueOf(n).pow(p - 2);
                for (int i = 0; i < p; i++) {
                    BigInteger cur = numTrees(k[i]).multiply(BigInteger.valueOf(k[i]));
                    res = res.multiply(cur);
                }
            } else {
                res = numTrees(k[0]);
            }
            res = res.mod(module);
            System.out.println(res.toString());
        }
    }
}