import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class FastReader {

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

    static class EdgeWeightedGraph {

        ArrayList<ArrayList<Edge>> adj;
        int noOfVertices;

        public EdgeWeightedGraph(int v) {
            this.adj = new ArrayList<ArrayList<Edge>>(v + 1);
            this.noOfVertices = v + 1;
            // Vertices start from 1, ignore the list at index 0
            for (int l = 0; l <= v; l++) {
                adj.add(new ArrayList<Edge>());
            }
        }

        private ArrayList<Edge> getAdjEdges(int u) {
            return adj.get(u);
        }

        private ArrayList<Integer> getAdjVertices(int u) {
            ArrayList<Integer> adjVertices = new ArrayList<Integer>();
            for (Edge e : adj.get(u)) {
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

        private void removeEdge(int u, int v) {
            for (Edge e : adj.get(u)) {
                if (e.otherVertex(u) == v) {
                    e.weight = 0;
                }
            }
            for (Edge e : adj.get(v)) {
                if (e.otherVertex(v) == u) {
                    e.weight = 0;
                }
            }
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

    static class IndexMinPQ<Key extends Comparable<Key>> {

        private int N;
        private int[] pq, qp;
        private Key[] keys;

        public IndexMinPQ(int cap) {
            pq = new int[cap + 1];
            qp = new int[cap + 1];
            keys = (Key[]) new Comparable[cap + 1];
            for (int i = 0; i < cap + 1; i++) {
                qp[i] = -1;
            }
        }

        public boolean isEmpty() {
            return N == 0;
        }

        public int size() {
            return N;
        }

        public boolean contains(int k) {
            return qp[k] != -1;
        }

        public void insert(int k, Key key) {
            N++;
            qp[k] = N;
            pq[qp[k]] = k;
            keys[k] = key;
            swim(N);
        }

        public void change(int k, Key key) {
            Key current = keys[k];
            if (current.compareTo(key) > 0) {
                keys[k] = key;
                sink(k);
            } else if (current.compareTo(key) < 0) {
                keys[k] = key;
                swim(k);
            }
        }

        public Key min() {
            return keys[pq[1]];
        }

        public int delMin() {
            int indexOfMin = pq[1];
            exch(1, N--);
            sink(1);
            keys[pq[N + 1]] = null;
            qp[pq[N + 1]] = -1;
            return indexOfMin;
        }

        private boolean greater(int i, int j) {
            return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
        }

        private void exch(int i, int j) {
            int swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }

        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                exch(k / 2, k);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && greater(j, j + 1)) {
                    j++;
                }
                if (!greater(k, j)) {
                    break;
                }
                exch(k, j);
                k = j;
            }
        }

    }

    static class SteinerTree {        
        private ArrayList<Edge> ST;
        private PriorityQueue<Edge> pq;        
        private Set<Integer> STvertices;
        private Set<Integer> terminals;

        public SteinerTree(EdgeWeightedGraph G, Set<Integer> terminals) {
            int V = G.noOfVertices;            
            this.terminals = terminals;           
            pq = new PriorityQueue<Edge>();
            ST = new ArrayList<Edge>();
            STvertices = new HashSet<Integer>();
            int src = terminals.iterator().next();
            //System.out.println("src" + src);
            STvertices.add(src);            
            pq.addAll(G.getAdjEdges(src));
            //System.out.println(terminals);
           
            while (!STvertices.containsAll(terminals)) {
               //System.out.println("STvertices " + STvertices + STvertices.containsAll(terminals));
               // System.out.println("pq "+pq);
                Edge e = pq.remove();
                G.removeEdge(e);
               // G.printGraph();
                int v = e.eitherVertex();
                if(STvertices.contains(v)){
                    v = e.otherVertex(v);
                }
               // System.out.println("v : "+v);
                pq.addAll(G.getAdjEdges(v));
                STvertices.add(v);
                ST.add(e);                
            }
            //   System.out.println("STvertices "+STvertices);
        }        

        public ArrayList<Edge> edges() {            
            return ST;
        }
        
        
    }

    public static void main(String[] args) {
        FastReader fs = new FastReader();
        int n = fs.nextInt();
        int k = fs.nextInt();
        Set<Integer> destSet = new HashSet<Integer>();
        for (int i = 0; i < k; i++) {
            destSet.add(fs.nextInt());
        }
        EdgeWeightedGraph G = new EdgeWeightedGraph(n);
        for (int i = 0; i < n - 1; i++) {
            int u = fs.nextInt();
            int w = fs.nextInt();
            int d = fs.nextInt();
            G.addEdge(new Edge(u, w, d));
        }

       // G.printGraph();
        SteinerTree ST = new SteinerTree(G, destSet);

       // System.out.println(ST.edges());

        HashMap<Integer, Integer> mappedVertices = new HashMap<Integer, Integer>();
        int i = 1;
        for (Edge e : ST.edges()) {
            int v = e.eitherVertex();
            int w = e.otherVertex(v);
            if (!mappedVertices.containsKey(v)) {
                mappedVertices.put(v, i);
                i++;
            }
            if (!mappedVertices.containsKey(w)) {
                mappedVertices.put(w, i);
                i++;
            }
        }

        // System.out.println(mappedVertices);
        G = new EdgeWeightedGraph(mappedVertices.size());
        int eulerianTour = 0;
        for (Edge e : ST.edges()) {
            int v = e.eitherVertex();
            int w = e.otherVertex(v);
            int newV = mappedVertices.get(v);
            int newW = mappedVertices.get(w);
            G.addEdge(new Edge(newV, newW, e.getWeight()));
            eulerianTour += e.getWeight();
        }

        eulerianTour = 2 * eulerianTour;

        // G.printGraph();  
        BFS bfs = new BFS(G, ST.edges().get(0).eitherVertex());
        bfs = new BFS(G, bfs.lastFound());
        int diameter = bfs.distTo(bfs.lastFound());
        System.out.println(eulerianTour - diameter);

    }

    static class BFS {

        boolean[] marked;
        int[] distTo;
        int lastFound;

        public BFS(EdgeWeightedGraph G, int source) {
            marked = new boolean[G.noOfVertices];
            distTo = new int[G.noOfVertices];
            for (int i = 0; i < G.noOfVertices; i++) {
                distTo[i] = Integer.MAX_VALUE;
            }
            bfs(G, source);
        }

        public void bfs(EdgeWeightedGraph G, int s) {
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(s);
            marked[s] = true;
            distTo[s] = 0;
            while (!queue.isEmpty()) {
                int v = queue.remove();
                lastFound = v;
                for (Edge e : G.getAdjEdges(v)) {
                    int w = e.otherVertex(v);
                    if (!marked[w]) {
                        queue.add(w);
                        marked[w] = true;
                        distTo[w] = distTo[v] + e.getWeight();
                    }
                }
            }
        }

        public boolean hasPathTo(int v) {
            return marked[v];
        }

        public int distTo(int v) {
            return distTo[v];
        }

        public int lastFound() {
            return lastFound;
        }

    }

}