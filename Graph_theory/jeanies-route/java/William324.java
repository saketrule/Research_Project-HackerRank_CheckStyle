import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 public static long sum = 0;
 public static long max = 0;
 public static int maxi = 0;
 public static long temp = 0;
 public static LinkedList<Integer>[] edge;
 public static Integer[][] di;
 public static int[] mt;
 public static boolean[] visit;
 public static boolean bool = false;
 
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt(), k = in.nextInt();
  mt = new int[k];
  visit = new boolean[n + 1];
  while (k-- > 0) {
   mt[k] = in.nextInt();
  }
  Arrays.sort(mt);
  edge = new LinkedList[n + 1];
  di = new Integer[n + 1][];
  ArrayList<Integer>[] d = new ArrayList[n + 1];
  for (int i = 1; i <= n; i++) {
   edge[i] = new LinkedList<Integer>();
   d[i] = new ArrayList<Integer>();
  }
  while (n-- > 1) {
   int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
   edge[a].add(b);
   edge[b].add(a);
   d[a].add(c);
   d[b].add(c);
  }
  for (int i = 1; i <= n; i++) {
   int j = d[i].size();
   di[i] = new Integer[j];
   for (int l = 0; l < j; l++)
    di[i][l] = d[i].remove(0);
  }
  in.close();
  dfs(mt[0]);
  sum = 0;
  max = 0;
  temp = 0;
  dfs(mt[maxi]);
  System.out.println(2 * sum - max);
 }

 public static void dfs(int t) {
  if (visit[t]) {
  } else {
   for (int i : edge[t]) {
    int d = edge[t].indexOf(i);
    sum += di[t][d];
    temp += di[t][d];
    bool = false;
    dfs(i);
    if (Arrays.binarySearch(mt, i) < 0) {
    } else {
     bool = true;
     if (temp > max) {
      max = temp;
      maxi = i;
     }
    }
    temp -= di[t][d];
    if (!bool)
     sum -= di[t][d];
   }
  }
 }
}