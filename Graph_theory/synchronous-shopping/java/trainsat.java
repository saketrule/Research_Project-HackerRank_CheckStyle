import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c = in.nextInt(); 
        int r = in.nextInt();
        int t = in.nextInt();

        EWG g = new EWG(c, t);
        List<Integer>[] fishInCities = (ArrayList<Integer>[]) new ArrayList[t];
        for (int i = 0; i < t; i++) {
            fishInCities[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < c; i++) {
            int ti = in.nextInt();
            
            for (int j = 0; j < ti; j++) {
                int fish = in.nextInt()-1;
                g.addFish(i, fish);
                fishInCities[fish].add(i);                
            }
        }

        for (int i = 0; i < r; i++) {
            int v1 = in.nextInt()-1;
            int v2 = in.nextInt()-1;
            int weight = in.nextInt();
            g.addEdge(new Edge(v1, v2, weight));
            g.addEdge(new Edge(v2, v1, weight));
        }
        
        Dijkstra d1 = new Dijkstra(g, 0);
        Dijkstra dn = new Dijkstra(g, c-1);
        
        
        if((d1.fish(c-1)).cardinality() == t) {
            System.out.println(d1.distTo(c-1));
            return;
        } else if ((d1.fish(c-1)).cardinality() == t - 1) {
            int fishToBuy = (d1.fish(c-1)).nextClearBit(0);
            
            int minPath = Integer.MAX_VALUE;
            for (Integer city : fishInCities[fishToBuy]) {
                int dist = d1.distTo(city) + dn.distTo(city);
                if (dist < minPath) {
                    minPath = dist;
                }
            }
            
            System.out.println(Math.max(d1.distTo(c-1), minPath));
            return;
        } else {       
            int maxPath = -1;
            int maxCity = -1;

            for (int i = 0; i < t; i++) {
                int minPath = Integer.MAX_VALUE;
                int mCity = -1;
                for (Integer city : fishInCities[i]) {
                    int dist = d1.distTo(city) + dn.distTo(city);
                    if (dist < minPath) {
                        minPath = dist;
                        mCity = city;
                    }
                }
                if (minPath > maxPath) {
                    maxPath = minPath;
                    maxCity = mCity;
                }
            }

            BitSet bought = (BitSet)d1.fish(maxCity).clone();
            bought.or(dn.fish(maxCity));

            int fishToBuy = bought.nextClearBit(0);
            int minPath = Integer.MAX_VALUE;
            for (Integer city : fishInCities[fishToBuy]) {
                BitSet fishHere = (BitSet)d1.fish(city).clone();
                fishHere.or(dn.fish(city));
                fishHere.or(bought);
                if (fishHere.cardinality() == t) {
                    int dist = d1.distTo(city) + dn.distTo(city);
                    if (dist < minPath) {
                        minPath = dist;
                    }
                }
            }
            System.out.println(Math.max(minPath, maxPath));
        }
    }
}

class Dijkstra {
    private Edge[] edgeTo;
    private int[] distTo;
    private BitSet[] fish;
    private PriorityQueue<Pair> pq;
    public Dijkstra (EWG G, int s) {
        edgeTo = new Edge[G.V()];
        distTo = new int[G.V()];
        fish = new BitSet[G.V()];
        Comparator<Pair> comp = new DistComp();
        pq = new PriorityQueue<Pair>(G.V(), comp);
            
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Integer.MAX_VALUE;
        }
        distTo[s] = 0;
        fish[s] = G.getFish(s);
        
        pq.add(new Pair(s, 0));
        while (!pq.isEmpty()) {
            int v = pq.poll().city;
            for (Edge e : G.adj(v)) {
                relax(e, G);
            }   
        }
    }
    public int distTo(int v) {
        return distTo[v];
    }
    public BitSet fish(int v) {
        return fish[v];
    }
    public Iterable<Edge> pathTo(int v) {
        Stack<Edge> path = new Stack<Edge>();
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path; 
    }
    private void relax(Edge e, EWG G) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            fish[w] = (BitSet)G.getFish(w).clone();
            fish[w].or(fish[v]);
            Pair p = new Pair(w, distTo[w]);
            if (pq.contains(p)) {
                pq.remove(p);
            } 
            pq.add(p);
        }
    }
}
class Pair {
    public int city, dist;
    public Pair(int city, int dist) {
        this.city = city;
        this.dist = dist;
    }
    public boolean equals(Object that) {
        if ( this == that ) return true;
        if ( !(that instanceof Pair) ) return false;
        Pair p = (Pair)that;
        return this.city == p.city;
    }
}
class DistComp implements Comparator<Pair> {
    public int compare(Pair p1, Pair p2) {
        return p1.dist - p2.dist;
    }
}
class EWG {
    private final int V;
    private final List<Edge>[] adj;
    private final BitSet[] fish;
    public EWG(int V, int t) {
        this.V = V;
        adj = (ArrayList<Edge>[]) new ArrayList[V];
        fish = new BitSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Edge>();
            fish[i] = new BitSet(t);
        }
    } 
    public void addFish(int c, int t) {
        fish[c].set(t);
    }
    public BitSet getFish(int c) {
        return fish[c];
    }
    public void addEdge(Edge e) {
        int v = e.from();
        adj[v].add(e);
    }  
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
    public int V() {
        return V;
    }
}
class Edge {
    private final int from, to, weight;
    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    public int from() {
        return from;
    }
    public int to() {
        return to;
    }
    public int weight() {
        return weight;
    }
}