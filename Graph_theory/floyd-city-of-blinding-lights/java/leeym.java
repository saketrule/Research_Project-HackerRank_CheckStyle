import java.util.*;
public class Solution {
    static Map<Integer, Map<Integer, Integer>>E = new HashMap<Integer, Map<Integer, Integer>>();
    static Set<Integer>V = new HashSet<Integer>();
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int N = in.nextInt(), M = in.nextInt();
        for (int m = 0; m < M; m++) {
            final int X = in.nextInt() - 1, Y = in.nextInt() - 1, R = in.nextInt();
            if (!E.containsKey(X)) E.put(X, new HashMap<Integer, Integer>());
            E.get(X).put(Y, R);
            V.add(X);
            V.add(Y);
        }
        int[][] D = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                D[i][j] = i == j ? 0 : Integer.MAX_VALUE;
                if (E.containsKey(i) && E.get(i).containsKey(j)) D[i][j] = E.get(i).get(j);
            }
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (D[i][k] == Integer.MAX_VALUE || D[k][j] == Integer.MAX_VALUE) continue;
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }
        final int Q = in.nextInt();
        for (int q = 0; q < Q; q++) {
            final int A = in.nextInt() - 1;
            final int B = in.nextInt() - 1;
            System.out.println(D[A][B] == Integer.MAX_VALUE ? -1 : D[A][B]);
        }
    }
}