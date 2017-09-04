import java.util.*;
import java.io.*;
import java.math.BigInteger;
import static java.lang.Math.*;

public class Solution {
 final int MOD = 1000000007;

 private void solution() throws IOException {
  int ts = in.nextInt();
  while (ts-- > 0) {
   int n = in.nextInt();
   int[] x = new int[n];
   int[] y = new int[n];
   for (int i = 0; i < n; ++i) {
    x[i] = in.nextInt();
    y[i] = in.nextInt();
   }
   int[] min = new int[1 << n];
   int[] ways = new int[1 << n];
   Arrays.fill(min, Integer.MAX_VALUE / 2);
   min[0] = 0;
   ways[0] = 1;
   int[] prev = new int[1 << n];
   Arrays.fill(prev, -1);
   boolean[] match = new boolean[1 << n];
   for (int set = 1; set < (1 << n); ++set) {
    if (Integer.bitCount(set) <= 2) {
     match[set] = true;
    } else {
     int first = -1;
     int second = -1;
     for (int i = 0; i < n; ++i) {
      if (((set >> i) & 1) != 0) {
       if (first == -1) {
        first = i;
       } else {
        second = i;
       }
      }
     }
     boolean ok = true;
     for (int i = 0; i < n; ++i) {
      if (((set >> i) & 1) != 0) {
       ok &= cross(x[first] - x[i], y[first] - y[i], x[second] - x[i], y[second] - y[i]) == 0;
      }
     }
     match[set] = ok;
    }
   }
   // for (int set = 1; set < (1 << n); ++set) {
   // if (match[set]) {
   // prev[set] = set;
   // } else {
   // prev[set] = prev[set & (set - 1)];
   // }
   // }
   for (int set = 1; set < (1 << n); ++set) {
    int curSet = set;
    while (curSet != 0) {
     if (match[curSet]) {
      if (min[set] > min[curSet ^ set] + 1) {
       min[set] = min[curSet ^ set] + 1;
       ways[set] = ways[curSet ^ set];
      } else if (min[set] == min[curSet ^ set] + 1) {
       ways[set] += ways[curSet ^ set];
       if (ways[set] >= MOD) {
        ways[set] -= MOD;
       }
      }
     }
     curSet = (curSet - 1) & set;
    }
   }
   out.println(min[(1 << n) - 1] + " " + ways[(1 << n) - 1]);
  }
 }

 private int cross(int x, int y, int x2, int y2) {
  return x * y2 - y * x2;
 }

 public void run() {
  try {
   solution();
   in.reader.close();
   out.close();
  } catch (Throwable e) {
   e.printStackTrace();
   System.exit(1);
  }
 }

 private void debug(Object... objects) {
  System.out.println(Arrays.toString(objects));
 }

 private static class Scanner {
  private BufferedReader reader;
  private StringTokenizer tokenizer;

  public Scanner(Reader reader) {
   this.reader = new BufferedReader(reader);
   this.tokenizer = new StringTokenizer("");
  }

  public boolean hasNext() throws IOException {
   while (!tokenizer.hasMoreTokens()) {
    String line = reader.readLine();
    if (line == null) {
     return false;
    }
    tokenizer = new StringTokenizer(line);
   }
   return true;
  }

  public String next() throws IOException {
   hasNext();
   return tokenizer.nextToken();
  }

  public int nextInt() throws IOException {
   return Integer.parseInt(next());
  }

  public double nextDouble() throws IOException {
   return Double.parseDouble(next());
  }

  public long nextLong() throws IOException {
   return Long.parseLong(next());
  }

  public String nextLine() throws IOException {
   tokenizer = new StringTokenizer("");
   return reader.readLine();
  }

  public int[] nextInts(int n) throws IOException {
   int[] res = new int[n];
   for (int i = 0; i < n; ++i) {
    res[i] = nextInt();
   }
   return res;
  }

  public long[] nextLongs(int n) throws IOException {
   long[] res = new long[n];
   for (int i = 0; i < n; ++i) {
    res[i] = nextLong();
   }
   return res;
  }

  public double[] nextDoubles(int n) throws IOException {
   double[] res = new double[n];
   for (int i = 0; i < n; ++i) {
    res[i] = nextDouble();
   }
   return res;
  }

  public String[] nextStrings(int n) throws IOException {
   String[] res = new String[n];
   for (int i = 0; i < n; ++i) {
    res[i] = next();
   }
   return res;
  }
 }

 public static void main(String[] args) throws Exception {
  new Solution().run();
 }
 private Scanner in = new Scanner(new InputStreamReader(System.in));
 private PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
}