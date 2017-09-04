import java.io.*;
import java.util.*;

public class Solution {

 BufferedReader br;
 PrintWriter out;
 StringTokenizer st;
 boolean eof;

 int n;
 int[] a;
 int low, high;

 int ans;
 long ansVal = -1;

 void check(int x) {
  if (x < low || x > high) {
   return;
  }
  int val = Integer.MAX_VALUE;
  for (int i = 0; i < n; i++) {
   val = Math.min(val, Math.abs(x - a[i]));
  }

  if (val > ansVal || (val == ansVal && x < ans)) {
   ansVal = val;
   ans = x;
  }
 }

 void solve() throws IOException {
  n = nextInt();
  a = new int[n];
  for (int i = 0; i < n; i++) {
   a[i] = nextInt();
  }
  low = nextInt();
  high = nextInt();
  check(low);
  check(high);
  Arrays.sort(a);
  for (int i = 0; i < n - 1; i++) {
   check((a[i] + a[i + 1]) / 2);
  }
  out.println(ans);
 }

 Solution() throws IOException {
  br = new BufferedReader(new InputStreamReader(System.in));
  out = new PrintWriter(System.out);
  solve();
  out.close();
 }

 public static void main(String[] args) throws IOException {
  new Solution();
 }

 String nextToken() {
  while (st == null || !st.hasMoreTokens()) {
   try {
    st = new StringTokenizer(br.readLine());
   } catch (Exception e) {
    eof = true;
    return null;
   }
  }
  return st.nextToken();
 }

 String nextString() {
  try {
   return br.readLine();
  } catch (IOException e) {
   eof = true;
   return null;
  }
 }

 int nextInt() throws IOException {
  return Integer.parseInt(nextToken());
 }

 long nextLong() throws IOException {
  return Long.parseLong(nextToken());
 }

 double nextDouble() throws IOException {
  return Double.parseDouble(nextToken());
 }
}