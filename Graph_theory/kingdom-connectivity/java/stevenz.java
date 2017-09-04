/* Enter your code here. Read input from STDIN. Print output to STDOUT */
import java.util.*;
import java.io.*;

public class Solution {
  BufferedReader in;
  PrintWriter out;
  StringTokenizer st;

  class Edge {
    int to;
    public Edge(int to) {
      this.to = to;
    }
  }

  class Node {
    List<Edge> adj = new ArrayList<Edge>();
  }

  Node[] nodes = new Node[10000+5];
  Node[] reverse = new Node[10000+5];
  
  boolean[] seen = new boolean[10000+5];

  int[] colour = new int[10000+5];

  int n;
  boolean[] can_reach = new boolean[10000+5];
  boolean has_cycle(int v) {
    if (colour[v] == 0) {
      boolean res = false;
      colour[v] = 2;
      for (Edge e : nodes[v].adj) {
        res |= has_cycle(e.to) && can_reach[e.to];
      }
      colour[v] = 1;
      return res;
    } else if (colour[v] == 2) {
      return true;
    } else {
      return false;
    }
  }

  final int MOD = 1000*1000*1000;
  int[] dp = new int[10000+5];
  int f(int v) {
    if (!can_reach[v]) {
      return 0;
    }
    if (dp[v] != -1) {
      return dp[v];
    }
    int ans = 0;
    for (Edge e : nodes[v].adj) {
      ans += f(e.to);
      ans %= MOD;
    }
    return dp[v] = ans;
  }

  boolean path_exists(int u, int v) {
    if (u == v) return true;
    if (!seen[u]) {
      seen[u] = true;
      boolean res = false;
      for (Edge e : nodes[u].adj) {
        res |= path_exists(e.to, v);
      }
      return res;
    }
    return false;
  }

  void calc_reach(int v) {
    if (!seen[v]) {
      seen[v] = true;
      can_reach[v] = true;
      for (Edge e: reverse[v].adj) {
        calc_reach(e.to);
      }
    }
  }

  public void solve() throws IOException {
    n = readInt();
    int m = readInt();
    for (int i = 1; i <= n; i++) {
      nodes[i] = new Node();
      reverse[i] = new Node();
    }
    for (int i = 0; i < m; i++) {
      int u = readInt();
      int v = readInt();
      nodes[u].adj.add(new Edge(v));
      reverse[v].adj.add(new Edge(u));
    }
    calc_reach(n);
    Arrays.fill(seen, false);
    if (!path_exists(1, n)) {
      out.println("0");
      return;
    }
    if (has_cycle(1)) {
      out.println("INFINITE PATHS");
      return;
    }
    Arrays.fill(dp, -1);
    dp[n] = 1;
    out.println(f(1));
  }

  public void run() throws IOException {
    in = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(System.out);
    solve();
    out.close();
    in.close();
  }

  public static void main(String[] args) throws IOException {
    new Solution().run();
  }

  String read() throws IOException {
    while (st == null || !st.hasMoreTokens()) {
      st = new StringTokenizer(in.readLine());
    }
    return st.nextToken();
  }

  public int readInt() throws IOException {
    return Integer.parseInt(read());
  }
}