import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    static int n;
    static int m;
    static int[] c;
    static int[][] g;
    static int mx;
    static int mxCnt;

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        m = s.nextInt();
        c = new int[n + 1];
        g = new int[n + 1][];
        int maxSumC = 0;
        c[0] = 0;
        g[0] = new int[n + 1];
        Arrays.fill(g[0], 0);
        for (int i = 1; i <= n; i++) {
            c[i] = s.nextInt();
            maxSumC += c[i];
            g[i] = new int[n + 1];
            Arrays.fill(g[i], 0);
        }
        for (int i = 1; i <= m; i++) {
            int f = s.nextInt();
            int t = s.nextInt();
            g[f][t] = 1;
            g[t][f] = 1;
        }
        mx = -1;
        mxCnt = 0;

        int restSumC = maxSumC;
        int[] vis = new int[n + 1];
        Arrays.fill(vis, 0);
        find(0, vis, 0);
        System.out.println(mx + " " + mxCnt);
    }

    private static void find(int idx, int[] vis, int sumC) {
        for (int i = idx + 1; i <= n; i++) {
            if (vis[i] == 0) {
                int[] tmpVis = Arrays.copyOf(vis, vis.length);
                tmpVis[i] = 1;
                int tmpSumC = sumC + c[i];
                for (int j = i + 1; j <= n; j++) {
                    if (g[i][j] == 1) {
                        tmpVis[j] = 1;
                    }
                }
                find(i, tmpVis, tmpSumC);
            }
        }
        if (idx > 0) {
            if (sumC > mx) {
//            printVis(vis);
                mx = sumC;
                mxCnt = 1;
            } else if (sumC == mx) {
//            printVis(vis);
                mxCnt++;
            }
        }
    }

    private static void printVis(int[] vis) {
        for (int i = 0; i < vis.length; i++) {
            System.out.print(", " + vis[i]);
        }
        System.out.println();
    }

}