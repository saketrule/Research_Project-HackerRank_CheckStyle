import java.util.*;
import java.io.*;
import static java.lang.Math.*;

public class Solution {
 static class State {
  int k;
  int line;
  int point;
  public int hashCode() {
   int res = k;
   res = res * 31 + line;
   res = res *31 + point;
   return res;
  }
  public boolean equals(Object obj) {
   State s = (State)obj;
   return k == s.k && line == s.line && point == s.point;
  }
  public State copy() {
   State curr = new State();
   curr.k = k;
   curr.line = line;
   curr.point = point;
   return curr;
  }
 }
 
 static class Foo33 {
  static final int MOD = 1000000007;
  
  void main() {
   BufferedReader br = null;
   try {
    br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());    
    for (int i = 0; i < T; i++) {
     int N = Integer.parseInt(br.readLine());
     int[] x = new int[N];
     int[] y = new int[N];
     for (int j = 0; j < N; j++) {
      String[] s = br.readLine().split("\\s");
      x[j] = Integer.parseInt(s[0]);
      y[j] = Integer.parseInt(s[1]);
     }
     long[] res = foo(x, y);
     System.out.println(res[0] + " " + res[1]);
    }
   } catch (Exception e) {
    e.printStackTrace();
   } finally {
    try {
     br.close();
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  }
  
  long[] foo(int[] x, int[] y) {
   int N = x.length;
   int[][] arr = new int[N][2];
   for (int i = 0; i < N; i++) {
    arr[i][0] = x[i];
    arr[i][1] = y[i];
   }
   Arrays.sort(arr, new Comparator<int[]>() {
    public int compare(int[] a, int[] b) {
     if (a[0] == b[0])
      return a[1] - b[1];
     return a[0] - b[0];
    }
   });
   for (int i = 0; i < N; i++) {
    x[i] = arr[i][0];
    y[i] = arr[i][1];
   }
   boolean[][] pInLine = new boolean[N][1<<14]; // line with more than 2 points will not exceed 14 for 16 points
   int[][] pXpInLine = new int[N][1<<N];
   int[][] lineXpoint = new int[14][];
   int M = getLongLines(x, y, pInLine, pXpInLine, lineXpoint);
   long[] res = new long[2];
   // find min K
   int K = (N+1)/2;
   for (int i = 1; i < (1<<M); i++) {
    int point = 0;
    int count = 0;
    int s = Integer.bitCount(i);
    if (s > K) continue;
    for (int j = 0; j < M; j++) {
     if ((i&1<<j) != 0) {
      for (int val : lineXpoint[j]) {
       if ((point & 1<<val) == 0) {
        point |= 1<<val;
        count++;
       }
      }
     }
    }
    K = min(K, s + (N-count+1)/2);
   }
   res[0] = K;
   // find ways
   HashMap<State, Integer> map = new HashMap<State, Integer>();
   Queue<State> queue = new ArrayDeque<State>();
   State s = new State();
   queue.add(s);
   map.put(s, 1);
   for (int i = 0; i < N; i++) {
    HashMap<State, Integer> nextMap = new HashMap<State, Integer>();
    Queue<State> nextQueue = new ArrayDeque<State>();
    while (!queue.isEmpty()) {
     s = queue.remove();
     int c = map.get(s);
     int v = s.line;
     while (v > 0) {
      int curr = v;
      v &= v-1;
      curr -= v;
      // point belongs to current line?
      if (pInLine[i][curr]) {
       // choose add it to the line
       State state = s.copy();
       update(nextMap, nextQueue, state, c);
      }
     }
     v = s.point;
     while (v > 0) {
      int curr = v;
      v &= v-1;
      curr -= v;
      // 2 points belong to a line having at least 3 points?
      if (pXpInLine[i][curr] >= 0) {
       int index = pXpInLine[i][curr];
       // if this line already in s.line no need to do it
       if ((s.line & 1<<index) == 0) {
        State state = s.copy();
        state.line |= 1<<index;
        state.point ^= curr;
        update(nextMap, nextQueue, state, c);
       }
      } else {
       // a normal line with only 2 points
       State state = s.copy();
       state.point ^= curr;
       update(nextMap, nextQueue, state, c);
      }
     }
     if (s.k < K) {
      State state = s.copy();
      state.k++;
      state.point |= 1<<i;
      nextMap.put(state, c);
      nextQueue.add(state);
     }
    }
    queue = nextQueue;
    map = nextMap;
   }
   while (!queue.isEmpty()) {
    s = queue.remove();
    res[1] += map.get(s);
   }
   for (int i = 1; i <= K; i++) {
    res[1] = res[1]*i%MOD;
   }
   return res;
  }
  void update(HashMap<State, Integer> nextMap, Queue<State> nextQueue, State state, int c) {
   Integer num = nextMap.get(state);
   if (num == null) {
    nextMap.put(state, c);
    nextQueue.add(state);
   } else {
    nextMap.put(state, c+num);
   }
  }
  int getLongLines(int[] x, int[] y, boolean[][] pInLine, int[][] pXpInLine, int[][] lineXpoint) {
   int N = x.length;
   for (int[] val : pXpInLine)
    Arrays.fill(val, -1);
   int index = 0;
   for (int i = 0; i < N; i++) {
    for (int j = i+1; j < N; j++) {
     if (pXpInLine[i][1<<j] >= 0)
      continue;
     boolean isLine = false;
     int k = j+1;
     for (; k < N; k++) {
      if (oneLine(x, y, i, j, k)) {
       isLine = true;
       break;
      }
     }
     if (isLine) {
      int[] nodes = new int[N];
      int nc = 0;
      nodes[nc++] = i;
      nodes[nc++] = j;
      nodes[nc++] = k;
      for (k++; k < N; k++) {
       if (oneLine(x, y, i, j, k)) 
        nodes[nc++] = k;
      }
      lineXpoint[index] = Arrays.copyOf(nodes, nc);
      for (int a = 0; a < nc; a++) {
       pInLine[nodes[a]][1<<index] = true;
       for (int b = 0; b < nc; b++) {
        if (a == b) continue;
        pXpInLine[nodes[a]][1<<nodes[b]] = index;
       }
      }
      index++;
     }
     
    }
   }
   return index;
  }
  
  boolean oneLine(int[] x, int[] y, int i, int j, int k) {
   return (x[j]-x[i])*(y[k]-y[j]) == (x[k]-x[j])*(y[j]-y[i]);
  }
 }
 
 public static void main(String[] args) {
  Foo33 foo = new Foo33();
  foo.main();
 }
}