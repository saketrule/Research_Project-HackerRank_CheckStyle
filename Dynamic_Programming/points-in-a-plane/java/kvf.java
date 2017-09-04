import java.io.*;
import java.util.*;

public class Solution {

 final int mod = (int) (1e9 + 7);

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

   boolean[] isCollinear = new boolean[1 << n];
   for (int i = 0; i < n; ++i) {
    for (int j = 0; j < n; ++j) {
     isCollinear[(1 << i) | (1 << j)] = true;
    }
   }

   for (int mask = 1; mask < (1 << n); ++mask) {
    if (isCollinear[mask] && Integer.bitCount(mask) >= 2) {
     int first = -1;
     int second = -1;
     for (int i = 0; i < n; ++i) {
      if (((mask >> i) & 1) != 0) {
       if (first == -1) {
        first = i;
       } else if (second == -1) {
        second = i;
       } else {
        break;
       }
      }
     }

     for (int next = 0; next < n; ++next) {
      if (((mask >> next) & 1) == 0) {
       if (checkCollinear(first, second, next, x, y)) {
        isCollinear[mask ^ (1 << next)] = true;
       }
      }
     }
    }
   }

   int[] moves = new int[1 << n];
   int[] count = new int[1 << n];
   Arrays.fill(moves, -1);
   Arrays.fill(count, -1);

   fillMoves((1 << n) - 1, isCollinear, moves);
   fillCount((1 << n) - 1, isCollinear, moves, count);
   out.println(moves[(1 << n) - 1] + " " + count[(1 << n) - 1]);
  }
 }

 private int fillCount(int mask, boolean[] isCollinear, int[] moves, int[] count) {
  if (count[mask] != -1) {
   return count[mask];
  }

  int res = 0;
  for (int nmask = mask; nmask > 0; nmask = (nmask - 1) & mask) {
   if (isCollinear[nmask] && moves[mask ^ nmask] + 1 == moves[mask]) {
    res += fillCount(mask ^ nmask, isCollinear, moves, count);
    if (res >= mod) {
     res -= mod;
    }
   }
  }

  if (res == 0) {
   res = 1;
  }
  return count[mask] = res;
 }

 private int fillMoves(int mask, boolean[] isCollinear, int[] moves) {
  if (moves[mask] != -1) {
   return moves[mask];
  }

  if (mask == 0) {
   return 0;
  }

  int res = Integer.MAX_VALUE;
  for (int nmask = mask; nmask > 0; nmask = (nmask - 1) & mask) {
   if (isCollinear[nmask]) {
    int cur = fillMoves(mask ^ nmask, isCollinear, moves) + 1;
    if (cur < res) {
     res = cur;
    }
   }
  }
  return moves[mask] = res;
 }

 private boolean checkCollinear(int i, int j, int k, int[] x, int[] y) {
  return (x[i] - x[j]) * (y[k] - y[j]) - (x[k] - x[j]) * (y[i] - y[j]) == 0;
 }

 public void run() {
  try {
   solution();
   in.reader.close();
   out.close();
  } catch (Exception e) {
   e.printStackTrace();
   System.exit(1);
  }
 }

 private class Scanner {
  StringTokenizer tokenizer;

  BufferedReader reader;

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

  public String nextLine() throws IOException {
   tokenizer = new StringTokenizer("");
   return reader.readLine();
  }

  public int nextInt() throws IOException {
   return Integer.parseInt(next());
  }

  public long nextLong() throws IOException {
   return Long.parseLong(next());
  }
 }

 public static void main(String[] args) throws IOException {
  new Solution().run();
 }

 Scanner in = new Scanner(new InputStreamReader(System.in));
 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
}