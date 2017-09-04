import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BlackAndWhiteTree {

 public Graph g;
 private boolean[] processed;
 private boolean[] discovered;
 private int parent[];
 public List<Integer> components;
 public int[] color;
 public int[] balance;

 public static void main(String[] args) {
  BlackAndWhiteTree b = read();
  b.solve();
 }
 
 public static BlackAndWhiteTree read() {
  Scanner in = new Scanner(System.in);
  int nvertices = in.nextInt();
  BlackAndWhiteTree b = new BlackAndWhiteTree(nvertices);
  int nedges = in.nextInt();
  int x;
  int y;
  for (int i = 0; i != nedges; i++) {
   x = in.nextInt();
   y = in.nextInt();
   b.g.add(x, y);
  }
  in.close();
  return b;
 }
 
 public BlackAndWhiteTree(int nvertices) {
  g = new Graph(nvertices);
  initBFS();
  components = new ArrayList<Integer>();
  color = new int[nvertices+1];
 }
 
 public class Graph {
  
  public int nvertices;
  public int nedges;
  public Edgenode[] edgenodes;
  public boolean isDirected = false;
  
  public Graph(int nvertices) {
   this.nvertices = nvertices;
   edgenodes = new Edgenode[nvertices+1];
  }
  
  public void add(int x, int y) {
   add(x,y,isDirected);
  }
  
  private void add(int x, int y, boolean directed) {
   Edgenode e = new Edgenode(y);
   Edgenode next = edgenodes[x];
   e.next = next;
   edgenodes[x] = e;
   
   if (directed) {
    nedges++;    
   } else {
    add(y,x,true);
   }
  }
  
  @Override
  public String toString() {
   StringBuilder buf = new StringBuilder();
   for (int i = 1; i <= nvertices; i++) {
    buf.append(i + ":");
    Edgenode e = edgenodes[i];
    while (e != null) {
     buf.append(" " + e.y);
     e = e.next;
    }
    buf.append("\n");
   }
   return buf.toString();
  }
 }
 
 class Edgenode {

  public int y;
  public Edgenode next;
  
  public Edgenode(int y) {
   this.y = y;
  }
  
 }
 
 static class BFSProcess {
  public int balance;
  public void before(int x) {};
  public void edge(int x, int y) {};
  public void after(int x) {};  
 }
 
 public void bfs(int start, BFSProcess process) {
  Queue<Integer> q = new LinkedList<Integer>();
  q.add(start);
  discovered[start] = true;
  
  while (!q.isEmpty()) {
   int p = q.remove();
   processed[p] = true;
   process.before(p);
   Edgenode e = g.edgenodes[p];
   while (e != null) {
    if (!discovered[e.y]) {
     q.add(e.y);
     discovered[e.y] = true;     
    }
    if (!processed[e.y]) {
     parent[e.y] = p;
     process.edge(p, e.y);
    }
    e = e.next;
   }
   process.after(p);
  }
 }
 
 public void components() {
  for (int i = 1; i <= g.nvertices; i++) {
   if (!discovered[i]) {
    bfs(i, new BFSProcess());
    components.add(i);
   }
  }
 }
 
 public void color() {
  balance = new int[components.size()];
  for (int i = 0; i != components.size(); i++) {
   int start = components.get(i);
   initBFS();
   
   BFSProcess proc = new BFSProcess() {
    @Override
    public void after(int x) {
     int p = parent[x];
     if (p == 0) {
      color[x] = 1;
     } else {
      color[x] = color[p] == 1 ? -1 : 1;      
      Edgenode e = g.edgenodes[x];
      while (e != null) {
       if (color[e.y] == color[x]) {
        System.err.println("Cannot color tree; nodes have same color: " + x + " " + e.y);
       }
       e = e.next;
      }
     }
     balance += color[x];
    }
   };
   
   bfs(start, proc);
   balance[i] = proc.balance;
  }
 }

 public void solve() {
  components();
  
  if (components.size() < 2) {
   System.out.println("0 0\n");
   return;
  }
  
  color();
  
  class Data implements Comparable<Data> {
   int component;
   int balance;
   @Override
   public int compareTo(Data o) {
    return new Integer(Math.abs(balance)).compareTo(Math.abs(o.balance));
   }
  };
  
  Data[] data = new Data[balance.length];
  for (int i = 0; i != balance.length; i++) {
   Data d = new Data();
   d.component = i;
   d.balance = balance[i];
   data[i] = d;
  }
  Arrays.sort(data);
  
  StringBuilder out = new StringBuilder();
  int nadded = 0;
  int topvertex = components.get(data[data.length-1].component);
  int bal = data[data.length-1].balance;
  for (int i = data.length - 2; i >= 0; i--) {
//   System.out.println("component " + data[i].component + ": " + data[i].balance);
   int b = data[i].balance;
   int vertex = components.get(data[i].component);
   if (bal > 0 && b > 0) {
    out.append(topvertex + " " + vertex + "\n");
   } else {
    int top;
    int child;
    if (g.edgenodes[topvertex] == null) {
     top = vertex;
     child = topvertex;
    } else {
     top = topvertex;
     child = vertex;
    }
    out.append(top + " " + child + "\n");    
   }
   nadded++;
  }
  System.out.println(Math.abs(bal) + " " + nadded + "\n" + out);
 }
 
 private void initBFS() {
  discovered = new boolean[g.nvertices+1];
  processed = new boolean[g.nvertices+1];
  parent = new int[g.nvertices+1];  
 }
 
}