import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class GraphRoadsInHackerLand {
  // problem https://www.hackerrank.com/challenges/johnland
  InputStream is;
  PrintWriter out;
  String INPUT =  "5 6\n" + 
                  "1 3 5\n" + 
                  "4 5 0\n" + 
                  "2 1 3\n" + 
                  "3 2 1\n" + 
                  "4 3 4\n" + 
                  "4 2 2\n";


  
  private static class Point {
    int u;
    private TreeMap<Integer, Integer> path;

    public Point(int u, TreeMap<Integer, Integer> path) {
      this.u = u;
      this.path = path;
    }
  }

  void solve() {
    int n = ni();
    int e = ni();
    int[] u = new int[e];
    int[] v = new int[e];
    int[] w = new int[e];
    for (int i = 0; i < e; i++) {
      u[i] = ni() - 1;
      v[i] = ni() - 1;
      w[i] = ni() + 1;
    }
    int[][][] g = pack(u, v, w, n);
    // dump(g);

    shortestPath(g, n, 0);

    char[] res = new char[200020];
    Arrays.fill(res, '0');
    for (int i = 0; i < n; i++) {
      TreeMap<Integer, Integer>[] d = shortestPath(g, n, i);
      for (int k = i + 1; k < n; k++) {
        TreeMap<Integer, Integer> min = d[k];
        for (int power : min.keySet()) {
          int value = min.get(power);
          while (value > 0) {
            int dst = res.length - power; 
            while (true) {
              if (res[dst] == '0') {
                res[dst] = '1';
                break;
              }
              res[dst] = '0';
              dst--;
            }
            value--;
          }
        }
      }
    }
    for (int i = 0; i < res.length; i++) {
      if (res[i] == '1') {
        System.out.println(new String(res, i, res.length - i));
        break;
      }
    }
  }

  private TreeMap<Integer, Integer>[] shortestPath(int[][][] g, int n, int src) {
    TreeMap<Integer, Integer>[] d = new TreeMap[n];
    int[] from = new int[n];
    for (int i = 0; i < n; i++) {
      if (i == src) {
      d[i] = new TreeMap<Integer, Integer>();
      } else {
        d[i] = new TreeMap<Integer, Integer>();
        d[i].put(10_000_000, 1);
      }
    }
    PriorityQueue<Point> q = new PriorityQueue<>(n, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return less(o1.path, o2.path);
      }
    });
    q.add(new Point(src, new TreeMap<Integer, Integer>()));
    from[src] = src;
    while (!q.isEmpty()) {
      Point p = q.poll();
      int cur = p.u;
      int cmp = less(d[cur], p.path);
      if (cmp < 0) {
        continue;
      }
      // System.out.println(String.format("cur = %s d = %s cost = %s", cur, d[cur], p.path));
      for (int i = 0; i < g[cur].length; i++) {
        int next = g[cur][i][0];
        int weight = g[cur][i][1];
        TreeMap<Integer, Integer> newPath = (TreeMap<Integer, Integer>) p.path.clone();
        Integer cnt = newPath.get(weight);
        if (cnt == null) {
          newPath.put(weight, 1);
        } else {
          newPath.put(weight, cnt + 1);
        }
        if (less(newPath, d[next]) < 0) {
          d[next] = newPath;
          q.add(new Point(next, newPath));
        }
      }
      // try {
      //   TimeUnit.MILLISECONDS.sleep(1000);
      // } catch (Exception e) {
      //   e.printStackTrace();
      // }
    }
    return d;
  }

  public int less(TreeMap<Integer, Integer> t1, 
      TreeMap<Integer, Integer> t2) {
    if (t1 == t2) return 0;
    if (t1 == null && t2 == null) return 0;
    if (t1 != null && t2 == null) return 1;
    if (t1 == null && t2 != null) return -1;
    if (t1.size() == 0 && t2.size() == 0) return 0;
    if (t1.size() > 0 && t2.size() == 0) return 1;
    if (t2.size() > 0 && t1.size() == 0) return -1;
    
    Integer lastKey1 = t1.lowerKey(20_000_000);
    Integer lastKey2 = t2.lowerKey(20_000_000);
    while (lastKey1 != null && lastKey2 != null) {
      int res = Integer.compare(lastKey1, lastKey2);
      if (res != 0) {
        return res;
      }
      res = Integer.compare(t1.get(lastKey1), t2.get(lastKey1));
      if (res != 0) {
        return res;
      }
      lastKey1 = t1.lowerKey(lastKey1);
      lastKey2 = t2.lowerKey(lastKey2);
    }
    if (lastKey1 == null && lastKey2 == null) return 0;
    return lastKey1 == null ? -1 : 1; 
  }

  public void dump(int[][][] g) {
    for (int i = 0; i < g.length; i++) {
      System.out.println(i);
      for (int j = 0; j < g[i].length; j++) {
        System.out.print(" " + Arrays.toString(g[i][j]));
      }
      System.out.println();
    }
  }
  public int[][][] pack(int[] u, int[] v, int[] w, int n) {
    int[][][] g = new int[n][][];
    int e = u.length;
    int[] cnt = new int[n];
    for (int i = 0; i < e; i++) {
      cnt[u[i]]++;
      cnt[v[i]]++;
    }
    for (int i = 0; i < n; i++) {
      g[i] = new int[cnt[i]][2];
    }
    for (int i = 0; i < e; i++) {
      int index = --cnt[u[i]];
      g[u[i]][index][0] = v[i];
      g[u[i]][index][1] = w[i];
      index = --cnt[v[i]];
      g[v[i]][index][0] = u[i];
      g[v[i]][index][1] = w[i];
    }
    return g;
  }
  
  public static void main(String[] args) throws Exception {
    new GraphRoadsInHackerLand().run();
  }
  
  void run() throws Exception {
    is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
    out = new PrintWriter(System.out);
  
    long s = System.currentTimeMillis();
    solve();
    out.flush();
    tr(System.currentTimeMillis() - s + "ms");
  }
  
  private byte[] inbuf = new byte[1024];
  private int lenbuf = 0, ptrbuf = 0;
  
  private int readByte() {
    if (lenbuf == -1)
      throw new InputMismatchException();
    if (ptrbuf >= lenbuf) {
      ptrbuf = 0;
      try {
        lenbuf = is.read(inbuf);
      } catch (IOException e) {
        throw new InputMismatchException();
      }
      if (lenbuf <= 0)
        return -1;
    }
    return inbuf[ptrbuf++];
  }
  
  private boolean isSpaceChar(int c) {
    return !(c >= 33 && c <= 126);
  }
  
  private int skip() {
    int b;
    while ((b = readByte()) != -1 && isSpaceChar(b))
      ;
    return b;
  }
  
  private double nd() {
    return Double.parseDouble(ns());
  }
  
  private char nc() {
    return (char) skip();
  }
  
  private String ns() {
    int b = skip();
    StringBuilder sb = new StringBuilder();
    while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
      sb.appendCodePoint(b);
      b = readByte();
    }
    return sb.toString();
  }
  
  private char[] ns(int n) {
    char[] buf = new char[n];
    int b = skip(), p = 0;
    while (p < n && !(isSpaceChar(b))) {
      buf[p++] = (char) b;
      b = readByte();
    }
    return n == p ? buf : Arrays.copyOf(buf, p);
  }
  
  private char[][] nm(int n, int m) {
    char[][] map = new char[n][];
    for (int i = 0; i < n; i++)
      map[i] = ns(m);
    return map;
  }
  
  private int[] na(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = ni();
    return a;
  }
  
  private int ni() {
    int num = 0, b;
    boolean minus = false;
    while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = readByte();
    }
  
    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = readByte();
    }
  }
  
  private long nl() {
    long num = 0;
    int b;
    boolean minus = false;
    while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
      ;
    if (b == '-') {
      minus = true;
      b = readByte();
    }
  
    while (true) {
      if (b >= '0' && b <= '9') {
        num = num * 10 + (b - '0');
      } else {
        return minus ? -num : num;
      }
      b = readByte();
    }
  }
  
  private boolean oj = true;
  
  private void tr(Object... o) {
    if (!oj)
      System.out.println(Arrays.deepToString(o));
  }

}