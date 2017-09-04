import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Edge {
        private int fromNodeId,toNodeId, distance;
        public Edge(int fromNode, int toNode, int distance) {
            this.fromNodeId = fromNode;
            this.toNodeId = toNode;
            this.distance = distance;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;
            Edge edge = (Edge) o;
            if (distance != edge.distance) return false;
            if (fromNodeId != edge.fromNodeId) return false;
            if (toNodeId != edge.toNodeId) return false;
            return true;
        }
    }
    public static class Graph2 {
        int numNodes, numEdges;
        Map<Integer,Node> nodesById = new HashMap<Integer, Node>();
        List<Edge> edges = new ArrayList<Edge>();

        public Graph2(int numNodes, int numEdges) {
            this.numNodes = numNodes;
            this.numEdges = numEdges;
        }
        private Node getOrCreateNode(int nodeId) {
            Node node = nodesById.get(nodeId);
            if (node==null) {
                node = new Node(nodeId);
                nodesById.put(nodeId, node);
            }
            return node;
        }
        public void addEdge(int fromNodeId, int toNodeId, int dist) {
            Node fromNode = getOrCreateNode(fromNodeId), toNode = getOrCreateNode(toNodeId);
            fromNode.addNeighbor(toNode, dist); toNode.addNeighbor(fromNode, dist);
            edges.add(new Edge(fromNodeId,toNodeId,dist));
        }
        public int[] getDijskraShortestDistancesToSource(int start) {
            return getDijskraShortestDistancesToSource(start, new Operator() {
                @Override
                public int process(int op1, int op2) {
                    return op1+op2;
                }
            });
        }
        public int[] getDijskraShortestDistancesToSource(int start, Operator ope) {
            NodeComparator nodeComparator = new NodeComparator();
            TreeSet<Node> toVisit = new TreeSet<Node>(nodeComparator);
            toVisit.add(getOrCreateNode(start).setDistanceFromSource(null, 0));

            while (!toVisit.isEmpty()) {
                //Collections.sort(toVisit, nodeComparator);
                Node min = toVisit.pollFirst();
                if (min.getIsVisited()) {
                    continue;
                }
                min.setIsVisited(true);
                for (Map.Entry<Node, Integer> neighborEntry : min.getDistancesByNeighbor().entrySet()) {
                    Node neighbor = neighborEntry.getKey();
                    if (neighbor.getIsVisited()) continue;

                    int adjacentDistance = ope.process(min.getDistanceFromSource(), neighborEntry.getValue());
                    if (neighbor.getDistanceFromSource()==-1) {
                        neighbor.setDistanceFromSource(min, adjacentDistance);
                        toVisit.add(neighbor);
                    } else if (neighbor.getDistanceFromSource()>adjacentDistance) {
                        toVisit.remove(neighbor);
                        neighbor.setDistanceFromSource(min, adjacentDistance);
                        toVisit.add(neighbor);
                    }
                }
            }

            int[] distances = new int[numNodes+1];
            distances[start]=0;
            for (int i=1; i<=numNodes; i++) {
                if (i==start) continue;
                Node node = nodesById.get(i);
                int d = node==null?-1:node.getDistanceFromSource();
                distances[i]=d;
            }
            return distances;
        }

        int[] bellmanFord(int src, Operator ope)
        {
            int dist[] = new int[numNodes+1];

            for (int i=0; i<=numNodes; ++i)
                dist[i] = Integer.MAX_VALUE;
            dist[src] = 0;

            // Step 2: Relax all edges |V| - 1 times. A simple
            // shortest path from src to any other vertex can
            // have at-most |V| - 1 edges
            for (int i=1; i<numNodes; ++i)
            {
                for (int j=0; j<numEdges; ++j)
                {
                    int u = edges.get(j).fromNodeId;
                    int v = edges.get(j).toNodeId;
                    int weight = edges.get(j).distance;
                    if (dist[u]!=Integer.MAX_VALUE) {
                        int newDist = ope.process(dist[u],weight);
                        if (newDist<dist[v]) {
                            dist[v]=newDist;
                        }
                    }
                }
            }

            // Step 3: check for negative-weight cycles.  The above
            // step guarantees shortest distances if graph doesn't
            // contain negative weight cycle. If we get a shorter
            //  path, then there is a cycle.
            for (int j=0; j<numEdges; ++j)
            {
                int u = edges.get(j).fromNodeId;
                int v = edges.get(j).toNodeId;
                int weight = edges.get(j).distance;
                if (dist[u]!=Integer.MAX_VALUE) {
                    int newDist = ope.process(dist[u],weight);
                    if (newDist<dist[v]) {
                        System.err.println("Graph contains negative weight cycle");
                    }
                }
            }
            return dist;
        }
    }
    public static interface Operator {
        public int process(int op1, int op2);
    }
    private static int nextInt(StreamTokenizer st) throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
    public static void main(String[] args) throws IOException {
        InputStream in = new BufferedInputStream(System.in);        
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(in)));
        int numNodes=nextInt(st), numEdges=nextInt(st);
        Graph2 graph = new Graph2(numNodes,numEdges);
        for (int i=0;i<numEdges;i++) {
            graph.addEdge(nextInt(st), nextInt(st), nextInt(st));
        }
        int source=nextInt(st), dest=nextInt(st);
        int[] distances = graph.getDijskraShortestDistancesToSource(source, new Operator() {
            @Override
            public int process(int op1, int op2) {
                return op1|op2;
            }
        });
        System.out.print(distances[dest]);
    }
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            int diff = o1.getDistanceFromSource()-o2.getDistanceFromSource();
            if (diff!=0) return diff;
            return o1.id-o2.id;
        }
    }
    public static class Node {
        int id;
        int distanceFromSource=-1;
        boolean isVisited=false;
        Map<Node, Integer> distancesByNeighbor;
        List<Integer> pathFromSource = new ArrayList<Integer>();

        public Node(int id) {
            this.id = id;
            this.distancesByNeighbor = new HashMap<Node, Integer>();
        }
        public void addNeighbor(Node node, int distance) {
            Integer currentDistance = distancesByNeighbor.get(node);
            if (currentDistance==null || currentDistance>distance) {
                distancesByNeighbor.put(node, distance);
            }
        }
        public Map<Node, Integer> getDistancesByNeighbor() {
            return distancesByNeighbor;
        }
        public int getDistanceFromSource() {
            return distanceFromSource;
        }
        public Node setDistanceFromSource(Node fromNode, int distanceFromSource) {
            this.distanceFromSource = distanceFromSource;
            if (fromNode!=null) {
                this.pathFromSource = new ArrayList<Integer>(fromNode.pathFromSource);
                this.pathFromSource.add(fromNode.id);
            }
            return this;
        }
        public boolean getIsVisited() {
            return isVisited;
        }
        public void setIsVisited(boolean visited) {
            this.isVisited = visited;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            return id == ((Node)o).id;
        }
        @Override
        public int hashCode() {
            return id;
        }
    }
}