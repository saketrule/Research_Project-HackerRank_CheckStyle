import java.io.*;
import java.util.*;

public class Solution {

 BufferedReader br;
 PrintWriter out;
 StringTokenizer st;
 boolean eof;

 void solve() throws IOException {
  int n = nextInt();
  int[] a = new int[n];
  for (int i = 0; i < n; i++) {
   a[i] = nextInt();
  }
  Arrays.sort(a);
  
  if (n == 0) {
   out.println(0);
   return;
  }
  
  int ans = Integer.MAX_VALUE;
  int[] q = new int[n];
  int head = 0;
  int tail = 0;
  int prev = Integer.MIN_VALUE;
  for (int i = 0; i < n; ) {
   int j = i;
   while (j < n && a[i] == a[j]) {
    j++;
   }
   int k = j - i;
   if (a[i] != prev + 1) {
    if (tail != head) {
     ans = Math.min(ans, q[tail - 1]);
    }
    head = tail = 0;
   }
//   if (a[i] == 2) {
//    System.err.println(head + " " + tail + " " + k);
//   }
   if (k > tail - head) {
    for (int zzz = head; zzz < tail; zzz++)
     q[zzz]++;
    while (k > tail - head) {
     q[tail++] = 1;
    }
   } else if (k == tail - head) {
    for (int zzz = head; zzz < tail; zzz++)
     q[zzz]++;
   } else { // k < tail - head
    while (k < tail - head) {
     ans = Math.min(ans, q[head++]);
    }
    for (int zzz = head; zzz < tail; zzz++)
     q[zzz]++;
   }
   prev = a[i];
   i = j;
  }
  for (int zzz = head; zzz < tail; zzz++) {
   ans = Math.min(ans, q[zzz]);
  }
  out.println(ans);
 }

 Solution() throws IOException {
  br = new BufferedReader(new InputStreamReader(System.in));
  out = new PrintWriter(System.out);
  int t = nextInt();
  while (t-- > 0) {
   solve();   
  }
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