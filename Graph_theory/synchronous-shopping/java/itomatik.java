import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    InputStream inputStream = System.in;
    OutputStream outputStream = System.out;
    if (args.length > 0) {
      inputStream = new FileInputStream(args[0]);
    }
    InputReader in = new InputReader(inputStream);
    PrintWriter out = new PrintWriter(outputStream);
    Task_v2 solver = new Task_v2();
    solver.solve(in, out);
    out.close();
  }

  public static class Node implements Comparable<Node> {
    public int v, t, mask;

    public Node(int v, int t, int mask) {
      this.v = v;
      this.t = t;
      this.mask = mask;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof Node)) {
        return false;
      }
      Node node = (Node)o;
      return (this.v == node.v) && (this.mask == node.mask);
    }

    @Override
    public int compareTo(Node node) {
      //return t.compareTo(node.t);
      if (t == node.t) return 0;
      return t > node.t ? +1 : -1;
    }

    @Override
    public int hashCode() {
      return Objects.hash(v, mask);
    }

    @Override
    public String toString() {
      return "v:" + v + " t:" + t + " mask:" + Integer.toBinaryString(mask) ;
    }
  }

  static class Edge {
    public int v, c;
    public Edge(int v, int c) {
      this.v = v;
      this.c = c;
    }
  }

  static class CityAndMask {
    public int v, mask;
    public CityAndMask(int v, int mask) {
      this.v = v;
      this.mask = mask;
    }

    @Override
    public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof CityAndMask)) {
        return false;
      }
      CityAndMask cityAndMask = (CityAndMask)o;
      return (this.v == cityAndMask.v) && (this.mask == cityAndMask.mask);
    }

    @Override
    public int hashCode() {
      return Objects.hash(v, mask);
    }

    @Override
    public String toString() {
      return "v:" + v + " mask:" + mask;
    }
  }

  static class Task_v2 {
    int n, m, k;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    int[] T;
    int[] masks;
    List<Edge>[] g;
    HashMap<CityAndMask, Integer> bestTime = new HashMap<>();
    int[][] dp;
    boolean[][] vst;

    public void solve(InputReader  in, PrintWriter out) {
      n = in.nextInt();
      m = in.nextInt();
      k = in.nextInt();

      dp = new int[n][];
      vst = new boolean[n][];
      for (int i = 0; i < n; i++) {
        dp[i] = new int[1 << k];
        Arrays.fill(dp[i], Integer.MAX_VALUE);
        vst[i] = new boolean[1 << k];
        Arrays.fill(vst[i], false);
      }

      masks = new int[n];
      T = new int[n];

      for (int i = 0; i < n; i++) {
        T[i] = in.nextInt();
        int curMask = 0;
        for (int j = 0; j < T[i]; j++) {
          int fishType = in.nextInt();
          curMask |= (1 << (fishType - 1));
        }
        masks[i] = curMask;
      }

      int result = Integer.MAX_VALUE;

      g = new ArrayList[n];
      for (int i = 0; i < n; i++) {
        g[i] = new ArrayList<>();
      }

      for (int i = 0; i < m; i++) {
        int v1 = in.nextInt();
        int v2 = in.nextInt();
        int c = in.nextInt();
        v1--;
        v2--;
        g[v1].add(new Edge(v2, c));
        g[v2].add(new Edge(v1, c));
      }

      int allMask = (1 << k) - 1;

      pq.add(new Node(0, 0, masks[0]));
      while (!pq.isEmpty()) {
        Node cur = pq.poll();

        if (vst[cur.v][cur.mask]) {
          continue;
        }
        vst[cur.v][cur.mask] = true;
        dp[cur.v][cur.mask] = cur.t;

        if (cur.v == n - 1 && cur.mask == allMask) {
          // no need to go further we already have all we need and located in the final destination.
          continue;
        }

        for (Edge e1 : g[cur.v]) {
          if (vst[e1.v][cur.mask | masks[e1.v]]) {
            continue;
          }
          Node nextNode = new Node(e1.v, cur.t + e1.c, cur.mask | masks[e1.v]);
          pq.add(nextNode);
        }
      }


      // suppose cat 1 gets only mask1 products
      for (int mask1 = 0; mask1 <= allMask; mask1++) {
        if (!vst[n - 1][mask1]) continue;
        int time1 = dp[n - 1][mask1];
        for (int mask2 = 0; mask2 <= allMask; mask2++) {
          if ((mask1 | mask2) == allMask) {
            if (!vst[n - 1][mask2]) continue;
            int time2 = dp[n - 1][mask2];
            result = Math.min(result, Math.max(time1, time2));
          }
        }
      }

      out.println(result);
    }
  }

  static class InputReader {
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
}