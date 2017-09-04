import java.util.*;


public class Solution {
 private static final Scanner sc = new Scanner(System.in);
 
 public static void main(String[] args) {
  int T = 1;
  while(T-- != 0) {
   final int N = sc.nextInt();
   final int K = sc.nextInt();
   
   long sum = 0;
   SlideMinimum min = new SlideMinimum(N + 1, K);
   min.shift(0);
   for(int i = 0; i < N; i++) {
    int x = sc.nextInt();
    sum += x;
    min.shift(x + min.min());
   }
   System.out.println(sum - min.min());
  }
 }
 
 
 public static class SlideMinimum {
  private int m, s, t, idx;
  private long minValue;
  private long[] a;
  private int[] deq;
  
  public SlideMinimum(int n, int m) {
   this.m = m;
   
   idx = s = t = 0;
   minValue = Integer.MAX_VALUE;
   a = new long[n];
   deq = new int[n];
  }
  
  // min [idx-m..idx)
  public long min() {
   return minValue;
  }
  
  public void shift(long val) {
   a[idx] = val;
   while(s < t && a[deq[t-1]] >= a[idx]) t--;
   deq[t++] = idx;
   minValue = a[deq[s]];
   if(idx - m >= 0) {
    if(deq[s] == idx - m) {
     s++;
    }
   }
   idx++;
  }
 }


}