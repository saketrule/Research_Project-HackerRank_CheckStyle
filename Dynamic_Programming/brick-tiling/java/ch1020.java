import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution {
    static int NS;
    static int MS;
    static int mod = 1000000007;
    static long[][][] dp;
 
 static int[][] block = {{1,1,3}, {7,1,0}, {3,2,2},{4,7,0},{2,2,3},{7,4,0},{3,1,1},{1,7,0}};
 static int[]blockoff = {0,0,0,2,1,0,0,0};
 
 static int depth;
 static int froms1;
 static int froms2;
 
 //fill the line
 static void dfs(int column, int s[]) {
 /*
  System.out.print(s[0]);System.out.print(";");
  System.out.print(s[1]);System.out.print(";");
  System.out.print(s[2]);System.out.print(";");
  System.out.print("\n");
*/
  if (column == MS) {
  /*
   System.out.print(s[0]);System.out.print(";");
   System.out.print(s[1]);System.out.print(";");
   System.out.print(s[2]);System.out.print(";");
   System.out.print("\n");
   */
   //System.out.print("+");
   dp[depth+1][s[1]][s[2]] = (dp[depth+1][s[1]][s[2]] + dp[depth][froms1][froms2])%mod;
   return;
  }
  
  if ((s[0] & (1<<column)) != 0) {
   dfs(column+1, s);
  }
  for (int move = 0; move < 8; move++) {
   if (isValid(move,column,s)) {
    int[] ss = new int[3];
    put(move, column, s, ss);
    dfs(column+1, ss);
   }
   else {
    //System.out.print("N");
   }
  }
 }

 static boolean isValid(int move, int column, int[] s) {
  if (column < blockoff[move]) return false;
  int[] a = new int[3];
  for (int i = 0; i <3; i++) {
   a[i] = ((block[move][i])<<column)>>blockoff[move];
   //System.out.print(String.format("move:%d on %d \n", move, s[i]));
   if (((a[i]&s[i]) != 0) || ((a[i]&(1<<MS))!=0)) return false;
  }
  return true;
 }
 
 static void put(int move, int column, int[] s, int[] ss) {
  int[] a = new int[3];
  for (int i = 0; i <3; i++) {
   a[i] = ((block[move][i])<<column)>>blockoff[move];
   ss[i] = a[i] | s[i];
  }
 }
 
    public static void main(String[] args) {
  dp = new long[24][1<<8][1<<8];
  int[] map = new int[24];
 
        Scanner in = new Scanner(System.in);
        int TN = in.nextInt();
        
        for (int ti = 0; ti < TN; ti++) {
            NS = in.nextInt();
            MS = in.nextInt();
   
   for(int i1 = 0; i1 <24;i1++)
   for(int i2 = 0; i2 <1<<MS;i2++)
   for(int i3 = 0; i3 <1<<MS;i3++){
    dp[i1][i2][i3] = 0;
   }
   for(int i1 = 0; i1 <24;i1++){
    map[i1] = 0;
   }
   
   for (int i = 1; i <= NS; i++) {
    String str = in.next();
    char[] ca = str.toCharArray(); 
    for (int ci = 0; ci < MS; ci++) {
     if (ca[ci] == '#')
      map[i]=map[i]|(1<<ci);
    }
   }
   
   if (NS == 1) {
    if (map[1] == 1<<MS -1) {
     System.out.println(1);
    }
    else {
     System.out.println(0);
    }
   }
   /*
   System.out.print("map:");
   System.out.print(map[1]);System.out.print(";");
   System.out.print(map[2]);System.out.print("\n");
   */
   dp[2][map[1]][map[2]] = 1;
   
   for (int row = 2; row <= NS; row++)
   {
    depth = row;
    for (int d1 = 0; d1 < 1<<MS; d1++)
     for (int d2 = 0; d2 < 1<<MS; d2++) {
      if (dp[row][d1][d2] == 0) continue;
      froms1 = d1; froms2 = d2;
      int[] ss = new int[3];
      ss[0] = d1; ss[1] = d2; ss[2] = map[row+1];
      dfs(0, ss);
    }
   }
   
   System.out.println(dp[NS+1][(1<<MS)-1][0]);
  }
    }
}