import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.Iterator;

class Solution {
  public static void main(String[] args) throws IOException {
    Scanner in = new Scanner(new InputStreamReader(System.in));
    int n = in.nextInt();
    int m = in.nextInt();
    Graph G = new Graph(n);
    for (int edge = 0; edge<m; edge++) {
      int start = in.nextInt()-1;
      int end   = in.nextInt()-1;
      G.edges_out[start].add(end);
      G.edges_in[end].add(start);
    }

    boolean[] reach_from_start = G.dfs(0,G.edges_out);
    if (!reach_from_start[n-1]) {
      System.out.println("0");
      return;
    }
    boolean[] reach_to_end = G.dfs(n-1,G.edges_in);
    int[] label = G.tarjan();
    for (int v=0; v<n; v++) {
      if (reach_to_end[v] && reach_from_start[v] && G.size_of_component[label[v]]>1) {
        System.out.println("INFINITE PATHS");
        return;
      }
    }

    for (int v=0; v<G.n; v++) {
      if (G.size_of_component[label[v]]>1) {
        G.edges_out[v] = new ArrayList();
        G.edges_in [v] = new ArrayList();
        continue;
      }
      int end;
      for (Iterator<Integer> it = G.edges_out[v].iterator(); it.hasNext();) {
        end = it.next();
        if (G.size_of_component[label[end]]>1) it.remove();
      }
      for (Iterator<Integer> it = G.edges_in[v].iterator(); it.hasNext();) {
        end = it.next();
        if (G.size_of_component[label[end]]>1) it.remove();
      }
    }
    
    int[] num_paths = new int[n];
    num_paths[0] = 1;
    ArrayList<Integer> order = G.toposort();
    for (int v: order)
      for (int neighbor: G.edges_out[v])
        num_paths[neighbor] = (num_paths[neighbor] + num_paths[v])%1000000000;
    System.out.println(num_paths[n-1]);
  }
}

class Graph {
  int n;
  ArrayList<Integer>[] edges_out;
  ArrayList<Integer>[] edges_in;
  
  Stack<Integer> S;
  boolean[] instack;
  int[] lowlink;
  int[] index;
  int[] label;
  int[] size_of_component;
  int curr_label;
  int overall_index;


  public Graph(int nodes) {
    this.n = nodes;
    this.edges_out = new ArrayList[n];
    this.edges_in  = new ArrayList[n];
    for (int i=0; i<n; i++) {
      edges_out[i] = new ArrayList();
      edges_in[i]  = new ArrayList();
    }
  }

  public boolean[] dfs(int start, ArrayList<Integer>[] edgeset) {
    boolean[] found = new boolean[n];
    Stack<Integer> explore = new Stack();
    explore.push(start);
    while (!explore.isEmpty()) {
      int current = explore.pop();
      found[current] = true;
      for (Integer neighbor: edgeset[current]) {
        if (!found[neighbor])
          explore.push(neighbor);
      }
    }
    return found;
  }

  public ArrayList<Integer> toposort() {
    ArrayList<Integer> sorted = new ArrayList();
    Queue<Integer> todo = new LinkedList();
    int[] num_edges_in = new int[n];
    for (int v=0; v<n; v++) {
      num_edges_in[v] = edges_in[v].size();
      if (num_edges_in[v] == 0)
        todo.offer(v);
    }
    while (!todo.isEmpty()) {
      Integer current = todo.remove();
      sorted.add(current);
      for (Integer neighbor: edges_out[current]) {
        num_edges_in[neighbor]--;
        if (num_edges_in[neighbor]==0) {
          todo.offer(neighbor);
        }
      }
    }
    return sorted;
  }

  public int[] tarjan() {
    S = new Stack();
    overall_index = 1;
    curr_label = 0;
    instack = new boolean[n];
    lowlink = new int[n];
    index   = new int[n];
    label   = new int[n];
    size_of_component = new int[n];

    for (int i=0; i<n; i++)
      if (index[i]==0)
        strongconnect(i);
    return label;
  }

  public void strongconnect(int v) {
    index[v]   = overall_index;
    lowlink[v] = overall_index;
    overall_index++;
    S.push(v);
    instack[v] = true;

    for (Integer neighbor: edges_out[v]) {
      if (index[neighbor]==0) {
        strongconnect(neighbor);
        lowlink[v] = Math.min(lowlink[v],lowlink[neighbor]);
      }
      else if (instack[neighbor])
        lowlink[v] = Math.min(lowlink[v],index[neighbor]);
    }

    if (lowlink[v] == index[v]) {
      int next;
      int num_elts = 0;
      do {
        next = S.pop();
        instack[next] = false;
        label[next] = curr_label;
        num_elts++;
      } while (next!=v);
      size_of_component[curr_label] = num_elts;
      curr_label++;
    }
  }
}