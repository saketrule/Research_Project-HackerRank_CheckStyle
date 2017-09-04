import java.util.*;
import static java.lang.Math.*;
public class Solution {
    long foo12(int[] arr, int K) {
  int N = arr.length;
  long[] dp = new long[N+1];
  TreeMap<Long, Integer> map = new TreeMap<Long, Integer>();
  long[] sum = new long[N+1];
  for (int i = 1; i <= N; i++) {
   sum[i] = sum[i-1] + arr[i-1];
  }
  long base = 0;
  dp[1] = arr[0];
  map.put(0L, 1);
  for (int i = 2; i <= N; i++) {
   int val = arr[i-1];
   dp[i] = dp[i-1];
   // choose
   if (i <= K) {
    dp[i] = sum[i];
   } else {
    long largest = map.lastKey() + base;
    dp[i] = max(dp[i], largest + val);
   }
   if (i-K-1 >= 0) {
    long del = dp[i-K-1] + sum[i-1]-sum[i-K]-base;
    if (map.get(del) > 1) {
     map.put(del, map.get(del)-1);
    } else {
     map.remove(del);
    }
   }
   base += val;
   long add = dp[i-1]-base;
   if (map.containsKey(add)) {
    map.put(add, map.get(add)+1);
   } else {
    map.put(add, 1);
   }
  }
  return dp[N];
 }
    public static void main(String[] args) {
   Solution sol = new Solution();
   Scanner scan = new Scanner(System.in);
   String[] s = scan.nextLine().split("\\s");
   int N = Integer.parseInt(s[0]);
   int K = Integer.parseInt(s[1]);
   int[] arr = new int[N];
   for (int i = 0; i < N; i++) {
    arr[i] = Integer.parseInt(scan.nextLine());
   }
   System.out.println(sol.foo12(arr, K));
   scan.close();
  

 }

}