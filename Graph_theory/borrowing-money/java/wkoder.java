import java.io.*;
import java.util.*;

public class Solution {
    private static int[] C;
    private static List<Integer>[] adj;
    private static List<Integer> comp = new ArrayList<>();
    private static int[] compIndex;
    private static Map<Long, Integer> memo = new HashMap<>();

    private static void go(long used, int money) {
        if (memo.containsKey(used)) {
            return;
        }

        for (int i = 0; i < comp.size(); i++) {
            if ((used & (1 << i)) > 0) {
                continue;
            }

            boolean ok = true;
            for (int j : adj[comp.get(i)]) {
                int k = compIndex[j];
                ok &= (used & (1 << k)) == 0;
            }
            if (!ok) {
                continue;
            }

            go(used | (1 << i), money + C[comp.get(i)]);
        }

        memo.put(used, money);
    }

    @SuppressWarnings("unchecked")
    private static void solve() throws Exception {
        int N = in.nextInt();
        int M = in.nextInt();
        C = new int[N];
        for (int i = 0; i < N; i++) {
            C[i] = in.nextInt();
        }
        adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        while (M-- > 0) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        int maxC = 0;
        long ways = 1;
        boolean[] vis = new boolean[N];
        compIndex = new int[N];
        for (int i = 0; i < N; i++) {
            if (vis[i]) {
                continue;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            comp.clear();
            compIndex[i] = 0;
            comp.add(i);
            vis[i] = true;
            while (!queue.isEmpty()) {
                int k = queue.poll();
                for (int w : adj[k]) {
                    if (vis[w]) {
                        continue;
                    }

                    vis[w] = true;
                    queue.add(w);
                    compIndex[w] = comp.size();
                    comp.add(w);
                }
            }

            memo.clear();
            go(0, 0);

            int max = -1;
            int w = 0;
            for (Map.Entry<Long, Integer> entry : memo.entrySet()) {
                if (entry.getValue() == max) {
                    w++;
                } else if (entry.getValue() > max) {
                    max = entry.getValue();
                    w = 1;
                }
            }
            ways *= w;
            maxC += max;
        }

        out.println(maxC + " " + ways);
    }

    private static Reader in = new Reader();
    private static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static void main(String[] args) throws Exception {
        solve();
        out.flush();
    }

    private static class Reader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        Reader() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = new StringTokenizer("");
        }

        String next() throws IOException {
            while (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}