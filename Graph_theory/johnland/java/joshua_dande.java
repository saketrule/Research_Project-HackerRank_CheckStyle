import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Node implements Comparable<Node> {
    private int node;
    private int shortestWeight;
    private int parent;
    
    public Node(int node, int shortestWeight, int parent) {
        this.node = node;
        this.shortestWeight = shortestWeight;
        this.parent = parent;
    }
    
    public int compareTo(Node n) {
        return Integer.compare(shortestWeight, n.shortestWeight);
    }
    
    public int getNode() {
        return node;
    }
}

class Edge {
    private int end;
    private int weight;

    public Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    
    public int getEnd() {
        return end;
    }
    
    public int getWeight() {
        return weight;
    }
}

class Graph {
    private int numberVertices;
    private int numberEdges;
    private LinkedList<Edge>[] adjList;

    public Graph(int numberVertices, int numberEdges) {
      this.numberVertices = numberVertices;
      this.numberEdges = numberEdges;
      this.adjList = new LinkedList[numberVertices];

      for(int i = 0; i < numberVertices; i++) {
        adjList[i] = new LinkedList<>();
      }
    }

    public void addEdge(int start, int end, int weight) {
      Edge s = new Edge(end, weight);
      Edge e = new Edge(start, weight);

      adjList[start].add(s);
      adjList[end].add(e);
    }
    
    public LinkedList<Edge>[] getAdj() {
        return adjList;
    }
}

public class Solution {
    static int[] distance;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numCities = in.nextInt();
        int numRoads = in.nextInt();
        int sum = 0;

        Graph graph = new Graph(numCities, numRoads);

        for(int i = 0; i < numRoads; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int weight = (int)Math.pow(2, in.nextInt());
            graph.addEdge(start - 1, end - 1, weight);
        }
        
        
        for(int i = 0; i < numCities; i++) {
            getDistance(numCities, i, graph);
            for(int j = i + 1; j < distance.length; j++) {
                sum += distance[j];
            }
        }
        
        System.out.println(Integer.toBinaryString(sum));
    }
    
    public static void getDistance(int numCities, int start, Graph g) {
        distance = new int[numCities];
        
        for(int i = 0; i < numCities; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        
        distance[start] = 0;
        
        PriorityQueue<Node> minHeap = new PriorityQueue<>(numCities);
        minHeap.add(new Node(start, 0, -1));
        
        while (minHeap.size() > 0) {
            Node min = minHeap.poll();

            Iterator<Edge> it = g.getAdj()[min.getNode()].iterator();
            
            while(it.hasNext()) {
                Edge currentEdge = it.next();
                
                if(distance[min.getNode()] + currentEdge.getWeight() < distance[currentEdge.getEnd()]) {
                    distance[currentEdge.getEnd()] = distance[min.getNode()] + currentEdge.getWeight();
                    minHeap.add(new Node(currentEdge.getEnd(), distance[currentEdge.getEnd()], min.getNode()));
                }
            }
        }
    }
}