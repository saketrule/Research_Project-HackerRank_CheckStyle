import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.BitSet;

public class Solution {
 static InputStream is;
 static PrintWriter out;
 static String INPUT = "";
 static int mod = 1000000007;
 
 static void solve()
 {
  outer:
  for(int T = ni();T >= 1;T--){
   int n = ni();
   int[][] co = new int[n][2];
   for(int i = 0;i < n;i++){
    co[i][0] = ni();
    co[i][1] = ni();
   }
   if(n == 1){
    out.println("1 1");
    continue;
   }
   
   BitSet bs = new BitSet();
   for(int i = 1;i < 1<<n;i++){
    if(Integer.bitCount(i) <= 2){
     bs.set(i);
    }else{
     int k = i&i-1;
     if(bs.get(k)){
      int j = Integer.numberOfTrailingZeros(i&-i);
      int l = Integer.numberOfTrailingZeros(k&-k);
      int m = Integer.numberOfTrailingZeros((k&k-1)&-(k&k-1));
      if((co[l][1]-co[j][1])*(co[m][0]-co[j][0]) == (co[m][1]-co[j][1])*(co[l][0]-co[j][0])){
       bs.set(i);
      }
     }
    }
   }
   int[] valid = new int[bs.cardinality()];
   int p = 0;
   for(int i = bs.nextSetBit(0);i != -1;i = bs.nextSetBit(i+1)){
    valid[p++] = i;
   }
   
   int m = (n+1)/2;
   int[][] fill = new int[m+1][1<<n];
   boolean[][] pre = new boolean[m+1][1<<n];
   fill[0][0] = 1;
   pre[0][0] = true;
   for(int k = 0;k < m;k++){
    for(int j = 0;j < 1<<n;j++){
     if(pre[k][j]){
//      tr(j, valid);
      for(int i : valid){
       if((i&j) == 0){
        fill[k+1][j|i] = (fill[k+1][j|i] + fill[k][j]) % mod;
        pre[k+1][j|i] = true;
       }
      }
     }
    }
//    tr(k+1, fill[k+1]);
    if(pre[k+1][(1<<n)-1]){
     out.println((k+1) + " " + fill[k+1][(1<<n)-1]);
     continue outer;
    }
   }
  }
 }
 
 public static void main(String[] args) throws Exception
 {
  is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
  out = new PrintWriter(System.out);
  
  solve();
  out.flush();
 }
 
 static boolean eof()
 {
  try {
   is.mark(1000);
   int b;
   while((b = is.read()) != -1 && (b == ' ' || b == '\t' || b == '\r' || b == '\n'));
   is.reset();
   if(b == -1){
    return true;
   }else{
    return false;
   }
  } catch (IOException e) {
  }
  return true;
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
    if(b == -1)return minus ? -num : num;
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