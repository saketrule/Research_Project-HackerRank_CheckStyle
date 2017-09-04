import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static class Edge implements Comparable<Edge> {
        private final int node;
        private final int len;
        Edge(int node, int len) {
            this.node = node;
            this.len = len;
        }
        @Override
        public int compareTo(Edge o1) {
            return Integer.compare(len, o1.len);
        }
    }

    public static class Graph {
        private final int size;
        private final Map<Integer, Map<Integer, TreeSet<Integer>>> graph;

        public Graph(int size) {
            this.size = size;
            this.graph = new HashMap<Integer, Map<Integer, TreeSet<Integer>>>(size);
        }

        public void addEdge(int first, int second, int len) {
            if (!graph.containsKey(first)) {
                graph.put(first, new HashMap<Integer, TreeSet<Integer>>());
            }
            if (!graph.containsKey(second)) {
                graph.put(second, new HashMap<Integer, TreeSet<Integer>>());
            }
            if (first == second) {
                return;
            }
            // multiple edges to same node reduced to a set
            // NB. this is need for the OR-based path-cast
            Map<Integer, TreeSet<Integer>> map = graph.get(first);
            if (!map.containsKey(second))
                map.put(second, new TreeSet<Integer>());
            map.get(second).add(len);
            map = graph.get(second);
            if (!map.containsKey(first))
                map.put(first, new TreeSet<Integer>());
            map.get(first).add(len);
        }

        public int shortestReach(int startId, int endId, int bottleneck) { // 0 indexed
            int len[] = new int[size];
            BitSet visited = new BitSet(size);
            for (int idx = 0; idx < size; ++idx) {
                len[idx] = Integer.MAX_VALUE;
            }
            PriorityQueue<Edge> nodes = new PriorityQueue<>();
            nodes.add(new Edge(startId, 0));

            /**
             * This works in spite of cycles since all edges have positive weight.
             */
            while (!nodes.isEmpty()) {
                Edge parentEdge = nodes.poll();
                int parent = parentEdge.node;
                if (parentEdge.len < len[parent]) {
                    len[parent] = parentEdge.len;
                    Map<Integer, TreeSet<Integer>> children = graph.get(parent);
                    if (children != null) {
                        for (Integer child : children.keySet()) {
                            if (!visited.get(child)) {
                                for (int childLen : children.get(child)) {
                                    if ((len[child] > (len[parent] | childLen))) {
                                        if ((len[parent] | childLen | bottleneck) == bottleneck)
                                            nodes.offer(new Edge(child, (len[parent] | childLen)));
                                    }                            
                                }                                
                            }
                        }
                    }                    
                }
                visited.set(parent);
            }
            return len[endId] == Integer.MAX_VALUE ? -1 : len[endId];
        }
    }

    public static void main(String[] args) {
        // NB. for large inputs, Scanner might be too slow. Use FastIO
        Scanner scanner = new Scanner(System.in);
        // create a graph of size n
        Graph graph = new Graph(scanner.nextInt());
        int m = scanner.nextInt();

        // read and set edges, nodes are now 0-indexed
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            int r = scanner.nextInt();

            // add each edge to the graph
            graph.addEdge(x, y, r);
        }

        // Find shortest reach from node s to node t
        int startId = scanner.nextInt() - 1;
        int endId = scanner.nextInt() - 1;
        
        // find an upper bound
        int pathLen = graph.shortestReach(startId, endId, 1023);
        // TODO use binary search to find a path of max length
        if (pathLen != -1) {
            for (int st = 1; st < pathLen; ++st) {
                int newLen = graph.shortestReach(startId, endId, st);
                if (newLen != -1) {
                    pathLen = newLen;
                    break;
                }
            }            
        }
        System.out.println(pathLen);
        scanner.close();
    }
}