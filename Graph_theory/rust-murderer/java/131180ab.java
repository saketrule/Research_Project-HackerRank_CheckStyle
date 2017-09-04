import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static int INF = Integer.MAX_VALUE >> 2;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; ++i) {
            int N = in.nextInt();
            int M = in.nextInt();
            ArrayList<HashSet<Integer>> V = new ArrayList<HashSet<Integer>>();
            for (int n = 0; n <= N; ++n) {
                V.add(new HashSet<Integer>());
            }
            for (int j = 0; j < M; ++j) {
                int x = in.nextInt();
                int y = in.nextInt();
                V.get(x).add(y);
                V.get(y).add(x);
            }
            int S = in.nextInt();
            String ret = solve(N, M, S, V);
            System.out.println(ret);
        }
    }

    private static String solve(int N, int M, int S, ArrayList<HashSet<Integer>> V) {
        int[] dist = new int[N+1];

        List<Integer> notVisited = new LinkedList<Integer>();
        for (int i = 1; i <= N; ++i) {
            if (i != S) {
                notVisited.add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(S);
        dist[S] = 0;
        while (queue.peek() != null && notVisited.size() != 0) {
            int v1 = queue.poll();
            for (Iterator<Integer> iterator = notVisited.iterator(); iterator.hasNext();) {
                int v2 = iterator.next();
                if (!V.get(v1).contains(v2)) {
                    dist[v2] = dist[v1] + 1;
                    iterator.remove();
                    queue.add(v2);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int d = 1; d < dist.length; ++d) {
            if (d != S) {
                sb.append(Integer.toString(dist[d]) + " ");
            }
        }
        return sb.toString();
    }
}