import java.util.*;

public class Solution {
 private final int mod = 1000000000;
 public static void main(String[] args) {
  Solution s = new Solution();
  s.solve();
 }
 
 public void solve() {
  Scanner in = new Scanner(System.in);
  int numCities = in.nextInt();
  int numRoads = in.nextInt();
  int[] numWays = new int[numCities+1];
  int[] incoming = new int[numCities+1];
  boolean failsafe = false;
  
  ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
  ArrayList<ArrayList<Integer>> reverse = new ArrayList<ArrayList<Integer>>();
  
  graph.add(null);
  reverse.add(null);
  for(int i = 1; i <= numCities; ++i) {
   graph.add(new ArrayList<Integer>());
   reverse.add(new ArrayList<Integer>());
  }
  
  int start, end;
  for(int i = 0; i < numRoads; ++i) {
   start = in.nextInt();
   end = in.nextInt();
   graph.get(start).add(end);
   ++incoming[end];
   reverse.get(end).add(start);
  }
  
  boolean[] reachable = new boolean[numCities+1];
  boolean[] temp = new boolean[numCities+1];
  
  bfs(graph, temp, 1);
  for(int i = 1; i <= numCities; ++i) {
   reachable[i] |= temp[i];
  }
  Arrays.fill(temp, false);
  bfs(reverse, temp, numCities);
  for(int i = 1; i <= numCities; ++i) {
   reachable[i] &= temp[i];
  }
  //System.out.println(Arrays.toString(reachable));
  //System.out.println(Arrays.toString(incoming));
  for(int i = 1; i <= numCities; ++i) {
   if(!reachable[i]) {
    Iterator<Integer> it = graph.get(i).iterator();
    while(it.hasNext()) {
     --incoming[it.next()];
    }
   }
  }
  //System.out.println(Arrays.toString(incoming));
  numWays[1] = 1;
  if(reachable[numCities]) {
   int count = -1;
   for(int i = 1; i <= numCities; ++i) {
    if(reachable[i]) {
     ++count;
    }
   }
   ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
   stack.offer(1);
   int cur, next;
   while(!stack.isEmpty()) {
    cur = stack.poll();
    Iterator<Integer> it = graph.get(cur).iterator();
    while(it.hasNext()) {
     next = it.next();
     if(reachable[next]) {
      numWays[next] += numWays[cur];
      numWays[next] %= mod;
      --incoming[next];
      if(incoming[next] == 0) {
       stack.offer(next);
       --count;
      }
     }
    }
   }
   if(count != 0) {
    //System.out.printf("Count = %d\n", count);
    //System.out.println("Didn't reach all cities because there was a loop.");
    failsafe = true;
   }
   
  } else {
   //System.out.println("Can't reach the last city.");
   failsafe = true;
  }
  
  
  if(failsafe) {
   System.out.println("INFINITE PATHS");
  } else {
   System.out.println(numWays[numCities]);
  }
 }
 
 public void bfs(ArrayList<ArrayList<Integer>> graph, boolean[] temp, int start) {
  ArrayDeque<Integer> list = new ArrayDeque<Integer>();
  list.offer(start);
  int cur, next;
  while(!list.isEmpty()) {
   cur = list.poll();
   temp[cur] = true;
   ArrayList<Integer> t = graph.get(cur);
   for(int i = 0; i < t.size(); ++i) {
    next = t.get(i);
    if(!temp[next]) {
     temp[next] = true;
     list.offer(next);
    }
   }
  }
 }
}