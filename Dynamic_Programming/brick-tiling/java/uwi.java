import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Solution {
 static InputStream is;
 static PrintWriter out;
 static String INPUT = "";
 
 static void rev(int i, int j, int k, int q)
 {
  if(i == 255){
//   tr(j,k);
   met[mp++] = j*256+k;
   return;
  }
  for(int l = 0;l <= 6;l++){
   if((i&3<<l) == 0 && (j&1<<l) == 0 && (k&1<<l) == 0 && q < l*4+1){
    rev(i|3<<l, j|1<<l, k|1<<l, l*4+1);
   }
   if((i&3<<l) == 0 && (j&2<<l) == 0 && (k&2<<l) == 0 && q < l*4+2){
    rev(i|3<<l, j|2<<l, k|2<<l, l*4+2);
   }
   if((i&1<<l) == 0 && (j&1<<l) == 0 && (k&3<<l) == 0 && q < l*4+3){
    rev(i|1<<l, j|1<<l, k|3<<l, l*4+3);
   }
   if((i&2<<l) == 0 && (j&2<<l) == 0 && (k&3<<l) == 0 && q < l*4+4){
    rev(i|2<<l, j|2<<l, k|3<<l, l*4+4);
   }
  }
  for(int l = 0;l <= 5;l++){
   if((i&7<<l) == 0 && (j&1<<l) == 0 && q < 28+l*4+1){
    rev(i|7<<l, j|1<<l, k, 28+l*4+1);
   }
   if((i&7<<l) == 0 && (j&4<<l) == 0 && q < 28+l*4+2){
    rev(i|7<<l, j|4<<l, k, 28+l*4+2);
   }
   if((i&1<<l) == 0 && (j&7<<l) == 0 && q < 28+l*4+3){
    rev(i|1<<l, j|7<<l, k, 28+l*4+3);
   }
   if((i&4<<l) == 0 && (j&7<<l) == 0 && q < 28+l*4+4){
    rev(i|4<<l, j|7<<l, k, 28+l*4+4);
   }
  }
 }
 
 static int[] met;
 static int mp = 0;
 
 static void solve()
 {
  met = new int[300];
  int[][][][] ct = new int[256][256][][];
  for(int i = 0;i < 256;i++){
   for(int j = 0;j < 256;j++){
    mp = 0;
    rev(i, j, 0, 0);
    Arrays.sort(met, 0, mp);
    int[][] cct = new int[256][];
    int cp = 0;
    int x = 0;
    for(int k = 0;k <= mp-1;k++){
     if(k == mp-1 || met[k] != met[k+1]){
      cct[cp++] = new int[]{met[k], k-x+1};
      x = k+1;
     }
    }
    ct[i][j] = Arrays.copyOf(cct, cp);
   }
  }
  
  for(int T = ni();T >= 1;T--){
   int n = ni(), m = ni();
   char[][] map = new char[n][];
   for(int i = 0;i < n;i++){
    map[i] = ns(m);
   }
   int space = 0;
   for(int i = 0;i < n;i++){
    for(int j = 0;j < m;j++){
     if(map[i][j] == '.'){
      space++;
     }
    }
   }
   if(space % 4 != 0){
    out.println(0);
   }else{
    int[] M = new int[n+1];
    for(int i = 0;i < n;i++){
     for(int j = 0;j < m;j++){
      if(map[i][j] == '#')M[i] |= 1<<j;
     }
    }
    M[n] = 255;
    
    int mod = 1000000007;
    int F = (-1<<m)&255;
    long[][] dp = new long[2][1<<16];
    int v = (F|M[0])<<8|(F|M[1]);
    dp[1][v] = 1;
    int cur = 1, nex = 0;
    for(int i = 1;i < n;i++, cur^=1, nex^=1){
     Arrays.fill(dp[nex], 0);
     for(int j = 0;j < 1<<16;j++){
      if(dp[cur][j] > 0){
       dp[cur][j] %= mod;
       for(int[] z : ct[j>>>8][j&255]){
        int low = z[0]&255;
        if(((F|M[i+1])&low) == 0){
         dp[nex][z[0]|F|M[i+1]] += dp[cur][j] * z[1];
        }
       }
      }
     }
    }
    out.println(dp[cur][(1<<16)-1] % mod);
   }
  }
 }
 
 static int[][][] B = {
  {{0, 0}, {1, 0}, {0, 1}, {0, 2}},
  {{0, 0}, {1, 0}, {1, 1}, {1, 2}},
  {{0, 0}, {1, 2}, {0, 1}, {0, 2}},
  {{0, 0}, {1, 0}, {2, 0}, {0, 1}},
  {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
  {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
  {{0, 0}, {1, 0}, {1, -1}, {1, -2}},
  {{0, 0}, {0, 1}, {1, 1}, {2, 1}}
 };
 
 static int rec(char[][] map, int r, int c)
 {
  int n = map.length, m = map[0].length;
  if(r >= n){
//   tr(map);
   return 1;
  }
  if(c >= m)return rec(map, r+1, 0);
  if(map[r][c] != '.')return rec(map, r, c+1);
  
  int ret = 0;
  outer:
  for(int[][] b : B){
   for(int[] e : b){
    if(r+e[0] >= 0 && r+e[0] < n && c+e[1] >= 0 && c+e[1] < m && map[r+e[0]][c+e[1]] == '.'){
    }else{
     continue outer;
    }
   }
   Random gen = new Random();
   char C = (char)(gen.nextInt(26)+'A');
   for(int[] e : b){
    map[r+e[0]][c+e[1]] = C;
   }
   ret += rec(map, r, c+1);
   for(int[] e : b){
    map[r+e[0]][c+e[1]] = '.';
   }
  }
  
  return ret;
 }
 
 public static char[] ns(int n) {
  char[] buf = new char[n];
  try {
   int b = 0, p = 0;
   while ((b = is.read()) != -1
     && (b == ' ' || b == '\r' || b == '\n'))
    ;
   if (b == -1)
    return null;
   buf[p++] = (char) b;
   while (p < n) {
    b = is.read();
    if (b == -1 || b == ' ' || b == '\r' || b == '\n')
     break;
    buf[p++] = (char) b;
   }
   return Arrays.copyOf(buf, p);
  } catch (IOException e) {
  }
  return null;
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