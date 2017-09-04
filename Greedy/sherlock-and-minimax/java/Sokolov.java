import java.io.*;
import java.util.*;
import java.math.*;


public class Solution {
 static BufferedReader in;
 static PrintWriter out;
 static StringTokenizer tok;
 
 
 static void solve() throws Exception {
  int n = nextInt();
  int[] a = new int[n];
  for (int i = 0; i < n; ++i) {
   a[i] = nextInt();
  }
  int l = nextInt();
  int r = nextInt();
  Arrays.sort(a);
  int maxV = Integer.MIN_VALUE;
  int m = -1;
  if (l <= a[0]) {
   maxV = a[0]-l;
   m = l;
  }
  for (int i = 0; i < n-1; ++i) {
   if (a[i+1] < l) {
    continue;
   }
   if (a[i] > r) {
    break;
   }
   int mid = (a[i] + a[i+1]) / 2;
   if (mid >= l && mid <= r) {
    if (mid-a[i] > maxV) {
     maxV = mid-a[i];
     m = mid;
    }
   } else {
    if (l < mid) {
     if (r-a[i] > maxV) {
      maxV = r-a[i];
      m = r;
     }
    } else {
     if (a[i+1]-l > maxV) {
      maxV = a[i+1]-l;
      m = l;
     }
    }
   }
  }
  if (r >= a[n-1] && r-a[n-1] > maxV) {
   maxV = r-a[n-1];
   m = r;
  }
  out.println(m);
 }
 
 static int sqr(int x) {
  return x*x;
 }
 
 static int nextInt() throws IOException {
  return Integer.parseInt(next());
 }

 static long nextLong() throws IOException {
  return Long.parseLong(next());
 }

 static double nextDouble() throws IOException {
  return Double.parseDouble(next());
 }

 static BigInteger nextBigInteger() throws IOException {
  return new BigInteger(next());
 }
 
 static String next() throws IOException {
  while (tok == null || !tok.hasMoreTokens()) {
   tok = new StringTokenizer(in.readLine());
  }
  return tok.nextToken();
 }
 
 static String nextLine() throws IOException {
  tok = new StringTokenizer("");
  return in.readLine();
 }

 static boolean hasNext() throws IOException {
  while (tok == null || !tok.hasMoreTokens()) {
   String s = in.readLine();
   if (s == null) {
    return false;
   }
   tok = new StringTokenizer(s);
  }
  return true;
 }

 public static void main(String args[]) {
  try {
   in = new BufferedReader(new InputStreamReader(System.in));
   out = new PrintWriter(new OutputStreamWriter(System.out));
   //in = new BufferedReader(new FileReader("input.in"));
   //out = new PrintWriter(new FileWriter("output.out"));
   solve();
   in.close();
   out.close();
  } catch (Throwable e) {
   e.printStackTrace();
   java.lang.System.exit(1);
  }
 }
}