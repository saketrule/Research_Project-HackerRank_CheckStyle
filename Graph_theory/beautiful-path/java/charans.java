import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    
 
    
static class EdgeWeightedGraph {

    ArrayList<ArrayList<Edge>> adj;
    int noOfVertices;

    public EdgeWeightedGraph(int v) {
        this.adj = new ArrayList<ArrayList<Edge>>(v + 1);
        this.noOfVertices = v+1;
        // Vertices start from 1, ignore the list at index 0
        for (int l = 0; l <= v; l++) {
            adj.add(new ArrayList<Edge>());
        }
    }
    
    private ArrayList<Edge> getAdjEdges(int u){
        return adj.get(u);
    }
    
    private ArrayList<Integer> getAdjVertices(int u){
        ArrayList<Integer> adjVertices = new ArrayList<Integer>();
        for(Edge e : adj.get(u)){
            adjVertices.add(e.otherVertex(u));
        }
        return adjVertices;
    }

    private void addEdge(Edge edge) {
        int u = edge.eitherVertex(), w = edge.otherVertex(u);
        adj.get(u).add(edge);
        adj.get(w).add(edge);
    }

    private void removeEdge(Edge edge) {
        int u = edge.eitherVertex(), w = edge.otherVertex(u);
        adj.get(u).remove(edge);
        adj.get(w).remove(edge);
    }

    public void printGraph() {
        for (int i = 1; i < adj.size(); i++) {
            System.out.print(i + " :");
            for (Edge e : adj.get(i)) {
                System.out.print(e + ",");
            }
            System.out.println();
        }
    }

}

static class Edge implements Comparable<Edge> {

    int u;
    int v;
    int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int compareTo(Edge that) {
        return Integer.compare(this.weight, that.weight);
    }

    public int eitherVertex() {
        return u;
    }

    public int otherVertex(int x) {
        return x == v ? u : v;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "( " + u + " -- " + v + ", " + weight + " )";
    }
}

 static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
   
   static boolean[][] f = new boolean[1111][1111];

  public static void main(String[] args)
    {
        FastReader fs=new FastReader();
      int v = fs.nextInt();
      int e = fs.nextInt();
      EdgeWeightedGraph G = new EdgeWeightedGraph(v);
      for(int i=0; i<e;i++){
          int u = fs.nextInt();
          int w = fs.nextInt();
          int d = fs.nextInt();
          G.addEdge(new Edge(u,w,d));
      }
    // G.printGraph();
      
      int src = fs.nextInt();
      int dest = fs.nextInt();    
     
     BFS bfs = new BFS(G,src);
      
      int ans = -1;
    for (int i = 0; i < 1111; i++) {
        if (f[dest][i]) {
            ans = i;
            break;
        }
    }
      
      System.out.println(ans);
      
    }
    
    static class BFS {
    

    public BFS(EdgeWeightedGraph G, int source) {           
        bfs(G, source);
    }

    public void bfs(EdgeWeightedGraph G, int s) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        queue.add(0);       
        while (!queue.isEmpty()) {
            int v = queue.remove();
            int res_or = queue.remove();            
            f[v][res_or] = true;            
            for (Edge e : G.getAdjEdges(v)) {
                int w = e.otherVertex(v);  
                int result = res_or | e.getWeight();
                if ( !f[w][result]) {
                    queue.add(w);
                    queue.add(result);
                    f[w][result] = true;                    
                }
            }
        }
    }

    

}
}