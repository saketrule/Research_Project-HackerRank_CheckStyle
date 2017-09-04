import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.hackerrank.com/contests/zenhacks/challenges/rust-murderer
 * 
 * @author sultan.of.swing
 *
 */

public class Solution {

 private FasterScanner mFScanner;
 private PrintWriter mOut;

 public Solution() {
  mFScanner = new FasterScanner();
  mOut = new PrintWriter(System.out);
 }

 public void solve() {
  int T;
  int N, M;
  int u, v;
  int S;
  ArrayList<Integer> mAdj[];
  int res[];
  boolean visited[];
  HashSet<Integer> set1, set2, temp;
  Queue<Integer> queue = new LinkedList<>();

  T = mFScanner.nextInt();
  set1 = new HashSet<>();
  set2 = new HashSet<>();

  while (T-- > 0) {

   N = mFScanner.nextInt();
   M = mFScanner.nextInt();
   mAdj = new ArrayList[N];
   res = new int[N];
   visited = new boolean[N];
            set1.clear();
            set2.clear();

   for (int i = 0; i < N; i++) {
    mAdj[i] = new ArrayList<>();
    set1.add(i);
   }

   for (int i = 0; i < M; i++) {
    u = mFScanner.nextInt() - 1;
    v = mFScanner.nextInt() - 1;
    mAdj[u].add(v);
    mAdj[v].add(u);
   }

   S = mFScanner.nextInt() - 1;
   set1.remove(S);
   queue.add(S);
            visited[S] = true;

   while (!queue.isEmpty()) {
    u = queue.poll();

    for (int w : mAdj[u]) {
     if (!visited[w]) {
      set1.remove(w);
      set2.add(w);
     }
    }

    for (int node : set1) {
     res[node] = res[u] + 1;
     visited[node] = true;
     queue.add(node);
    }

    temp = set1;
    set1 = set2;
    set2 = temp;
    set2.clear();
                
                if (set1.isEmpty() && set2.isEmpty())
                    queue.clear();

   }

   for (int i = 0; i < N; i++) {
    if (i == S)
     continue;
    mOut.print(res[i]);
                if (i < N - 1)
        mOut.print(" ");
   }
            
            mOut.println();

  }

 }

 public void dfs(int u) {

 }

 public void flush() {
  mOut.flush();
 }

 public void close() {
  flush();
  mOut.close();
 }

 public static void main(String[] args) {
  Solution mSol = new Solution();
  mSol.solve();
  mSol.close();
 }

 class FasterScanner {
  private InputStream mIs;
  private byte[] buf = new byte[1024];
  private int curChar;
  private int numChars;

  public FasterScanner() {
   this(System.in);
  }

  public FasterScanner(InputStream is) {
   mIs = is;
  }

  public int read() {
   if (numChars == -1)
    throw new InputMismatchException();
   if (curChar >= numChars) {
    curChar = 0;
    try {
     numChars = mIs.read(buf);
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
   Double next;
   next = Double.parseDouble(nextString());
   return next;
  }

  public boolean isSpaceChar(int c) {
   return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
  }

  public boolean isEndOfLine(int c) {
   return c == '\n' || c == '\r' || c == -1;
  }

  public char[] nextCharArray(int N) {
   int i;
   char[] array;
   String str;

   array = new char[N];

   i = 0;

   str = nextLine();

   for (i = 0; i < N && i < str.length(); i++) {
    array[i] = str.charAt(i);
   }

   return array;
  }

  public char[][] nextChar2DArray(int M, int N) {
   int i;
   char[][] array;

   array = new char[M][N];

   i = 0;

   for (i = 0; i < M; i++) {
    array[i] = nextCharArray(N);
   }

   return array;
  }

  public int[] nextIntArray(int N) {
   int i;
   int[] array;

   array = new int[N];

   i = 0;

   for (i = 0; i < N; i++) {
    array[i] = nextInt();
   }

   return array;
  }

  public int[][] nextInt2DArray(int M, int N) {
   int i;
   int[][] array;

   array = new int[M][N];

   i = 0;

   for (i = 0; i < M; i++) {
    array[i] = nextIntArray(N);
   }

   return array;
  }

  public long[] nextLongArray(int N) {
   int i;
   long[] array;

   array = new long[N];

   i = 0;

   for (i = 0; i < N; i++) {
    array[i] = nextLong();
   }

   return array;
  }

  public long[][] nextLong2DArray(int M, int N) {
   int i;
   long[][] array;

   array = new long[M][N];

   i = 0;

   for (i = 0; i < M; i++) {
    array[i] = nextLongArray(N);
   }

   return array;
  }

  public double[] nextDoubleArray(int N) {
   int i;
   double[] array;

   array = new double[N];

   for (i = 0; i < N; i++) {
    array[i] = nextDouble();
   }

   return array;
  }

  public double[][] nextDouble2DArray(int M, int N) {
   int i;
   double[][] array;

   array = new double[M][N];

   for (i = 0; i < M; i++) {
    array[i] = nextDoubleArray(N);
   }

   return array;
  }

 }
}