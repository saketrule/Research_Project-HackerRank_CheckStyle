import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Solution2 {
 Scanner in = new Scanner(System.in);
 EdgeWeightGraph2 G;
 int sol[] = new int[10];
 public static void main(String[] args) {
  new Solution2().solve();
 }

 void solve() {

  int n = in.nextInt();
  int e = in.nextInt();
  G = new EdgeWeightGraph2(n);
  for(int a0 = 0; a0 < e; a0++){
   int x = in.nextInt();
   int y = in.nextInt();
   int r = in.nextInt();
   DirectedEdge2 de = new DirectedEdge2(x-1,y-1,r);
   G.addEdge(de);
   de = new DirectedEdge2(y-1,x-1,1000-r);
   G.addEdge(de);
  }
  
  for (int i = 0; i < (n-1); i++) {
   for (int j= i+1; j < n; j++ ){
    DFS2 dfs = new DFS2(G,i,j);
    ArrayList<ArrayList<Integer>> allPaths = dfs.getAllPaths();
    boolean tempSol[] = new boolean[10];
    boolean tempSolI[] = new boolean[10];
    int cost;
    
    for (ArrayList<Integer> al1: allPaths) {
     
     cost = calculateCost(al1);
     int restCost = cost % 10;
     tempSol[restCost]=true;
     cost = ( 1000 * (al1.size() - 1) ) - cost;
     restCost = cost % 10;
     tempSolI[restCost]=true;
    }
    for (int k = 0; k < 10; k++) {
     if (tempSol[k]) {
      sol[k]++;
     }
     if (tempSolI[k]) {
      sol[k]++;
     }
    }
    
   }
  }
  for (int k = 0; k < 10; k++) {
   System.out.println(sol[k]);
  }
 }

 private int calculateCost(ArrayList<Integer> al) {
  
  int from = al.get(0);
  int to;
  int cost=0;
  for (int i=1; i < al.size(); i++) {
   to = al.get(i);
   cost += G.calculateCost(from,to);  
   from = to;
  }
  return cost;
  
 }
}

class DFS2 {
 EdgeWeightGraph2 G;
 boolean visited[];
 int[] pathTo;
 int pathIndex;
 ArrayList<ArrayList<Integer>> allPaths;
 DFS2 (EdgeWeightGraph2 g,int source,int end) {
  G = g;
  visited = new boolean[G.nodes()];
  pathTo = new int[g.nodes()];
  pathIndex =0;
  allPaths = new ArrayList<ArrayList<Integer>>();
  dfs (source,end); 
 }

 ArrayList<ArrayList<Integer>> getAllPaths() {
  return allPaths;
 }
 private void dfs(int s,int end) {
  visited[s]= true;
  pathTo[pathIndex]= s;
  pathIndex++;
  if(s==end) {
   ArrayList<Integer> al = new ArrayList<Integer>();
   for (int i = 0; i<pathIndex; i++)
            al.add(pathTo[i]);
   allPaths.add(al);
  }
  for (DirectedEdge2 de: G.adj(s)) {
   if (!visited[de.target]) {
    dfs(de.target,end);
   }
  }
  pathIndex--;
  visited[s]=false;
 }
}
class DirectedEdge2 {
 int source;
 int target;
 int weight;
 public DirectedEdge2(int v, int w, int weight)
 {
  this.source = v;
  this.target = w;
  this.weight = weight;
 }
 public int weight()
 { return weight; }
 public int from()
 { return source; }
 public int to()
 { return target; }
 public String toString()
 { return String.format("%d->%d %d", source, target, weight);
 }
}
class EdgeWeightGraph2 {
 int nodes;
 int vertices;
 ArrayList<DirectedEdge2>[] adj;

 public EdgeWeightGraph2(int size) {
  nodes = size;

  adj = new ArrayList[nodes];
  for (int i = 0; i < nodes; i++){
   adj[i]= new ArrayList<DirectedEdge2>();
  }
  vertices = 0;
 }
 public int calculateCost(int from, int to) {
  ArrayList<DirectedEdge2> al = adj[from];
  for (DirectedEdge2 de: al) {
   if (de.target == to) {
    return de.weight;
   }
  }
  return 0;
 }
 
 int nodes() {return nodes;}
 int vertices() {return vertices;}

 public void addEdge(DirectedEdge2 de) {
  adj[de.from()].add(de);

 }
 public Iterable<DirectedEdge2> adj(int v)
 { return adj[v]; }
}