import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {
    
    final static int AREA = 0;
    final static int PRICE = 1;
    
    private static class Edge {
        int to, from, capacity, residual;
        
        Edge(int from, int to, int capacity, int residual) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.residual = residual;
        }
    }
    
    private static class FlowGraph {
        int numVertices, source, sink;
        int numEdges = 0;
        ArrayList<ArrayList<Edge>> vertices;
        
        FlowGraph(int numVertices, int source, int sink) {
            this.numVertices = numVertices;
            this.source = source; 
            this.sink = sink;
            vertices = new ArrayList<ArrayList<Edge>>(numVertices);
            for (int i = 0; i < numVertices; i++) {
               vertices.add(i, new ArrayList<Edge>());
            }
        }
        
        public void addEdge(Edge edge) {
            //System.out.println("to from edge " + edge.to + " " + edge.from + " " + vertices.size());
            vertices.get(edge.from).add(edge);
            vertices.get(edge.to).add(edge);
            numEdges += 1;
        }
        
        
        private void updatePath(Stack<Edge> edges, int value) {
            int vertex = sink;
            while (!edges.empty()) {
                Edge thisEdge = edges.pop();
                if (thisEdge.to == vertex) {
                    thisEdge.capacity -= value;
                    thisEdge.residual += value;
                    vertex = thisEdge.from;
                }
                else if (thisEdge.from == vertex) {
                    thisEdge.capacity += value;
                    thisEdge.residual -= value;
                    vertex = thisEdge.to;
                }  
            }
        }
        
        private Stack<Edge> findPathHelper(int vertex, Stack<Edge> path, boolean[] visited) {
            if (vertex == sink) { return path; }
            for (Edge edge : vertices.get(vertex)) {
                Stack<Edge> newPath = null;
                if (vertex == edge.from && !visited[edge.to] && edge.capacity > 0) {
                    visited[edge.to] = true;
                    path.push(edge);
                    newPath = findPathHelper(edge.to, path, visited);
                    if (newPath == null) { path.pop(); }
                    else { return newPath; }
                }
                else if (vertex == edge.to && !visited[edge.from] && edge.residual > 0) {
                    visited[edge.from] = true;
                    path.push(edge);
                    newPath = findPathHelper(edge.from, path, visited);
                    if (newPath == null) { path.pop(); }
                    else { return newPath; }
                }
            }
            return null; 
        }
        
        private int findPath() {
            int output = 0;
            boolean[] visited = new boolean[numVertices];
            visited[source] = true;
            Stack<Edge> path = findPathHelper(source, new Stack<Edge>(), visited);
            if (path != null) {
                updatePath(path, 1);
                output += 1;
            }
            return output;
        }
        
        int maxFlow() {
            int maxFlow = 0;
            while (true) {
                int path = findPath();
                if (path > 0) { maxFlow += path; }
                else { break; }
            }
            return maxFlow;
        }
        
    }
    
    
    static void testPrint(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");           
        }
        System.out.println("");
    }
    
    
    
    public static FlowGraph createGraph(int[][] clients, int[][] houses) {
        FlowGraph output = new FlowGraph(houses.length + clients.length + 2,
                                         0, houses.length + clients.length + 1);
        int clientOffset = 1;
        int houseOffset = clients.length + 1;
        for (int i = 0; i < clients.length; i++) {
            output.addEdge(new Edge(output.source, i + clientOffset, 1, 0));
        }
        for (int i = 0; i < houses.length; i++) {
            output.addEdge(new Edge(i + houseOffset, output.sink, 1, 0));
        }
        for (int i = 0; i < clients.length; i++) {
            int cArea = clients[i][AREA];
            int cPrice = clients[i][PRICE];
            for (int j = 0; j < houses.length; j++) {
                //System.out.println("client i = " + i + " house j = " + j);
                if (houses[j][AREA] > cArea && houses[j][PRICE] <= cPrice) {
                    output.addEdge(new Edge(i + clientOffset, j + houseOffset, 1, 0));
                } 
            }
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numClients = in.nextInt();
        int numHouses = in.nextInt();
        int[][] clients = new int[numClients][2];
        int[][] houses = new int[numHouses][2];
        for (int i = 0; i < numClients; i++) {
            clients[i][AREA] = in.nextInt();
            clients[i][PRICE] = in.nextInt();
        }
        for (int i = 0; i < numHouses; i++) {
            houses[i][AREA] = in.nextInt();
            houses[i][PRICE] = in.nextInt();
        }
        FlowGraph graph = createGraph(clients, houses);
        System.out.println(graph.maxFlow());
    }
}