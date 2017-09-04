import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner s= new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int a,b,c;
        BigInteger ans=BigInteger.ZERO;
        Graph G = new Graph(n);
        for(int i= 0;i<m;i++) {
            a = s.nextInt();
            b = s.nextInt();
            c = s.nextInt();
            G.addEdge(a-1,b-1,BigInteger.ONE.shiftLeft(c));
        }
        for(int i = 0;i<n;i++) {
          DijkstraSP dsp = new DijkstraSP(G,i);
          ans = ans.add(dsp.total(i));
        }
        
        System.out.println(ans.toString(2));
    }
     static class Edge {
            int v,w;
            BigInteger weight;
            public Edge(int v,int w,BigInteger weight) {
                this.v = v;
                this.w = w;
                this.weight = weight;
            }
            public BigInteger weight() {
                return this.weight;
            }
            public int from() {
                return this.v;
            }
            public int to() {
                return this.w;
            }
        }
    static class Graph {
        ArrayList<Edge> ar[];
        int N;
        public Graph(int n) {
            ar = new ArrayList[n];
            for(int i = 0;i<n;i++) {
                ar[i] = new ArrayList<Edge>();
            }
            N = n;
        }
        public void addEdge(int v,int w,BigInteger weight) {
                ar[v].add(new Edge(v,w,weight));
                ar[w].add(new Edge(w,v,weight));
        }
        public Iterable<Edge> adj(int v) {
                return ar[v];
        }
        public int V() {
            return N;
        }
    }
    static public class DijkstraSP {
    private BigInteger[] distTo;
    private IndexMinPQ pq;    
    private BigInteger total = BigInteger.ZERO;
    public DijkstraSP(Graph G, int s) {
        distTo = new BigInteger[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = BigInteger.valueOf(Long.MAX_VALUE);
        }
        distTo[s] = BigInteger.ZERO;
        pq = new IndexMinPQ(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(Edge e) {
        int v = e.from(), w = e.to();
        if (distTo[w].compareTo(distTo[v].add(e.weight()))==1) {
            distTo[w] = distTo[v].add(e.weight());
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    public BigInteger total(int d) {
       for(int i = d;i<distTo.length;i++) {
           total = total.add(distTo[i]);
       }
       return total;
    }
}
    static public class IndexMinPQ {
    private int maxN;      
    private int N;           
    private int[] pq;       
    private int[] qp;      
    private BigInteger[] keys;      


    public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        keys = new BigInteger[maxN + 1];   
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];                   
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }


    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }
    public void insert(int i, BigInteger key) {
        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }
    public int delMin() {
        int min = pq[1];
        exch(1, N--);
        sink(1);
        qp[min] = -1;
        keys[min] = null; 
        pq[N+1] = -1;        
        return min;
    }
    public void decreaseKey(int i, BigInteger key) {
        keys[i] = key;
        swim(qp[i]);
    }

    public void increaseKey(int i, BigInteger key) {
        keys[i] = key;
        sink(qp[i]);
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
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

}
}