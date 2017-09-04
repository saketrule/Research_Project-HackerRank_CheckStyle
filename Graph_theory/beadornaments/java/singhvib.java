//package GraphTheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String l[] = br.readLine().split(" ");
            int b[] = new int[l.length];
            for (int j = 0; j < l.length; j++) {
                b[j] = Integer.parseInt(l[j]);
            }
            solve(N, b);
        }
    }

    public static void solve(int N, int b[]) {
        BigInteger numb[] = new BigInteger[b.length];
        BigInteger temp = BigInteger.ONE;

        // CAYLEY'S FORMULA
        for (int i = 0; i < N; i++) {
            if (b[i] <= 2) {
                numb[i] = BigInteger.ONE;
            } else {
                numb[i] = BigInteger.valueOf(b[i]).pow(b[i] - 2);
            }
            temp = temp.multiply(numb[i]);
        }

        BigInteger allprod = BigInteger.ONE;
        BigInteger allsum = BigInteger.ZERO;
        BigInteger res;

        for (int i = 0; i < N; i++) {
            allprod = allprod.multiply(BigInteger.valueOf(b[i]));
            allsum = allsum.add(BigInteger.valueOf(b[i]));
        }

        if (N < 2) {
            res = temp;
        } else if (N == 2) {
            res = allprod.multiply(temp);
        } else {
            res = (allsum.pow(b.length - 2)).multiply(allprod).multiply(temp);
        }

        res = res.mod(BigInteger.valueOf(1000000007));
        System.out.println(res);
    }
}