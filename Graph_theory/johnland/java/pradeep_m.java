import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static final BigInteger INFINITE = new BigInteger("2").pow((int)Math.pow(10, 6));
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int vertices = in.nextInt();
        int edges = in.nextInt();
        Graph graph = new Graph(vertices + 1, Integer.MAX_VALUE);
        for(int i = 0; i< edges; i++) {
            int source = in.nextInt();
            int destination = in.nextInt();
            int distance = in.nextInt();
            graph.addEdge(source, destination, distance);
        }
        floydWarshall(graph);
    }
    
    private static void floydWarshall(Graph graph) {
        int totalVertices = graph.getVertices();
        BigInteger dist[][] = new BigInteger[totalVertices][totalVertices];
        int i, j, k;

        for (i = 0; i < totalVertices; i++) {
            for (j = 0; j < totalVertices; j++) {
                long weight = graph.getEdgeWeight(i, j);
                if(weight != Integer.MAX_VALUE) {
                    dist[i][j] = new BigInteger("2").pow((int)weight);
                } else {
                    dist[i][j] = INFINITE;
                }
            }
        }

        for (k = 0; k < totalVertices; k++) {
            for (i = 0; i < totalVertices; i++) {
                for (j = 0; j < totalVertices; j++) {
                    if(dist[i][k] != INFINITE && dist[k][j] != INFINITE) {
                        if (dist[i][k].add(dist[k][j]).compareTo(dist[i][j]) == -1) {
                            dist[i][j] = dist[i][k].add(dist[k][j]);
                        }
                    }
                }
            }
        }

        // Print the shortest distance matrix
        printSolution(dist);
    }

    private  static void printSolution(BigInteger dist[][]) {
        BigInteger sum = new BigInteger("0");
        for (int i=1; i<dist.length; ++i) {
            for (int j=i+1; j<dist.length; ++j) {
                //System.out.println(" i " + i + "==" + " j " + j + " ->" +  dist[i][j]);
                sum = sum.add(dist[i][j]);
            }
        }
        //System.out.println(sum);
        System.out.println(sum.toString(2));
    }
    
    public static class Graph {
        private int vertices;
        private int[] [] adjMatrix;
        public Graph(int vertices) {
            this.vertices = vertices;
            adjMatrix = new int [vertices] [vertices];
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j< vertices; j++) {
                    adjMatrix[i][j] = -1;
                }
            }
        }

        public Graph(int vertices, int defaultValue) {
            this.vertices = vertices;
            adjMatrix = new int [vertices] [vertices];
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j< vertices; j++) {
                    adjMatrix[i][j] = defaultValue;
                }
            }
        }

        public void printMatrix() {
            for(int i = 0; i < vertices; i++) {
                for(int j = 0; j< vertices; j++) {
                    System.out.print(adjMatrix[i][j] + " ");

                }
                System.out.println("");
            }
        }

        public int getVertices() {
            return this.vertices;
        }

        public void addEdge(int source,int destination) {
            adjMatrix[source][destination] = 1;
            adjMatrix[destination][source] = 1;

        }

        public void addEdge(int source,int destination, int weight) {
            if(adjMatrix[source][destination] == -1 || adjMatrix[source][destination] > weight) {
                adjMatrix[source][destination] = weight;
                adjMatrix[destination][source] = weight;
            }

        }

        public boolean edgeExists(int source, int destination) {
            return adjMatrix[source][destination] != -1 ? true : false;
        }

        public int getEdgeWeight(int source, int destination) {
            return adjMatrix[source][destination];
        }
}
}