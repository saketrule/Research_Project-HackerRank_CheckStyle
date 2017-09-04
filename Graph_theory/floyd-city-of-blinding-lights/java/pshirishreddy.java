import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by shirishp on 22/6/15.
 */
public class WeightedGraph {

    public class Edge implements Comparable<Edge>{
        public int v;
        public int w;
        public double weight;

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int either() {
            return v;
        }
        public int other(int vertex) {
            if(vertex == v) {
                return w;
            } else if (vertex == w) {
                return v;
            } else {
                throw new RuntimeException("Inconsistent Edge");
            }
        }


        @Override
        public int compareTo(Edge o) {
            if(this.weight < o.weight) {
                return -1;
            } else if(this.weight > o.weight) {
                return 1;
            } else {
                return 0;
            }
        }
        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + v +
                    ", w=" + w +
                    ", weight=" + weight +
                    '}';
        }

    }
    public int vertices;
    public int edges;
    public ArrayList<Edge>[] adj;

    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        adj = new ArrayList[vertices+1];
        for (int i = 0; i < vertices + 1; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public WeightedGraph(Scanner s, boolean directed) {
        this(s.nextInt()); //take vertices;
        int edges = s.nextInt();
        for (int i = 0; i < edges; i++) {
            Edge edge = new Edge();
            edge.v = s.nextInt();
            edge.w = s.nextInt();
            edge.weight = s.nextDouble();
            addEdge(edge, directed);
        }
    }

    private void addEdge(Edge edge, boolean directed) {
        int v = edge.either(), w = edge.other(v);
        adj[v].add(edge);
        if(!directed) {
            adj[w].add(edge);
        }
        this.edges++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int v = 0; v < vertices; v++) {
            for(Edge e: adj[v]) {
                if(e.other(v) > v) edges.add(e);
            }
        }
        return edges;
    }

    //floyd here
    public double[][] dist;
    public void shortestPaths() {
        dist = new double[vertices+1][];
        for (int i = 0; i < vertices + 1; i++) {
            dist[i] = new double[vertices+1];
            for (int j = 0; j < vertices + 1; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < vertices + 1; i++) {
            dist[i][i]=0;
        }
        for (int i = 1; i < vertices + 1; i++) {
            for (Edge edge : adj[i]) {
                dist[edge.v][edge.w] = edge.weight;
            }
        }
        for (int k = 1; k < vertices + 1; k++) {
            for (int i = 1; i < vertices + 1; i++) {
                for (int j = 1; j < vertices + 1; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        WeightedGraph wg = new WeightedGraph(s, true);
        wg.shortestPaths();
        int q = s.nextInt();
        for (int i = 0; i < q; i++) {
            int v = s.nextInt();
            int w = s.nextInt();
//            System.out.println(v + " " + w);
            if(wg.dist[v][w] == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println((int)wg.dist[v][w]);
            }
        }
    }
}