import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        System.setIn(System.in);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 1 <= N <= 34
        int m = sc.nextInt(); // 0 <= M <= N(N-1)/2
        int moneys[] = new int[n];
        for (int i = 0; i < n; i++) {
            int c = sc.nextInt(); // 0 <= Ci <= 100;
            moneys[i] = c;
        }
        int array[][] = new int[n][n];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1; // 0 <= Ai <= N
            int b = sc.nextInt() - 1; // 0 <= Bi <= N
            array[a][b] = 1;
            array[b][a] = 1;
        }
        sc.close();

        Result max = new Result(0, 1);
        for (int i = 0; i < n; i++) {
//            System.out.println("start with " + i);
            Result r = solve(array, moneys, n, i);
            updateResult(max, r);
        }
        System.out.println(max);
    }

    private static Result solve(int[][] array, int[] moneys, int n, int current) {

        Result max = new Result(0, 1);

        if (current < n - 1) {
            int[] moneysNew = new int[moneys.length];
            System.arraycopy(moneys, 0, moneysNew, 0, moneys.length);
            for (int i = 0; i < n; i++) {
                if (current != i && array[current][i] == 1) {
                    moneysNew[i] = -1;
                }
            }
            for (int i = current + 1; i < n; i++) {
                if (array[current][i] == 1) {
                    continue;
                }
                Result r = solve(array, moneysNew, n, i);
                updateResult(max, r);
            }
        }

        max.money = max.money + moneys[current];


//        System.out.print("current " + current);
//        System.out.println(", max " + max);

        return max;
    }

    private static void updateResult(Result max, Result r) {
        if (max.money < r.money) {
            max.money = r.money;
            max.variants = r.variants;
        } else if (max.money == r.money && max.money != -1) {
            max.variants = max.variants + r.variants;
        }
    }

    private static class Result {
        private int money;
        private int variants;

        public Result(int money, int variants) {
            this.money = money;
            this.variants = variants;
        }

        @Override
        public String toString() {
            return String.valueOf(money) + " " + String.valueOf(variants);
        }
    }
}