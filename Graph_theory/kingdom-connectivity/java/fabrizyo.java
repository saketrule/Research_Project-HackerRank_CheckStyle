import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class Solution {

 private static final long MOD = 1000000000;
 private static int N;
 private static int M;
 private static HashMap<Integer, List<Integer>> roads;
 private static long[] memo;

 public static void main(String[] args) throws IOException {
  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("a.txt")));
  String[] split = reader.readLine().split(" ");
  N = Integer.parseInt(split[0]);
  M = Integer.parseInt(split[1]);
  roads = new HashMap<Integer, List<Integer>>();
  for (int i = 0; i < M; i++) {
   split = reader.readLine().split(" ");
   int from = Integer.parseInt(split[0]) - 1;
   int to = Integer.parseInt(split[1]) - 1;
   List<Integer> tos = roads.get(from);
   if (tos == null) {
    tos = new ArrayList<Integer>();
    roads.put(from, tos);
   }
   tos.add(to);
  }
  int paths = countPaths();
  System.out.println(paths == -1 ? "INFINITE PATHS" : paths);
 }

 private static int countPaths() {
  memo = new long[N];
  boolean[] done = new boolean[N];
  Arrays.fill(memo, -1);
  boolean[] visited = new boolean[N];
  Deque<Call> calls = new ArrayDeque<Call>();
  calls.add(new Call(0));
  boolean[] cycle = new boolean[N];
  next: while (!calls.isEmpty()) {
   Call call = calls.peek();
   List<Integer> tos = roads.get(call.from);
   visited[call.from] = true;
   for (; tos != null && call.indexDone < tos.size(); ++call.indexDone) {
    int to = tos.get(call.indexDone);
    if (visited[to]) {
     cycle[to] = true;
     continue;
    }

    if (done[to]) {
     continue;
    }
    calls.push(new Call(to));
    continue next;
   }
   if (call.from == N - 1) {
    memo[call.from] = 1;
   } else {
    memo[call.from] = 0;
    for (int i = 0; tos != null && i < tos.size(); ++i) {
     int to = tos.get(i);
     if (!done[to]) {
      // cycle
      continue;
     }
     memo[call.from] += memo[to];
     memo[call.from] %= MOD;
    }
   }
   // cycle with path to N-1
   if (cycle[call.from] && memo[call.from] != 0) {
    return -1;
   }
   calls.pop();
   visited[call.from] = false;
   done[call.from] = true;
  }
  return (int) memo[0];
 }

 static class Call {
  int from;
  int indexDone;

  public Call(int from) {
   this.from = from;
  }

 }

}