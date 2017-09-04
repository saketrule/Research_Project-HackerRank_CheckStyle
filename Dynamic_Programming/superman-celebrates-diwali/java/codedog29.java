import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Main {

 static Main.MyScanner sc = new Main.MyScanner();
 static PrintWriter out = new PrintWriter(System.out);

 // static PrintStream out = System.out;

 public static void main(String[] args) {
  int n = sc.nextInt(),h = sc.nextInt(),d = sc.nextInt(),mat[][] = new int[h][n+2],level[] = new int[h];
  for(int i = 1 ; i <= n ; i++){
   for(int j = sc.nextInt() ; j > 0 ; j--){
    mat[h-sc.nextInt()][i]++;
   }
  }
  for(int i = 1 ; i <= n ; i++){
   level[0] = Math.max(level[0], mat[0][i]);
  }
  int ans = level[0];
  for(int i = 1 ; i < h ; i++){
   int max = Integer.MIN_VALUE;
   if(i-d > -1)
    max = level[i-d];
   for(int j = 1 ; j <= n ; j++){
    mat[i][j] += Math.max(max, mat[i-1][j]);
    ans = Math.max(ans, mat[i][j]);
   }
   level[i] = ans;
  }
  out.println(ans);
  
  out.close();
 }
 
 static private class MyScanner {
  BufferedReader br;
  StringTokenizer st;

  public MyScanner() {
   br = new BufferedReader(new InputStreamReader(System.in));
  }

  public int mod(long x) {
   // TODO Auto-generated method stub
   return (int) x % 1000000007;
  }

  public int mod(int x) {
   return x % 1000000007;
  }

  boolean hasNext() {
   if (st.hasMoreElements())
    return true;
   try {
    st = new StringTokenizer(br.readLine());
   } catch (IOException e) {
    e.printStackTrace();
   }
   return st.hasMoreTokens();
  }

  String next() {
   while (st == null || !st.hasMoreElements()) {
    try {
     st = new StringTokenizer(br.readLine());
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
   return st.nextToken();
  }

  int nextInt() {
   return Integer.parseInt(next());
  }

  double nextDouble() {
   return Double.parseDouble(next());
  }

  String nextLine() {
   String str = "";
   try {
    str = br.readLine();
   } catch (IOException e) {
    e.printStackTrace();
   }
   return str;
  }

  public long nextLong() {
   return Long.parseLong(next());
  }
 }

}