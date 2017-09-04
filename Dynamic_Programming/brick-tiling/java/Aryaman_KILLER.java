import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Solution {
 static InputStream is;
 static PrintWriter out;
// static String INPUT = "1 2 2 ## ##";
 static String INPUT = "";
 
 static int[] makeMaskArray(int mask, int h, int amask)
 {
  int[] ret = new int[h];
  int p = 0;
  for(int i = 0;i < h;i++){
   if((i&mask) == 0 && (amask == -1 || (i&amask) != 0)){
    ret[p++] = i;
   }
  }
  return Arrays.copyOf(ret, p);
 }
 
 static void solve()
 {
  for(int T = ni();T >= 1;T--){
   int n = ni(), m = ni();
   char[][] map = nm(n,m);
   int[] pre = new int[1<<m*2+1];
   pre[(1<<m*2+1)-1] = 1;
   int[] cur = new int[1<<m*2+1];
   int mod = 1000000007;
   int omask = (1<<m*2+1)-1;
   int hmask = 1<<m*2;
   int mask1 = 1<<m-1|1<<2*m-1|1<<2*m;
   int mask2 = 1<<m-1|1<<2*m-1|1<<2*m-2;
   int mask3 = 1<<m-1|1<<m|1<<m+1;
   int mask4 = 1<<m-1|1<<m-2|1<<m-3;
   int mask5 = 1<<0|1<<m|1<<2*m;
   int mask6 = 1<<0|1<<m-1|1<<2*m-1;
   int mask7 = 1<<0|1<<1|1<<m+1;
   int mask8 = 1<<0|1<<1|1<<m-1;
   int[] val1 = makeMaskArray(mask1, 1<<2*m+1, -1);
   int[] val2 = makeMaskArray(mask2, 1<<2*m+1, hmask);
   int[] val3 = makeMaskArray(mask3, 1<<2*m+1, hmask);
   int[] val4 = makeMaskArray(mask4, 1<<2*m+1, hmask);
   int[] val5 = makeMaskArray(mask5, 1<<2*m+1, -1);
   int[] val6 = makeMaskArray(mask6, 1<<2*m+1, hmask);
   int[] val7 = makeMaskArray(mask7, 1<<2*m+1, hmask);
   int[] val8 = makeMaskArray(mask8, 1<<2*m+1, hmask);
   int[] valn = makeMaskArray(0, 1<<2*m+1, hmask);
   
   for(int i = 0;i < n;i++){
    for(int j = 0;j < m;j++){
     Arrays.fill(cur, 0);
     if(map[i][j] == '.'){
      // xx
      // .x
      // .x
      if(i >= 2 && j >= 1){
       for(int k : val1){
        int nk = ((k|mask1)<<1|1)&omask;
        cur[nk] += pre[k];
        if(cur[nk] >= mod)cur[nk] -= mod;
       }
      }
      // x.
      // x.
      // xx
      if(i >= 2 && j >= 1){
       for(int k : val5){
        int nk = ((k|mask5)<<1|1)&omask;
        cur[nk] += pre[k];
        if(cur[nk] >= mod)cur[nk] -= mod;
       }
      }
      // xx
      // x.
      // x.
      if(i >= 2 && j+1 < m){
       for(int k : val2){
        int nk = ((k|mask2)<<1|1)&omask;
        cur[nk] += pre[k];
        if(cur[nk] >= mod)cur[nk] -= mod;
       }
      }
      // .x
      // .x
      // xx
      if(i >= 2 && j >= 1){
       for(int k : val6){
        int nk = ((k|mask6)<<1|1)&omask;
        cur[nk] += pre[k];
        if(cur[nk] >= mod)cur[nk] -= mod;
       }
      }
      // xxx
      // ..x
      if(i >= 1 && j >= 2){
       for(int k : val3){
        int nk = ((k|mask3)<<1|1)&omask;
        cur[nk] += pre[k];
        if(cur[nk] >= mod)cur[nk] -= mod;
       }
      }
      // x..
      // xxx
      if(i >= 1 && j >= 2){
       for(int k : val7){
        int nk = ((k|mask7)<<1|1)&omask;
        cur[nk] += pre[k];
        if(cur[nk] >= mod)cur[nk] -= mod;
       }
      }
      // xxx
      // x..
      if(i >= 1 && j+2 < m){
       for(int k : val4){
        int nk = ((k|mask4)<<1|1)&omask;
        cur[nk] += pre[k];
        if(cur[nk] >= mod)cur[nk] -= mod;
       }
      }
      // ..x
      // xxx
      if(i >= 1 && j >= 2){
       for(int k : val8){
        int nk = ((k|mask8)<<1|1)&omask;
        cur[nk] += pre[k];
        if(cur[nk] >= mod)cur[nk] -= mod;
       }
      }
      // none
      for(int k : valn){
       int nk = (k<<1)&omask;
       cur[nk] += pre[k];
       if(cur[nk] >= mod)cur[nk] -= mod;
      }
     }else{
      for(int k : valn){
       int nk = (k<<1|1)&omask;
       cur[nk] += pre[k];
       if(cur[nk] >= mod)cur[nk] -= mod;
      }
     }
     int[] dum = cur; cur = pre; pre = dum;
    }
   }
   out.println(pre[(1<<m*2+1)-1]);
  }
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
 
 private static boolean eof()
 {
  if(lenbuf == -1)return true;
  int lptr = ptrbuf;
  while(lptr < lenbuf)if(!isSpaceChar(inbuf[lptr++]))return false;
  
  try {
   is.mark(1000);
   while(true){
    int b = is.read();
    if(b == -1){
     is.reset();
     return true;
    }else if(!isSpaceChar(b)){
     is.reset();
     return false;
    }
   }
  } catch (IOException e) {
   return true;
  }
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
 
 private static boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
 private static int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
 
 private static double nd() { return Double.parseDouble(ns()); }
 private static char nc() { return (char)skip(); }
 
 private static String ns()
 {
  int b = skip();
  StringBuilder sb = new StringBuilder();
  while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
   sb.appendCodePoint(b);
   b = readByte();
  }
  return sb.toString();
 }
 
 private static char[] ns(int n)
 {
  char[] buf = new char[n];
  int b = skip(), p = 0;
  while(p < n && !(isSpaceChar(b))){
   buf[p++] = (char)b;
   b = readByte();
  }
  return n == p ? buf : Arrays.copyOf(buf, p);
 }
 
 private static char[][] nm(int n, int m)
 {
  char[][] map = new char[n][];
  for(int i = 0;i < n;i++)map[i] = ns(m);
  return map;
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
 
 private static long nl()
 {
  long num = 0;
  int b;
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