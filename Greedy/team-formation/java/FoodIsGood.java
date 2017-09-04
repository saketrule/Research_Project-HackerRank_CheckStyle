import java.io.*;
import java.util.*;

public class Solution {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        solve();
        out.close();
    }

    private static void solve() {
        int t = in.nextInt();
        while (t --> 0) {
            solveTestcase();
        }
    }

    private static void solveTestcase() {
        int n = in.nextInt();
        if (n == 0) {
            out.println(0);
            return;
        }
        
        int[] level = new int[n];
        for (int i = 0; i < n; ++i) {
            level[i] = in.nextInt();
        }
        randomShuffle(level);
        Arrays.sort(level);

        Map<Integer, PriorityQueue<Integer>> sizes = new HashMap<Integer, PriorityQueue<Integer>>();
        for (int i = 0; i < n; ++i) {
            int size = getSize(sizes, level[i] - 1);
            addSize(sizes, level[i], size + 1);
        }

        Integer result = null;
        for (PriorityQueue<Integer> q : sizes.values()) {
            Integer s = q.peek();
            if (s != null) {
                if (result == null || result.compareTo(s) > 0) {
                    result = s;
                }
            }
        }
        out.println(result);
    }

    private static void addSize(Map<Integer, PriorityQueue<Integer>> sizes, Integer i, Integer size) {
        PriorityQueue<Integer> q = sizes.get(i);
        if (q == null) {
            q = new PriorityQueue<Integer>();
            sizes.put(i, q);
        }
        q.add(size);
    }

    private static int getSize(Map<Integer, PriorityQueue<Integer>> sizes, Integer i) {
        PriorityQueue<Integer> q = sizes.get(i);
        if (q == null || q.peek() == null) {
            return 0;
        }
        return q.poll();
    }

    private static void randomShuffle(int[] a) {
        Random random = new Random();
        for (int i = 1; i < a.length; ++i) {
            int j = random.nextInt(i);
            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }
}

class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}