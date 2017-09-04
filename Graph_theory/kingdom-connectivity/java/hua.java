import java.util.*;

public class Solution {
 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
  ArrayList<ArrayList<Integer>> graph2 = new ArrayList<ArrayList<Integer>>();
  int n = in.nextInt();
  int m = in.nextInt();
  int MOD = 1000000000;
  for(int i=0;i<n;++i) {
   graph.add(new ArrayList<Integer>());
   graph2.add(new ArrayList<Integer>());
  }
  int[] incoming = new int[n];
  for(int i=0;i<m;++i) {
   int from = in.nextInt()-1;
   int to = in.nextInt()-1;
   graph.get(from).add(to);
   graph2.get(to).add(from);
   incoming[to]++;
  }
  ArrayDeque<Integer> q = new ArrayDeque<Integer>();
  boolean[] visited = new boolean[n];
  boolean[] visited2 = new boolean[n];
  visited[0] = true;
  visited2[n-1] = true;
  q.offer(0);
  int visits = 0;
  int visitedSecondTime = 1;
  while(!q.isEmpty()) {
   int cur = q.poll();
   for(int edge : graph.get(cur)) {
    if (visited[edge]) {
     continue;
    }
    visited[edge] = true;
    q.offer(edge);
   }
  }
  if (!visited[n-1]) {
   System.out.println("0");
   return;
  }
  q.clear();
  q.offer(n-1);
  while(!q.isEmpty()) {
   int cur = q.poll();
   for(int edge : graph2.get(cur)) {
    if (visited2[edge]) {
     continue;
    }
    visited2[edge] = true;
    q.offer(edge);
   }
  }
  // System.err.println(Arrays.toString(visited));
  // System.err.println(Arrays.toString(visited2));
  for(int i=0;i<n;++i) {
   if (!visited[i] || !visited2[i]) {
    for(int to : graph.get(i)) {
     --incoming[to];
    }
    graph.get(i).clear();
   }
   else {
    ++visits;
   }
  }
  visited = new boolean[n];
  int[] dp = new int[n];
  dp[0] = 1;
  visited[0] = true;
  q.offer(0);
  if (incoming[0] > 0) {
   System.out.println("INFINITE PATHS");
   return;
  }
  while(!q.isEmpty()) {
   int cur = q.poll();
   // System.err.println(cur);
   // System.err.println(cur);
   for(int i : graph.get(cur)) {
    if (!visited2[i]) {
     continue;
    }
    dp[i] += dp[cur];
    dp[i] %= MOD;
    --incoming[i];
    if (incoming[i] == 0) {
     // visited[i] = true;
     q.offer(i);
     ++visitedSecondTime;
    }
   }
  }
  if (visits != visitedSecondTime) {
   System.out.println("INFINITE PATHS");
   return;
  }
  System.out.println(dp[n-1]);
 }
}