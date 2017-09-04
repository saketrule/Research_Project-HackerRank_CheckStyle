import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Edge {
    int dst, cst;
    public Edge(int dst, int cost) {
        this.dst = dst;
        this.cst = cost;
    }
}
class Adj implements Iterable<Edge>{
    ArrayList<Edge> adj;
    public Adj(){adj = new ArrayList<Edge>();}
    public void add(Edge edge){adj.add(edge);}
    public void add(int dst, int cost){add(new Edge(dst, cost));}
    public Iterator<Edge> iterator(){return adj.iterator();}
}

class Graph {
    Adj[] g;
    int V, E, OO = 1 << 11;
    public Graph(int V){
        this.V = V;
        this.g = new Adj[V];
        this.E = 0;
    }
    public void addEdge(int src, int dst, int cost) {
        addEdge(src, new Edge(dst, cost));
    }
    public void addEdge(int src, Edge e) {
        if(g[src] == null) 
            g[src] = new Adj();
        g[src].add(e);
    }
    
    public int shortDist(int src, int dst) {
        if(g[src] == null)  return -1;
        if(g[dst] == null)  return -1;
        this.mem = new int[V][OO];
        int cost = find(src, new boolean[V], dst, 0);
        return cost >= OO ? -1 : cost;
    }
    
    private int[][] mem;
    private int find(int cur, boolean[] vist, int dst, int cost) {
        if(cur == dst) return cost;
        if(cost >= OO) return OO;
        if(mem[cur][cost] > 0) 
            return mem[cur][cost];
        int min = OO;
        for(Edge edge: g[cur]) {
            if(!vist[edge.dst]) {
                vist[edge.dst] = true;
                min = Math.min(min, find(edge.dst, vist, dst, cost|edge.cst));
                vist[edge.dst] = false;
            }
        }
        return mem[cur][cost] = min;
    }
}
public class Solution {

    public static int I(String s) {
        return Integer.parseInt(s);
    }
    public static String[] S(Scanner in) {
        return in.nextLine().split("\\s+");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] s = S(in);
        int N = I(s[0]), M = I(s[1]);
        Graph g = new Graph(N);
        for(int i = 0; i < M; i++) {
            s = S(in);
            int src = I(s[0]) - 1;
            int dst = I(s[1]) - 1;
            int cst = I(s[2]);
            g.addEdge(src, dst, cst);
            g.addEdge(dst, src, cst);
        }
        s = S(in);
        int src = I(s[0]) - 1;
        int dst = I(s[1]) - 1;
        System.out.println(g.shortDist(src, dst));
        in.close();
    }
}