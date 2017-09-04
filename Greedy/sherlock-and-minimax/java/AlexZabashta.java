import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.util.Map.Entry;
import static java.lang.Math.*;

public class Solution {

 void run() {
  int n = nextInt();
  int[] a = nextArray(n);
  int l = nextInt(), r = nextInt();

  Arrays.sort(a);

  int cur = abs(a[0] - l);
  for (int q : a) {
   int temp = abs(q - l);
   if (cur > temp) {
    cur = temp;
   }
  }

  int d = cur, m = l;

  cur = abs(a[0] - r);
  for (int q : a) {
   int temp = abs(q - r);
   if (cur > temp) {
    cur = temp;
   }
  }

  if (d < cur) {
   d = cur;
   m = r;
  }

  for (int i = 1; i < n; i++) {
   int x = a[i - 1], y = a[i];
   if (x + 1 < y) {
    int z = (x + y) / 2;
    if (l <= z && z <= r) {
     cur = min(abs(x - z), abs(y - z));
     if (d < cur) {
      d = cur;
      m = z;
     }
    }
   }
  }

  out.println(m);

 }

 int[][] nextMatrix(int n, int m) {
  int[][] matrix = new int[n][m];
  for (int i = 0; i < n; i++)
   for (int j = 0; j < m; j++)
    matrix[i][j] = nextInt();
  return matrix;
 }

 String next() {
  while (!st.hasMoreTokens())
   st = new StringTokenizer(nextLine());
  return st.nextToken();
 }

 boolean hasNext() {
  while (!st.hasMoreTokens()) {
   String line = nextLine();
   if (line == null) {
    return false;
   }
   st = new StringTokenizer(line);
  }
  return true;
 }

 int[] nextArray(int n) {
  int[] array = new int[n];
  for (int i = 0; i < n; i++) {
   array[i] = nextInt();
  }
  return array;
 }

 int nextInt() {
  return Integer.parseInt(next());
 }

 long nextLong() {
  return Long.parseLong(next());
 }

 double nextDouble() {
  return Double.parseDouble(next());
 }

 String nextLine() {
  try {
   return in.readLine();
  } catch (IOException err) {
   return null;
  }
 }

 static PrintWriter out;
 static BufferedReader in;
 static StringTokenizer st = new StringTokenizer("");
 static Random rnd = new Random();

 public static void main(String[] args) throws IOException {
  out = new PrintWriter(System.out);
  in = new BufferedReader(new InputStreamReader(System.in));
  new Solution().run();
  out.close();
  in.close();
 }
}