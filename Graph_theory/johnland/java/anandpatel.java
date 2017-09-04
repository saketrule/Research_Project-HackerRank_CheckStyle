import java.util.*;
import java.io.*;

public class A {
 public static void main(String[] args) {
  new A().solve();
 }

 FasterScanner in = new FasterScanner();
 PrintWriter out = new PrintWriter(System.out);

 long mod = (long) 1e9 + 7;

 public void solve() {
  // int tt=in.nextInt();
  // for(int ii=0;ii<tt;ii++)
  {
   n = in.nextInt();
   int m = in.nextInt();
   g = new Node[n];
   t = new ArrayList[n];
   for (int i = 0; i < n; i++) {
    g[i] = new Node(i);
    t[i] = new ArrayList<>();
   }
   TreeSet<Node> tree = new TreeSet<>();
   g[0].val = 0;
   g[0].pa = -1;
   for (int i = 0; i < g.length; i++) {
    tree.add(g[i]);
   }
   for (int i = 0; i < m; i++) {
    int a = in.nextInt() - 1;
    int b = in.nextInt() - 1;
    int c = in.nextInt();
    g[a].next.add(new Edge(g[b], c));
    g[b].next.add(new Edge(g[a], c));
   }
   while (!tree.isEmpty()) {
    Node ver = tree.first();
    tree.remove(ver);
    if (ver.pa != -1)
     t[ver.pa].add(new Edge(ver, ver.val));
    for (Edge e : ver.next) {
     Node c = e.next;
     if (tree.contains(c)) {
      tree.remove(c);
      if (c.val > e.val) {
       c.pa = ver.idx;
       c.val = e.val;
      }
      tree.add(c);
     }
    }
   }
   size = new int[n];
   arr = new long[m + 20];
   dfs(0);
   for (int i = 0; i < arr.length - 2; i++) {
    if (arr[i] % 2 == 0) {
     arr[i + 1] += arr[i] / 2;
     arr[i] = 0;
    } else {
     arr[i + 1] += arr[i] / 2;
     arr[i] = 1;
    }
   }
//   System.out.println(Arrays.toString(arr));
   boolean b = false;
   for (int i = arr.length - 1; i >= 0; i--) {
    if (arr[i] == 1) {
     b = true;
    }
    if (b) {
     out.print(arr[i]);
    }
   }
   out.println();
  }
  out.close();
 }

 Node[] g;
 ArrayList<Edge>[] t;
 int[] size;
 long[] arr;
 int n;

 void dfs(int idx) {
  int temp = 0;
  // System.out.print(" dfs " + idx);
  // for(Edge x: t[idx]) {
  // System.out.print(" "+x.next.idx);
  // }
  int sum = 1;
//  System.out.println();
  for (Edge x : t[idx]) {

   dfs(x.next.idx);

   temp = size[x.next.idx];
   sum += temp;
   arr[x.val] += temp*1l * (n - temp);
  }
  // System.out.println();
  size[idx] = sum;
 }

 class Edge {
  Node next;
  int val;

  public Edge(Node n, int val) {
   this.next = n;
   this.val = val;

  }
 }

 class Node implements Comparable<Node> {
  int idx;
  int val;
  int pa;
  ArrayList<Edge> next = new ArrayList<>();

  public Node(int idx) {
   this.idx = idx;
   this.val = Integer.MAX_VALUE;
  }

  public int compareTo(Node o) {
   if (this.val != o.val)
    return Integer.compare(this.val, o.val);
   return Integer.compare(this.idx, o.idx);
  }
 }

 public static class FasterScanner {
  private byte[] buf = new byte[1024];
  private int curChar;
  private int numChars;

  public int read() {
   if (numChars == -1)
    throw new InputMismatchException();
   if (curChar >= numChars) {
    curChar = 0;
    try {
     numChars = System.in.read(buf);
    } catch (IOException e) {
     throw new InputMismatchException();
    }
    if (numChars <= 0)
     return -1;
   }
   return buf[curChar++];
  }

  public String nextLine() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   StringBuilder res = new StringBuilder();
   do {
    res.appendCodePoint(c);
    c = read();
   } while (!isEndOfLine(c));
   return res.toString();
  }

  private boolean isEndOfLine(int c) {
   return c == '\n' || c == '\r' || c == -1;
  }

  public String nextString() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   StringBuilder res = new StringBuilder();
   do {
    res.appendCodePoint(c);
    c = read();
   } while (!isSpaceChar(c));
   return res.toString();
  }

  private boolean isSpaceChar(int c) {
   return c == '\n' || c == '\r' || c == -1 || c == ' ' || c == '\t';
  }

  public long nextLong() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   int sgn = 1;
   if (c == '-') {
    sgn = -1;
    c = read();
   }
   long res = 0;
   do {
    if (c < '0' || c > '9')
     throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = read();
   } while (!isSpaceChar(c));
   return res * sgn;
  }

  public int nextInt() {
   int c = read();
   while (isSpaceChar(c))
    c = read();
   int sgn = 1;
   if (c == '-') {
    sgn = -1;
    c = read();
   }

   int res = 0;
   do {
    if (c < '0' || c > '9')
     throw new InputMismatchException();
    res *= 10;
    res += c - '0';
    c = read();
   } while (!isSpaceChar(c));
   return res * sgn;
  }

  public double nextDouble() {
   return Double.parseDouble(nextString());
  }

  public int[] nextIntArray(int n) {
   int[] arr = new int[n];
   for (int i = 0; i < n; i++) {
    arr[i] = nextInt();
   }
   return arr;
  }

  public long[] nextLongArray(int n) {
   long[] arr = new long[n];
   for (int i = 0; i < n; i++) {
    arr[i] = nextLong();
   }
   return arr;
  }

  public int[] nextIntArray10(int n) {
   int[] arr = new int[n + 1];
   for (int i = 1; i <= n; i++) {
    arr[i] = nextInt();
   }
   return arr;
  }

  public long[] nextLongArray10(int n) {
   long[] arr = new long[n + 1];
   for (int i = 1; i <= n; i++) {
    arr[i] = nextLong();
   }
   return arr;
  }

  public int[] nextIntArray11(int n) {
   int[] arr = new int[n + 2];
   for (int i = 1; i <= n; i++) {
    arr[i] = nextInt();
   }
   return arr;
  }

  public long[] nextLongArray11(int n) {
   long[] arr = new long[n + 2];
   for (int i = 1; i <= n; i++) {
    arr[i] = nextLong();
   }
   return arr;
  }
 }

}