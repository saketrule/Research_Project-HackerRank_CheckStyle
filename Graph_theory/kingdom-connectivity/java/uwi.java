import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Solution {
 static InputStream is;
 static PrintWriter out;
 static String INPUT = "";
 
 static void solve()
 {
  int n = ni(), m = ni();
  int[] from = new int[m];
  int[] to = new int[m];
  for(int i = 0;i < m;i++){
   from[i] = ni()-1;
   to[i] = ni()-1;
  }
  
  int[][] g = packD(n, from, to);
  int[][] ig = packD(n, to, from);
  
  boolean[] gr = new boolean[n];
  {
   Queue<Integer> q = new ArrayDeque<Integer>();
   gr[0] = true;
   q.add(0);
   while(!q.isEmpty()){
    int cur = q.poll();
    for(int e : g[cur]){
     if(!gr[e]){
      gr[e] = true;
      q.add(e);
     }
    }
   }
  }
  boolean[] igr = new boolean[n];
  {
   Queue<Integer> q = new ArrayDeque<Integer>();
   igr[n-1] = true;
   q.add(n-1);
   while(!q.isEmpty()){
    int cur = q.poll();
    for(int e : ig[cur]){
     if(!igr[e]){
      igr[e] = true;
      q.add(e);
     }
    }
   }
  }
  
  if(!gr[n-1]){
   out.println(0);
   return;
  }
  
  int[] nfrom = new int[m];
  int[] nto = new int[m];
  int p = 0;
  for(int i = 0;i < m;i++){
   if(gr[from[i]] && igr[from[i]] && gr[to[i]] && igr[to[i]]){
    nfrom[p] = from[i];
    nto[p] = to[i];
    p++;
   }
  }
  nfrom = Arrays.copyOf(nfrom, p);
  nto = Arrays.copyOf(nto, p);
  int[][] vg = packD(n, nfrom, nto);
  tr(vg);
  
  if(findLoop(vg)){
   out.println("INFINITE PATHS");
   return;
  }
  
  int[] ord = sortTopologically(vg);
  int mod = 1000000000;
  int[] dp = new int[n];
  dp[ord[0]] = 1;
  for(int i = 0;i < n;i++){
   for(int e : vg[ord[i]]){
    dp[e] = (dp[e] + dp[ord[i]]) % mod;
   }
  }
  out.println(dp[n-1]);
 }
 
 public static int[] sortTopologically(int[][] g)
 {
  int n = g.length;
  int[] ec = new int[n];
  for(int i = 0;i < n;i++){
   for(int to : g[i])ec[to]++;
  }
  int[] ret = new int[n];
  int q = 0;
  
  // sources
  for(int i = 0;i < n;i++){
   if(ec[i] == 0)ret[q++] = i;
  }
  
  for(int p = 0;p < q;p++){
   for(int to : g[ret[p]]){
    if(--ec[to] == 0)ret[q++] = to;
   }
  }
  // loop
  for(int i = 0;i < n;i++){
   if(ec[i] > 0)return null;
  }
  return ret;
 }
 
 static boolean findLoop(int[][] g)
 {
  int n = g.length;
  for(int i = 0;i < n;i++){
   Arrays.sort(g[i]);
  }
  for(int i = 0;i < n;i++){
   int[] o = g[i];
   for(int j = 0;j < o.length;j++){
    if(Arrays.binarySearch(g[o[j]], i) >= 0){
     return true;
    }
   }
  }
  
  return findLoop(g, 0, new boolean[n], new int[n], -1);
 }
 
 static boolean findLoop(int[][] g, int cur, boolean[] ved, int[] ea, int prev)
 {
  ved[cur] = true;
  while(ea[cur] < g[cur].length){
   int nex = g[cur][ea[cur]];
   if(nex != prev && ved[nex] || !ved[nex] && findLoop(g, nex, ved, ea, cur))return true;
   ea[cur]++;
  }
  ved[cur] = false;
  return false;
 }
 
 
 public static int[][] packD(int n, int[] from, int[] to)
 {
  int[][] g = new int[n][];
  int[] p = new int[n];
  for(int f : from)p[f]++;
  for(int i = 0;i < n;i++)g[i] = new int[p[i]];
  for(int i = 0;i < from.length;i++){
   g[from[i]][--p[from[i]]] = to[i];
  }
  return g;
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