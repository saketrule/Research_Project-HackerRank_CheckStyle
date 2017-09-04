import java.io.*;
import java.util.*;
import java.math.*;

public class Superman implements Runnable {
 public static void main(String[] args) {
  new Thread(new Superman()).start();
 }

 final static int MAXN = 2000;
 public void run() {
  InputReader in = new InputReader(System.in);
  PrintWriter out = new PrintWriter(System.out);

  int n = in.nextInt();
  int h = in.nextInt();
  int l = in.nextInt();

  int[][] save = new int[MAXN][MAXN];
  for (int i = 0; i < n; ++i) {
   int s = in.nextInt();
   while (s-- > 0) {
    int x = in.nextInt();
    ++save[x][i];
   }
  }

  int[][] dp = new int[MAXN][MAXN];
  int[] opt = new int[MAXN];
  for (int i = h; i >= 0; --i) {
   for (int j = 0; j < n; ++j) {
    dp[i][j] = dp[i + 1][j] + save[i][j];
    if (i + l <= h + 1) {
     dp[i][j] = Math.max(dp[i][j], opt[i + l] + save[i][j]);
    }
    opt[i] = Math.max(opt[i], dp[i][j]);
   }
  }

  out.println(opt[0]);

  out.close();
 }
}

class InputReader {
 BufferedReader buff;
 StringTokenizer tokenizer;

 InputReader(InputStream stream) {
  buff = new BufferedReader(new InputStreamReader(stream));
  tokenizer = null;
 }
 boolean hasNext() {
  while (tokenizer == null || !tokenizer.hasMoreTokens())
   try {
    tokenizer = new StringTokenizer(buff.readLine());
   }
   catch (Exception e) {
    return false;
   }
  return true;
 }
 String next() {
  if (!hasNext())
   throw new RuntimeException();
  return tokenizer.nextToken();
 }
 int nextInt() { return Integer.parseInt(next()); }
 long nextLong() { return Long.parseLong(next()); }
}