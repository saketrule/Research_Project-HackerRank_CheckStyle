import java.util.*;
import static java.lang.Math.*;
public class Solution {
    // easy to make a mistake, first scc, then topsort, then linear pass
 static class Foo8 {
  static final int MOD = 1000000000;
  int foo8(int N, TreeMap<Integer, Integer>[] graph) {
   index = new int[N];
   lowIndex = new int[N];
   isInStack = new boolean[N];
   stack = new LinkedList<Integer>();
   comp = new int[N];
   compCount = new int[N];
   topsort = new int[N];
   res = new int[N];
   this.N = N;
   this.graph = graph;
   Arrays.fill(index, -1);
   for (int i = 0; i < N; i++) {
    if (index[i] == -1)
     tarjin(i);
   }
   // generate component graph
   compGraph = new TreeMap[comps];
   for (int i = 0; i < comps; i++) {
    compGraph[i] = new TreeMap<Integer, Integer>();
   }
   for (int u = 0; u < N; u++) {
    for (Map.Entry<Integer, Integer> entry : graph[u].entrySet()) {
     int v = entry.getKey();
     int count = entry.getValue();
     if (comp[u] == comp[v])
      continue; // don't add self-loop
     if (!compGraph[comp[u]].containsKey(comp[v])) {
      compGraph[comp[u]].put(comp[v], 0);
     }
     compGraph[comp[u]].put(comp[v], compGraph[comp[u]].get(comp[v]) + count);
    }
   }
   // topsort compgraph, it's guarantee as it's comp graph
   int[] indegree = new int[comps];
   for (int i = 0; i < comps; i++) {
    for (int v : compGraph[i].keySet()) {
     indegree[v]++;
    }
   }
   Queue<Integer> queue = new LinkedList<Integer>();
   for (int i = 0; i < comps; i++) {
    if (indegree[i] == 0)
     queue.add(i);
   }
   int topsortIndex = 0;
   while (!queue.isEmpty()) {
    int u = queue.remove();
    topsort[topsortIndex++] = u;
    for (int v : compGraph[u].keySet()) {
     indegree[v]--;
     if (indegree[v] == 0)
      queue.add(v);
    }     
   }
   // everything is ready, compute res
   int i = 0;
   for (i = comps-1; i >= 0; i--) {
    int c = topsort[i];
    if (c != end)
     res[c] = 0;
    else {
     if (compCount[c] > 1) {
      // end city is in a component that has multiple vertices
      res[c] = -1; // means INF
     } else {
      res[c] = 1; // 1 way
     }
     break;
    }
   }
   for (i = i-1; i >= 0; i--) {
    // process each comp node
    int c = topsort[i];
    for (Map.Entry<Integer, Integer> entry : compGraph[c].entrySet()) {
     int c2 = entry.getKey();
     int count = entry.getValue();
     if (res[c2] == -1) {
      res[c] = -1;
      break;
     }
     if (res[c2] > 0 && compCount[c] > 1) {
      res[c] = -1;
      break;
     }
     res[c] = (res[c] + (int)((long)res[c2] * count % MOD)) % MOD;
    }
   }
   return res[start];
  }
  
  void tarjin(int u) {
   index[u] = currIndex;
   lowIndex[u] = currIndex;
   currIndex++;
   stack.push(u);
   isInStack[u] = true;
   for (int v : graph[u].keySet()) {
    if (index[v] == -1) {
     tarjin(v);
     lowIndex[u] = min(lowIndex[u], lowIndex[v]);
    } else if (isInStack[v]) {
     lowIndex[u] = min(lowIndex[u], index[v]);
    }
   }
   if (index[u] == lowIndex[u]) {
    int w = 0;
    do {
     w = stack.pop();
     isInStack[w] = false;
     comp[w] = comps;
     compCount[comps]++;
     if (w == 0)
      start = comps;
     else if (w == N-1)
      end = comps;
    } while (u != w);
    comps++;
   }
  }
  int N;
  TreeMap<Integer, Integer>[] graph;
  int currIndex;
  int[] index;
  int[] lowIndex;
  boolean[] isInStack;
  Deque<Integer> stack;
  int comps;
  int[] comp;
  
  int[] compCount;
  TreeMap<Integer, Integer>[] compGraph;
  
  int start;
  int end;
  
  int[] topsort;
  int[] res;
 }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
  int N = scan.nextInt();
  int M = scan.nextInt();
  TreeMap<Integer, Integer>[] graph = new TreeMap[N];
  for (int i = 0; i < N; i++)
   graph[i] = new TreeMap<Integer, Integer>();
  for (int i = 0; i < M; i++) {
   int u = scan.nextInt()-1;
   int v = scan.nextInt()-1;
   if (graph[u].containsKey(v)) {
    graph[u].put(v, graph[u].get(v)+1);
   } else {
    graph[u].put(v, 1);
   }
  }
  Foo8 foo8 = new Foo8();
  int res = foo8.foo8(N, graph);
  if (res >= 0)
   System.out.println(res);
  else
   System.out.println("INFINITE PATHS");
  
    }
}