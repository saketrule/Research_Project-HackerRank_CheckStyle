import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Solution {
 static InputStream is;
 static PrintWriter out;
 static String INPUT = "";
 
 static void dfs(int n, int rd, int res, int[] dim, List<int[]> dims, int num)
 {
  if(res == 0){
   dim[n] = num;
   if(rd == 0)dims.add(Arrays.copyOf(dim, dim.length));
  }else{
   for(int i = 1;i <= rd;i++){
    dim[n-res] = i;
    dfs(n, rd-i, res-1, dim, dims, num);
    num /= i;
   }
  }
 }
 
 static void solve()
 {
  List<List<int[]>> dist = new ArrayList<List<int[]>>();
  dist.add(null);
  dist.add(null);
  for(int i = 2;i <= 10;i++){
   int f = 1;
   for(int j = 1;j <= i-2;j++){
    f *= j;
   }
   List<int[]> dims = new ArrayList<int[]>();
   dfs(i, 2*(i-1), i, new int[i+1], dims, f);
   dist.add(dims);
  }
  
  int mod = 1000000007;
  for(int T = ni();T >= 1;T--){
   int n = ni();
   int[] b = na(n);
   long ret = 1;
   if(n > 1){
    for(int i = 0;i < n;i++){
     ret = ret * pow(b[i], b[i]-2, mod) % mod;
    }
    
    long lsum = 0;
    for(int[] dim : dist.get(n)){
     long lmul = 1;
     for(int i = 0;i < n;i++){
      lmul = lmul * pow(b[i], dim[i], mod) % mod;
     }
     lsum += lmul * dim[n] % mod;
    }
    lsum %= mod;
    ret = ret * lsum % mod;
   }else{
    ret = pow(b[0], b[0]-2, mod);
   }
   out.println(ret);
  }
 }
 
 public static long invl(long a, long mod)
 {
  long b = mod;
  long p = 1, q = 0;
  while(b > 0){
   long c = a / b;
   long d;
   d = a; a = b; b = d % b;
   d = p; p = q; q = d - c * q;
  }
  return p < 0 ? p + mod : p;
 }
 
 public static long pow(long a, long n, long mod)
 {
  long ret = 1;
  int x = 63-Long.numberOfLeadingZeros(n);
  for(;x >= 0;x--){
   ret = ret * ret % mod;
   if(n<<63-x<0)ret = ret * a % mod;
  }
  return ret;
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
 
 private static byte[] inbuf = new byte[1024];
 static int lenbuf = 0, ptrbuf = 0;
 
 private static int readByte()
 {
  if(lenbuf == -1)throw new InputMismatchException();
  if(ptrbuf >= lenbuf){
   ptrbuf = 0;
   try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
   if(lenbuf <= 0)return -1;
  }
  return inbuf[ptrbuf++];
 }
 
 private static int[] na(int n)
 {
  int[] a = new int[n];
  for(int i = 0;i < n;i++)a[i] = ni();
  return a;
 }
 
 private static int ni()
 {
  int num = 0, b;
  boolean minus = false;
  while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
  if(b == '-'){
   minus = true;
   b = readByte();
  }
  
  while(true){
   if(b >= '0' && b <= '9'){
    num = num * 10 + (b - '0');
   }else{
    return minus ? -num : num;
   }
   b = readByte();
  }
 }
 
 private static void tr(Object... o) { if(INPUT.length() != 0)System.out.println(Arrays.deepToString(o)); }
}