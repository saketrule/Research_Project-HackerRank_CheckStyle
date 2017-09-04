import java.util.Scanner;

class Solution {

 public static long check(long x, long[] ar) {
  long ret = Integer.MAX_VALUE;
  for (int i = 0; i < ar.length; ++i)
   ret = Math.min(ret, Math.abs(x - ar[i]));
  return ret;
 }

 public static void limit(long p, long q, long[] ar, long x, long[] ret) {
  if (x < p || x > q)
   return;
  if (check(x, ar) > check(ret[0], ar))
   ret[0] = x;
 }

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int N = in.nextInt();
  long[] ar = new long[N];
  for (int i = 0; i < N; i++) {
   ar[i] = in.nextLong();
  }
  long p = in.nextLong();
  long q = in.nextLong();
  long[] ret = new long[1];
  ret[0] = p;
  limit(p, q, ar, q, ret);
  for (int i = 0; i < N; ++i)
   for (int j = i + 1; j < N; ++j) {
    // check for (A_i + A_j)/2
    limit(p, q, ar, (ar[i] + ar[j]) / 2, ret);
   }
  System.out.println(ret[0]);

 }
}