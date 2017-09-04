import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Solution {
 BufferedReader reader;
    StringTokenizer tokenizer;
    PrintWriter out;

 public void solve() throws IOException {
  int N = nextInt();
        int[] A = new int[N];
        int minA = Integer.MAX_VALUE / 2;
        int maxA = 0;
  for (int i = 0; i < N; i++) {
            A[i] = nextInt();
            minA = Math.min(minA, A[i]);
            maxA = Math.max(maxA, A[i]);
        }
        Arrays.sort(A);

        int P = nextInt();
        int Q = nextInt();

        int best = 0;
        int best_index = -1;
        for (int n = 0; n < N-1; n++) {
            int l = Math.max(A[n], P);
            int r = Math.min(A[n+1], Q);

//            int l = Math.max(minA, P);
//            int r = Math.min(maxA, Q);
            while (r - l > 5) {
                int m1 = l + (r - l) / 3;
                int m2 = r - (r - l) / 3;
                if (calc(A, m1) < calc(A, m2)) {
                    l = m1;
                } else {
                    r = m2;
                }
            }


            for (int i = l; i <= r; i++) {
                if (calc(A, i) > best || (calc(A, i) == best && i < best_index)) {
                    best = calc(A, i);
                    best_index = i;
                }
            }
        }

        if (calc(A, P) > best || (calc(A, P) == best && P < best_index)) {
            best = calc(A, P);
            best_index = P;
        }
        if (calc(A, Q) > best || (calc(A, Q) == best && Q < best_index)) {
            best = calc(A, Q);
            best_index = Q;
        }

        out.println(best_index);
 }

    public int calc(int[] A, int mid) {
        int ret = Integer.MAX_VALUE/2;
        for (int i = 0; i < A.length; i++) {
            ret = Math.min(ret, Math.abs(A[i] - mid));
        }

//        out.println(mid + " " + ret);
        return ret;
    }

 /**
  * @param args
  */
 public static void main(String[] args) {
  new Solution().run();
 }

 public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
            out = new PrintWriter(System.out);
            solve();
            reader.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

}