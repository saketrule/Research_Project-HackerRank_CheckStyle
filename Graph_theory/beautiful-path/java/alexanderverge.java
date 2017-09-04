import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solve();
    }
    
    public void solve() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Node[] graph = new Node[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Node(i);
        }

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            // subtract 1 to make it zero based indexing
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            int c = sc.nextInt();
            Edge edge = new Edge(u, v, c);
            graph[u].edges.add(edge);
            graph[v].edges.add(edge);
        }

        // subtract 1 to make it zero based indexing
        int a = sc.nextInt() - 1;
        int b = sc.nextInt() - 1;
            
        System.out.println(this.shortestPathBetweenNodes(graph, a, b));
    }
    
    public int shortestPathBetweenNodes(Node[] graph, int a, int b) {
        PriorityQueue<Node> q = new PriorityQueue<Node>(10, new NodeComparator());
        graph[a].distanceFromStart = 0;
        q.add(graph[a]);
        
        while (!q.isEmpty()) {
            int d = this.visit(q, graph, q.remove(), b);            
            if (d > 0) {
                return d;
            }
        }
        
        return -1;
    }
    
    private int visit (PriorityQueue<Node> q, Node[] graph, Node node, int b) {
        if (node.index == b) {
            return node.distanceFromStart;
        }

        for (Edge e : node.edges) {
            if (!e.visited) {
                if (graph[e.u].distanceFromStart > -1) {
                    this.traverseEdge(q, graph[e.v], graph[e.u].distanceFromStart | e.c, e);
                }
                if (graph[e.v].distanceFromStart > -1) {
                    this.traverseEdge(q, graph[e.u], graph[e.v].distanceFromStart | e.c, e);
                }
                e.visited = true;
            }
        }

        return -1;
    }
    
    private void traverseEdge(PriorityQueue<Node> q, Node n, int newDistance, Edge e) {
        if (!(n.distanceFromStart > -1 && n.distanceFromStart < newDistance)) {
            if (n.distanceFromStart > newDistance) {
                q.remove(n);
            }                        
            n.distanceFromStart = newDistance;
            q.add(n);
        }
    }
    
    private class Edge {
        public int u;
        public int v;
        public int c;
        public boolean visited = false;
        public Edge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
    
    private class Node {
        public ArrayList<Edge> edges;
        int distanceFromStart;
        int index;
        public Node(int index) {
            this.index = index;
            this.edges = new ArrayList<Edge>();
            this.distanceFromStart = -1;
        }
    }
    
    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.distanceFromStart, o2.distanceFromStart);
        }
    }
}