import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Math.min;
import static java.lang.Math.max;

public class Solution {

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();
  int k = in.nextInt();
  long[] values = new long[n + 2];
  for (int i = 0; i < n; i++)
   values[i + 1] = in.nextInt();
  long[] dp = new long[n + 2];
  dp[0] = 0;
  SegTreeRU T = new SegTreeRU(dp);
  for (int i = 1; i <= n + 1; i++) {
   int l = max(i - k - 1, 0);
   int r = i - 1;
   dp[i] = T.query(l, r) - values[i];
   T.update(i, i, dp[i]);
  }
  long sum = 0;
  for (int i = 1; i <= n; i++)
   sum += values[i];
  System.out.println(sum + dp[n + 1]);
 }
}

class SegTreeRU {
 long[] t;
 long[] u;
 int len;

 public SegTreeRU(long[] a) {
  t = new long[4 * a.length];
  u = new long[4 * a.length];
  len = a.length;
  Arrays.fill(t, -1);
  build(0, 0, a.length - 1, a);
 }

 public void build(int i, int l, int r, long[] a) {
  if (l == r)
   t[i] = a[l];
  else {
   build(i * 2 + 1, l, (l + r) / 2, a);
   build(i * 2 + 2, (l + r) / 2 + 1, r, a);
   t[i] = func(t[i * 2 + 1], t[i * 2 + 2]);
  }
 }

 public long query(int l, int r) {
  return query(0, 0, len - 1, l, r);
 }

 public long query(int i, int a, int b, int l, int r) {
  refresh(i);
  if (a == l && b == r)
   return t[i];
  int mid = (a + b) / 2;
  if (l <= mid && r > mid)
   return func(query(i * 2 + 1, a, mid, l, min((a + b) / 2, r)),
     query(i * 2 + 2, mid + 1, b, max((a + b) / 2 + 1, l), r));
  else if (l <= mid)
   return query(i * 2 + 1, a, mid, l, min((a + b) / 2, r));
  else if (r > mid)
   return query(i * 2 + 2, mid + 1, b, max((a + b) / 2 + 1, l), r);
  return 0;
 }

 public void update(int l, int r, long v) {
  update(0, 0, len - 1, l, r, v);
 }

 public long func(long i, long j) {
  return (i >= j) ? i : j;
 }

 public void refresh(int i) {
  t[i] += u[i];
  if (i * 2 + 1 < t.length)
   u[i * 2 + 1] += u[i];
  if (i * 2 + 2 < t.length)
   u[i * 2 + 2] += u[i];
  u[i] = 0;
 }

 public void update(int i, int a, int b, int l, int r, long v) {
  refresh(i);
  if (a == l && b == r) {
   u[i] += v;
   refresh(i);
   return;
  }
  int mid = (a + b) / 2;
  if (l <= mid && r > mid) {
   update(i * 2 + 1, a, mid, l, min(mid, r), v);
   update(i * 2 + 2, mid + 1, b, max(mid + 1, l), r, v);
  } else if (l <= mid) {
   update(i * 2 + 1, a, mid, l, min(mid, r), v);
  } else if (r > mid) {
   update(i * 2 + 2, mid + 1, b, max(mid + 1, l), r, v);
  }
  t[i] = func(t[i * 2 + 1], t[i * 2 + 2]);
 }
}