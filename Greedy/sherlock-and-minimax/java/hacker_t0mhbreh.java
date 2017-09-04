import java.io.*;
import java.util.*;

public class Solution {

    private int min(int[] arr, int mid) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < arr.length; k++) {
            min = Math.min(min, Math.abs(arr[k] - mid));
        }
        return min;
    }

    public void solve() throws IOException {
        int n = nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
        int p = nextInt();
        int q = nextInt();
        int minp = min(arr, p);
        int minq = min(arr, q);
        int result, M;

        if (minp >= minq) {
            result = minp;
            M = p;
        } else {
            result = minq;
            M = q;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int mid = (arr[i] + arr[j]) / 2;
                if (mid < p || mid > q) {
                    continue;
                }
                int min = min(arr, mid);
                if (min > result) {
                    result = min;
                    M = mid;
                }
            }
        }

        System.out.println(M);

    }

    public static void main(String[] args) {
        new Solution().run();
    }

    public void print1Int(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public void print2Int(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            tok = null;
            solve();
            in.close();
        } catch (IOException e) {
            System.exit(0);
        }
    }

    public String nextToken() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }
    BufferedReader in;
    StringTokenizer tok;
}