/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/

import static java.util.Arrays.deepToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
 private static void debug(final Object...objs) {
  System.out.println(deepToString(objs));
 }
 
 Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();

 private boolean dfs(int node, Set<Integer> grey, Set<Integer> black, int des, Map<Integer, Integer> result) {
  if (grey.contains(node)) {
   if (black.contains(node)) {
//    debug("forward edge");
    return false;
   }
//   debug("back edge");
   return true;
  }
//  debug("tree edge");
  grey.add(node);
  boolean cycled = false;
  for (Integer end : graph.get(node)) {
   if (end == des) {
//    debug("reach des");
    long paths = result.get(node);
    paths = (paths + 1) % 1000000000;
    result.put(node, (int)paths);
   }
   cycled |= dfs(end, grey, black, des, result);
   if (cycled && result.get(node) > 0) {
    result.put(node, Integer.MAX_VALUE);
    return true;
   }
   else {
    long paths = result.get(end);
    if (paths == Integer.MAX_VALUE) {
     result.put(node, Integer.MAX_VALUE);
    }
    else {
     paths = (paths + result.get(node)) % 1000000000;
     result.put(node, (int)paths);
    }
   }
  }
  black.add(node);
  return false;
 }
 
 private void solve() throws IOException {
  final int N = nextInt();
  final int M = nextInt();
  Map<Integer, Integer> result = new HashMap<Integer, Integer>();
  for (int i = 0; i < M; i++) {
   int x = nextInt();
   int y = nextInt();
   if (!graph.containsKey(x)) {
    graph.put(x, new ArrayList<Integer>());
    result.put(x, 0);
   }
   if (!graph.containsKey(y)) {
    graph.put(y, new ArrayList<Integer>());
    result.put(y, 0);
   }
   graph.get(x).add(y);
  }
  Set<Integer> grey = new HashSet<Integer>();
  Set<Integer> black = new HashSet<Integer>();
  dfs(1, grey, black, N, result);
//  debug(graph);
//  debug(result);
  if (result.get(1) == Integer.MAX_VALUE)
   pw.println("INFINITE PATHS");
  else
   pw.println(result.get(1));
 }

 private BufferedReader br;
 private PrintWriter pw;
 private StringTokenizer st;

 int nextInt() throws IOException {
  return Integer.parseInt(next());
 }

 String next() throws IOException {
  while (st == null || !st.hasMoreTokens()) {
   st = new StringTokenizer(br.readLine());
  }
  return st.nextToken();
 }

 public static void main(final String[] args) throws IOException {
  new Solution().run();
 }

 private void run() throws IOException {
//  br = new BufferedReader(new FileReader("input00.txt"));
  br = new BufferedReader(new InputStreamReader(System.in));
  pw = new PrintWriter(System.out);
  solve();
  br.close();
  pw.close();
 }
}