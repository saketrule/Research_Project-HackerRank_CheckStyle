import java.util.*;

public class Solution {
 public static final long MOD = 1000000007;
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int t = in.nextInt();
  while (t-->0) {
   int n = in.nextInt();
   int[] counts = new int[n];
   for (int i = 0; i < n; ++i)
    counts[i] = in.nextInt();
   long ret = 1;
   for (int i = 0; i < n; ++i) {
    ret *= modPow(counts[i], counts[i] - 2, MOD);
    ret %= MOD;
   }
   if (n > 1) {
    long sum = 0;
    for (int i = 0; i < n; ++i) {
     ret *= counts[i];
     ret %= MOD;
     sum += counts[i];
    }
    ret *= modPow(sum, n-2, MOD);
   }
   ret %= MOD;
   System.out.println(ret);
  }
 }
 
 public static long modPow(long base, long exp, long mod) {
  long ret = 1;
  long mult = base;
  long bit = 1;
  while (bit <= exp) {
   if ( (bit & exp) != 0) {
    ret *= mult;
    ret %= mod;
   }
   bit <<= 1;
   mult *= mult;
   mult %= mod;
  }
  return ret;
 }
}