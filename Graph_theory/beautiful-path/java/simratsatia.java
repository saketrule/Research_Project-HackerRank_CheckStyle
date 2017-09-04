import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution2 {

 public static void main(String[] args) {
  // TODO Auto-generated method stub
  FastReader in = new FastReader();
  int n = in.nextInt();
  int m = in.nextInt();
  ArrayList<pair>[] vertices = new ArrayList[n + 1];
  HashMap<Integer, Integer>[]costfs=new HashMap[n+1];
  int[][] numberofedges = new int[n + 1][n + 1];
  for (int i = 0; i < m; i++) {
   int a = in.nextInt();
   int b = in.nextInt();
   int c = in.nextInt();
   numberofedges[a][b]++;
   numberofedges[b][a]++;

   if (vertices[a] == null) {
    vertices[a] = new ArrayList<>();
    vertices[a].add(new pair(b, c));
   } else {
    vertices[a].add(new pair(b, c));
   }

   if (vertices[b] == null) {
    vertices[b] = new ArrayList<>();
    vertices[b].add(new pair(a, c));
   } else {
    vertices[b].add(new pair(a, c));
   }
  }

  int source = in.nextInt();
  int dest = in.nextInt();
  Queue<Integer> queue = new LinkedList<>();
  queue.offer(source);
  costfs[source] = new HashMap<>();
  costfs[source].put(0, 0);
  while (!queue.isEmpty()) {
   int test = queue.poll();
   HashMap<Integer,Integer> costfp = costfs[test];
   ArrayList<pair> neighbours = vertices[test];
   if (neighbours != null) {
    for (pair val : neighbours) {
     int name = val.vtx2;
     int cost = val.weight;
     if (costfs[name] == null) {
      costfs[name] = new HashMap<>();
     }
     if(name==test)
      continue;
     Set<Integer>costfpkeys=costfp.keySet();
     for (Integer costofparent : costfpkeys) {
      costfs[name].put(cost | costofparent,0);
     }

     // Do the visited condition
     if (name == dest)
      continue;
     Integer number = numberofedges[test][name];
     if (number > 0) {
      queue.offer(name);
      numberofedges[test][name]--;
     }

    }
   }

  }

  HashMap<Integer,Integer> answer = costfs[dest];
  Set<Integer>keys=answer.keySet();
  
  int min = Integer.MAX_VALUE;
  for (Integer val : keys) {
   if (val < min)
    min = val;
   }

  System.out.println(min);

 }

 public static class pair {
  int vtx2;
  int weight;

  pair(int vtx2, int w) {
   this.vtx2 = vtx2;
   this.weight = w;
  }

 }

 public static class FastReader {
  BufferedReader br;
  StringTokenizer st;

  public FastReader() {
   br = new BufferedReader(new InputStreamReader(System.in));
  }

  String next() {
   while (st == null || !st.hasMoreElements()) {
    try {
     st = new StringTokenizer(br.readLine());
    } catch (IOException e) {
     e.printStackTrace();
    }
   }
   return st.nextToken();
  }

  int nextInt() {
   return Integer.parseInt(next());
  }

  long nextLong() {
   return Long.parseLong(next());
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
 }
}