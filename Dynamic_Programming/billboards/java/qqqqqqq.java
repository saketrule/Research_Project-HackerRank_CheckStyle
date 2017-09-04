import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class Solution {
 static InputStream is;
 static PrintWriter out;
 static String INPUT = "";
 
 static void solve()
 {
  int n = ni(), k = ni();
  int[] a = new int[n];
  for(int i = 0;i < n;i++)a[i] = ni();
  
  StarrySkyTree sst = new StarrySkyTree(n+1);
  // 1 0 0 0 0 0 0 [0]
  // 3 2 1 0 0 0 0 [1]
  // 3 5 4 3 0 0 0 [2]
  // 3 5 8 7 5 0 0 [3]
//  sst.addRange(0, 0, a[0]);
  for(int i = 0;i < n;i++){
   sst.addRange(i+1, i+1, sst.max(Math.max(i-k, 0), i));
//   for(int j = 0;j < n;j++){
//    tr(i, j, sst.max(j, j));
//   }
//   sst.addRange(i-1, i-1, a[i]);
   sst.addRange(Math.max(i-k+1,0), i, a[i]);
  }
//  for(int j = 0;j <= n;j++){
//   tr(j, sst.max(j, j));
//  }
  out.println(sst.max(Math.max(n-k, 0), n));
  
  // dp[i][k]
  // dp[i][j] = dp[i-j][0] + a[i]+a[i-1]+...+a[i-j]
  // dp[i][j] = dp[i-1][j-1] + a[i]
  // dp[i][0] = max(dp[i-1][0],dp[i-2][0]+a[i-1],..,dp[i-1-k][0]+a[i-1]+a[i-2]+..+a[i-k-1])
 }
 
 public static void main(String[] args) throws Exception
 {
  long S = System.currentTimeMillis();
  is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
  out = new PrintWriter(System.out);
  
  solve();
  out.flush();
  long G = System.currentTimeMillis();
  tr(G-S+"ms");
 }
 
 static boolean eof()
 {
  try {
   is.mark(1000);
   int b;
   while((b = is.read()) != -1 && !(b >= 33 && b <= 126));
   is.reset();
   return b == -1;
  } catch (IOException e) {
   return true;
  }
 }
 
 public static class StarrySkyTree {
  public int M, H, N, B;
  public long[] st;
  public long[] plus;
  
  public StarrySkyTree(int n)
  {
   N = n;
   M = Integer.highestOneBit(Math.max(n-1, 1))<<2;
   H = M>>>1;
   B = Integer.numberOfTrailingZeros(H);
   st = new long[M];
   plus = new long[H];
  }
  
  private void propagate(int i)
  {
   st[i] = Math.max(st[2*i], st[2*i+1]) + plus[i];
  }
  
  public void addRange(int f, int t, long v){ if(f <= t)addRange(f, t, v, B); }
  
  public void addRange(int f, int t, long v, int b)
  {
   if(b == 0){
    st[H+f] += v;
   }else if(f>>b<<b==f && t+1>>b<<b==t+1){
    plus[(H|f)>>b] += v;
    propagate((H|f)>>b);
   }else{
    b--;
    if((f^t)<<31-b<0){
     int w = t>>b<<b;
     addRange(f, w-1, v, b);
     addRange(w, t, v, b);
    }else{
     addRange(f, t, v, b);
    }
    propagate((H|f)>>b+1);
   }
  }
  
  public long max(int f, int t) { return f <= t ? max(f, t, B) : 0; }
  
  public long max(int f, int t, int b)
  {
   if(f>>b<<b==f && t+1>>b<<b==t+1){
    return st[(H|f)>>b];
   }
   
   b--;
   if((f^t)<<31-b<0){
    int w = t>>b<<b;
    return Math.max(max(f, w-1, b), max(w, t, b)) + plus[(H|f)>>b+1];
   }else{
    return max(f, t, b) + plus[(H|f)>>b+1];
   }
  }
 }
  
 static int ni()
 {
  try {
   int num = 0;
   boolean minus = false;
   while((num = is.read()) != -1 && !((num >= '0' && num <= '9') || num == '-'));
   if(num == '-'){
    num = 0;
    minus = true;
   }else{
    num -= '0';
   }
   
   while(true){
    int b = is.read();
    if(b >= '0' && b <= '9'){
     num = num * 10 + (b - '0');
    }else{
     return minus ? -num : num;
    }
   }
  } catch (IOException e) {
  }
  return -1;
 }
 
 static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}