import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    int C[] = new int[3401];
 int max = 0;
 int ci[];
 Map<Integer, List<Integer>> edges = new HashMap<>();
 
 public void solve(int[] nodes, int start, int N, int used, int sum) {
  ++C[sum];
  if(used == N) {
   if(sum > max) {
    max = sum;
   }
   return;
  }
  for(int i=start; i <= N; i++) {
   if(nodes[i] < 1) {
    continue;
   }
   --nodes[i];
   ++used;
   for(Integer v : edges.get(i)) {
    if(nodes[v] == 1) {
     ++used;
    }
    --nodes[v];
   }
   solve(nodes, i+1, N, used, sum + ci[i]);
   for(Integer v : edges.get(i)) {
    ++nodes[v];
    if(nodes[v] == 1) {
     --used;
    }
   }
   ++nodes[i];
   --used;
  }
 }
 
 public static void main(String[] args) {
  /*
  3 2
  6 8 2
  1 2
  3 2
   */
  
  Scanner in = new Scanner(System.in);
  Solution dm = new Solution();
  int N = in.nextInt(), M = in.nextInt();
  dm.ci = new int[N+1];
  for(int i=1; i <= N; i++) {
   dm.ci[i] = in.nextInt();
   dm.edges.put(i, new ArrayList<Integer>());
  }
  for(int i=0; i < M; i++) {
   int A = in.nextInt(), B = in.nextInt();
   dm.edges.get(A).add(B);
   dm.edges.get(B).add(A);
  }
  
  int use[] = new int[N+1];
  int numZeroes = 0, numIsolated = 0;
  for(Map.Entry<Integer, List<Integer>> entry: dm.edges.entrySet()) {
   if(entry.getValue().isEmpty()) {
    ++numIsolated;
    dm.max += dm.ci[entry.getKey()];
    use[entry.getKey()] = 0;
    if(dm.ci[entry.getKey()] == 0) {
     ++numZeroes;
    }
   }
   else {
    use[entry.getKey()] = 1;
   }
  }
  
  --dm.C[dm.max];
  dm.solve(use, 1, N, numIsolated, dm.max);
  long ways = Math.max(1, dm.C[dm.max]);
  ways *= Math.pow(2, numZeroes);
  System.out.println(dm.max + " " + ways);
  in.close();
 }
}