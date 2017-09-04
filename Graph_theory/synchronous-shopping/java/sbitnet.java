import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        String parts[] = s.split(" ");
        int N = Integer.valueOf(parts[0]);
        int M = Integer.valueOf(parts[1]);
        int K = Integer.valueOf(parts[2]);
        List<Node> lst = new ArrayList<Node>();
        for(int i=0;i<N;i++) {
            String s1 = scan.nextLine();
            String parts1[] = s1.split(" ");
            lst.add(new Node(Integer.valueOf(parts1[0])));
            for(int j=1;j<parts1.length;j++) {
                lst.get(i).bitOR(Integer.valueOf(parts1[j]));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<K;i++) {
            sb.append("1");
        }
        int nK = Integer.parseInt(sb.toString(), 2);
        Graph g = new Graph(N, nK);
        for(int i=0;i<M;i++) {
            String s1 = scan.nextLine();
            String parts1[] = s1.split(" ");
            g.addEdge(Integer.valueOf(parts1[0]), Integer.valueOf(parts1[1]), Long.valueOf(parts1[2]));
        }
        System.out.println(g.find(nK, lst));
    }
    
    static final class Graph {        
        List<LinkedList<Edge>> vertices = new ArrayList<LinkedList<Edge>>();
        long[][] dist = null;
        Graph(int n, int k) {
            dist = new long[n][k+1];
            for(int i=0;i<n;i++) {
                vertices.add(new LinkedList<Edge>());
                for(int j=0;j<=k;j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        void addEdge(int u, int v, long t) {
            vertices.get(u-1).add(new Edge(v-1, t));
            vertices.get(v-1).add(new Edge(u-1, t));
        }
        
        long find(int k, List<Node> lst) {   
            traverse(lst);
            long cost = Integer.MAX_VALUE;
            int n = vertices.size()-1;
            for(int i=0;i<=(k);i++) {
                for(int j=i;j<=(k);j++) {
                    if( (i|j) == (k)) { // check mask value adds up to k
                        cost = Math.min(cost, Math.max(dist[n][i], dist[n][j]));
                    }   
                }
            }
            return cost;
        }
        
        void traverse(List<Node> lst) {
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
            pq.add(new Pair(0, 0, lst.get(0).mask)); // distance to itself is 0           
            dist[0][lst.get(0).mask] = 0; // distance to itself is 0
            while(!pq.isEmpty()) {// traverse all paths from 0...vertices
                Pair pe = pq.poll();                
                for(Edge eg : vertices.get(pe.node)) {
                    if(dist[pe.node][pe.mask] + eg.t < dist[eg.node][pe.mask | lst.get(eg.node).mask] ) { 
                        // check shortest path, collect types/mask from parent
                        dist[eg.node][pe.mask | lst.get(eg.node).mask] = dist[pe.node][pe.mask] + eg.t;                        
                        Pair p = new Pair(eg.node, dist[eg.node][pe.mask | lst.get(eg.node).mask], 
                                        pe.mask | lst.get(eg.node).mask);
                        pq.add(p);
                    }
                }
            }            
        }        
    }
    
    static final class Node {
        int k;
        int mask;
        Node(int k) {
            this.k = k;
        }
        void bitOR(int type) {
            mask = mask | (1 << (type - 1));  // left shift 1, to place it at type position
        }        
    }
    
    static final class Pair implements Comparable<Pair> {
        int node;
        long time;
        int mask;
        Pair(int node, long time, int mask) {
            this.node = node;
            this.time = time;
            this.mask = mask;
        }
        public int compareTo(Pair o) {
            return Long.valueOf(time).compareTo(Long.valueOf(o.time));
        }
    }
    
    static final class Edge {
        int node;
        long t;
        Edge(int node, long t) {
            this.node = node;
            this.t = t;
        }
    }
}