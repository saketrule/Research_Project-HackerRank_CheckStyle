import java.util.Scanner;

public class Solution {
 private static char[][] tile[] = {{
   {'#', '#', '#'},
   {'#', '.', '.'}
  }, {
   {'#', '#', '#'},
   {'.', '.', '#'}
  }, {
   {'#', '#'},
   {'#', '.'},
   {'#', '.'},
  }, {
   {'#', '.'},
   {'#', '.'},
   {'#', '#'},
  }, {
   {'.', '#'},
   {'.', '#'},
   {'#', '#'},
  }, {
   {'#', '#'},
   {'.', '#'},
   {'.', '#'},
  }, {
   {'#', '.', '.'},
   {'#', '#', '#'}
  }, {
   {'.', '.', '#'},
   {'#', '#', '#'}
  }};
 private static boolean tile(char[][] g, int lr, int lc, char[][] t) {
  int fb = 0;
  while(fb < t[0].length && t[0][fb] == '.')
   fb++;
  lc -= fb;
  if(lc < 0)
   return false;
  int ur = lr + t.length - 1, uc = lc + t[0].length - 1;
  if(ur >= g.length || uc >= g[0].length)
   return false;
  for(int r = 0; r < t.length; r++) {
   for(int c = 0; c < t[r].length; c++) {
    if(t[r][c] == '#' && g[lr + r][lc + c] == '#')
     return false;
   }
  }
  for(int r = 0; r < t.length; r++) {
   for(int c = 0; c < t[r].length; c++) {
    if(t[r][c] == '#')
     g[lr + r][lc + c] = '#';
   }
  }
  return true;
 }
 private static void untile(char[][] g, int lr, int lc, char[][] t) {
  int fb = 0;
  while(fb < t[0].length && t[0][fb] == '.')
   fb++;
  lc -= fb;
  for(int r = 0; r < t.length; r++) {
   for(int c = 0; c < t[r].length; c++) {
    if(t[r][c] == '#')
     g[lr + r][lc + c] = '.';
   }
  }
 }
 private static int numWays(char[][] g, int r, int c) {
  int nr = r, nc = c;
  out: for(; nr < g.length; nr++, nc = 0)
   for(; nc < g[nr].length; nc++)
    if(g[nr][nc] == '.')
     break out;
  if(nr >= g.length)
   return 1;
  int w = 0;
  for(char[][] t : tile) {
   if(tile(g, nr, nc, t)) {
    w += numWays(g, nr, nc);
    untile(g, nr, nc, t);
   }
  }
  return w;
 }
 /**
  * @param args
  */
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int T = in.nextInt();
  for(int t = 0; t < T; t++) {
   int N = in.nextInt(), M = in.nextInt();
   in.nextLine();
   char[][] g = new char[N][M];
   for(int i = 0; i < N; i++)
    g[i] = in.nextLine().toCharArray();
   System.out.println(numWays(g, 0, 0));
  }
 }
}