import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 static int[][] sums = new int[10][10];
 static {
  for (int i = 0; i < 10; i++) {
   for (int j = 0; j < 10; j++) {
    sums[i][j] = (i + j) % 10;
   }
  }
 }
 
 static class Path {
  int toll;
  int to;
  Path(int to, int toll) {
   this.toll = toll % 10;
   this.to = to;
  }
 }
 
 static class Node {
  int id;
  List<Path> paths = new ArrayList<>();
  boolean onlyEven = true;
  boolean onlyFive = true;
   int[] ts = null;
  Node(int id) {
   this.id = id;
  }
 }
 
 static Node[] nodes;
 
 static List<Integer> collect(int b) {
  List<Integer> result = new ArrayList<>();
  Set<Integer> set = new HashSet<>();
  nodes[b].ts = new int[10];
  result.add(b);
  set.add(b);
  int c = 0;
  while(c < result.size()) {
   Node n = nodes[result.get(c)];
   for (Path p: n.paths) {
    int j = p.to;
    if(!set.contains(j)) {
     nodes[j].ts = new int[10];
     result.add(j);
     set.add(j);
    }
   }
   c++;
  }
  
  return result;
 }
 
 static void solve() {
  long[] dist = new long[10];
  int[] consider = new int[nodes.length];
  for (int i = 0; i < nodes.length; i++) {
   if(consider[i] == 1) continue;
   Node n = nodes[i];
   List<Integer> is = collect(i);
   boolean onlyEven = true;
   boolean onlyFive = true;
   for (int j: is) {
    if(!nodes[j].onlyEven) {
     onlyEven = false;
     break;
    }
   }
   for (int j: is) {
    if(!nodes[j].onlyFive) {
     onlyFive = false;
     break;
    }
   }
//   Map<Integer, int[]> tolls = new HashMap<>();
//   for (int j: is) {
//    tolls.put(j, new int[10]);
//   }
//   tolls.get(n.id)[0] = 1;
   n.ts[0] = 1;
   Set<Integer> queue = new HashSet<>();
   queue.add(n.id);
   boolean any = false;
   while(!queue.isEmpty() && !any) {
    int j = queue.iterator().next();
    queue.remove(j);
    int[] ts = nodes[j].ts;// tolls.get(j);
    for (Path p: nodes[j].paths) {
     int[] ts1 = nodes[p.to].ts; //tolls.get(p.to);
     boolean changed = false;
     for (int d = 0; d < 10; d++) {
      if(ts[d] == 1) {
       int d1 = sums[d][p.toll];//(d + p.toll) % 10;
       if(ts1[d1] == 0) {
        ts1[d1] = 1;
        changed = true;
        if(p.to == i) {
         if((d1 == 1 || d1 == 3 || d1 == 7 || d1 == 9)) {
          any = true;
         } else if(onlyEven && (d1 == 2 || d1 == 4 || d1 == 6 || d1 == 8)) {
          any = true;
         } else if(onlyFive && (d1 == 5)) {
          any = true;
         }
        }
       }
      }
     }
     if(changed) {
      queue.add(p.to);
     }
    }
   }
   if(any) {
    long a = 1l * is.size() * (is.size() - 1);
    int dd = onlyEven ? 2 : onlyFive ? 5 : 1;
    for (int d = 0; d < 10; d += dd) {
     dist[d] += a;
    }
    for (int j: is) {
     consider[j] = 1;
    }
    continue;
   }
   
   for (int j: is) {
    if(j == i) continue;
    int[] ts = nodes[j].ts; //tolls.get(j);
    for (int d = 0; d < 10; d++) {
     dist[d] += ts[d];
    }
   }
  }
  for (int d = 0; d < dist.length; d++) {
   System.out.println(dist[d]);
  }
 }
 
 static void addPath(int x, int y, int r) {
        nodes[x].paths.add(new Path(y, r));
        nodes[y].paths.add(new Path(x, 1000 - r));
        if(r % 2 == 1) {
         nodes[x].onlyEven = false;
         nodes[y].onlyEven = false;
        }
        if(r % 5 != 0) {
         nodes[x].onlyFive = false;
         nodes[y].onlyFive = false;
        }
 }
 
 static void run() {
  Scanner in = new Scanner(System.in);
  int n = in.nextInt();
        int e = in.nextInt();
        nodes = new Node[n];
        for (int i = 0; i < nodes.length; i++) {
         nodes[i] = new Node(i);
        }
        for(int a0 = 0; a0 < e; a0++){
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            int r = in.nextInt();
            addPath(x, y, r);
        }
  solve();
        in.close();
 }
    public static void main(String[] args) {
        run();
    }
}