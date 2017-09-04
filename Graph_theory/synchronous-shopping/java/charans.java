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

  public static void main(String[] args)
    {
        FastReader fs=new FastReader();
      
      int n = fs.nextInt(); // number of shops
      int m = fs.nextInt();
      int k = fs.nextInt(); //types of fsih
      
      Shop[] shops = new Shop[n+1];
      for(int i=1;i <=n; i++){
          shops[i] = new Shop(i);
          int s = fs.nextInt();          
          for(int j=0; j<s; j++){
              shops[i].addFish(fs.nextInt());
          }
      }
      
      EdgeWeightedGraph G = new EdgeWeightedGraph(n);
      
      for(int i=0;i <m; i++){
          int u = fs.nextInt(); 
           int v = fs.nextInt();
          int d = fs.nextInt(); 
          G.addEdge(new Edge(u,v,d));
      }
      
      Path[] paths = new Path[n+1]; 
      paths[0] = new Path(-1,-1,new HashSet<Integer>());  // to remove unnecessary errors
      
//      G.printGraph();
      
      
      boolean[] marked = new boolean[n+1];
      
      Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        marked[1] = true;
        paths[1] = new Path(1,0,shops[1].fish);
        while(!queue.isEmpty()){
            int v = queue.remove();
            for(Edge e : G.getAdjEdges(v)){
                int w = e.otherVertex(v);
                if(!marked[w]){
                    queue.add(w);
                    Set<Integer> fishes = new HashSet<Integer>(paths[v].fish);
                    fishes.addAll(shops[w].fish);
                    paths[w] = new Path(w,paths[v].time + e.getWeight(),fishes);
                    marked[w] = true;
                }
             }        
         }
      
//      for(int x=0; x<n+1; x++){
//          System.out.println(paths[x]);
//     }
      
      
      Arrays.sort(paths);
      
//      System.out.println(" -------------------- ");
      
//      for(int x=0; x<n+1; x++){
//        System.out.println(paths[x]);
//      }
      

      
      int totalTime = Integer.MAX_VALUE; 
      
      for(int x=1; x<n+1; x++){
          if(paths[x].fish.size() == k){
              if( paths[x].time < totalTime){
                  totalTime = paths[x].time + new BFS(G,paths[x].shop,n).distTo(n);
              }
          }
      }
      
      for(int x=1; x<n; x++){
        for(int y=x+1; y<n+1; y++){
          Set<Integer> fishes = new HashSet<Integer>();
          fishes.addAll(paths[x].fish);
          fishes.addAll(paths[y].fish);
          
        //  if(totalTime < paths[x].time) {break;}
          
          if(fishes.size() == k){
              int bigCatTime = paths[x].time + new BFS(G,paths[x].shop,n).distTo(n) ;
              int smallCatTime = paths[y].time + new BFS(G,paths[y].shop,n).distTo(n);
              int newTotalTime = Math.max(bigCatTime,smallCatTime);
              if( newTotalTime < totalTime){
                  totalTime = newTotalTime;
              }
          }            
        }
      }
      
      System.out.println(totalTime);
      
      
      
      
      
  }
    
    static class BFS {

    boolean[] marked;
    int[] distTo;
    int sink;

    public BFS(EdgeWeightedGraph G, int source, int destination) {
        marked = new boolean[G.noOfVertices];
        distTo = new int[G.noOfVertices];
        for(int i=0; i<G.noOfVertices; i++ ){
            distTo[i] = Integer.MAX_VALUE ;
        }        
        sink = destination;
        bfs(G, source);        
    }

    public void bfs(EdgeWeightedGraph G, int s) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        marked[s] = true;
        distTo[s] = 0;
        if(s == sink){ return ;}
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (Edge e : G.getAdjEdges(v)) {
                int w = e.otherVertex(v);
                if (!marked[w]) {
                    queue.add(w);
                    marked[w] = true;
                    distTo[w] = distTo[v] + e.getWeight() ;
                }
                if(w == sink){ return ;}
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }
        
    public int distTo(int v) {
        return distTo[v];
    }

}
}

    


class Path implements Comparable<Path>{
    
    int shop; //final shop where cat stops on the path
    int time; // time taken to reach from start to this shop
    Set<Integer> fish; // all types of diffrent fish cat was able to buy on this path
    
    public Path(int s, int t, Set<Integer> f ){
        this.shop = s;
        this.time = t;
        this.fish = f;
    }
    
    public int compareTo(Path that){
        return Integer.compare(this.time,that.time);
    }
    
    public String toString(){
        return "Shop, Time , Fishes = " + shop + " , " + time + " , { " + fish.toString() + " } ";  
    }
}

class Shop{
    int shopNumber;
    Set<Integer> fish;
    
    public Shop(int s){
        this.shopNumber = s;
        fish = new HashSet<Integer>();
    }
    
    public void addFish(int f){
        fish.add(f);
    }
}