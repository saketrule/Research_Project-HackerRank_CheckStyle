import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class DirectedEdge {
    private final int       startingVertex;
    private final int       finalVertex;
    private final double    weight;

    public DirectedEdge(int startingVertex, int finalVertex, double weight) {
        if (startingVertex < 0) {
            throw new IndexOutOfBoundsException("Vertex names must" 
                    + " be nonnegative integers");
        }
        
        if (finalVertex < 0) {
            throw new IndexOutOfBoundsException("Vertex names must" 
                    + " be nonnegative integers");
        }
        
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("Weight is NaN");
        }
        
        this.startingVertex = startingVertex;
        this.finalVertex = finalVertex;
        this.weight = weight;
    }

    public int from() {
        return startingVertex;
    }

    public int to() {
        return finalVertex;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        final int   PRIME = 31;
        int         result = 1;
        
        result = PRIME * result + startingVertex;
        result = PRIME * result + finalVertex;
        result = PRIME * result + Double.valueOf(weight).hashCode();
        
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        DirectedEdge other = (DirectedEdge) obj;
        
        if (startingVertex != other.startingVertex) {
            return false;
        }

        if (finalVertex != other.finalVertex) {
            return false;
        }      
        
        if (weight != other.weight) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public String toString() {
        return startingVertex + "->" + finalVertex 
                + " " + String.format("%5.2f", weight);
    }
}

class AdjMatrixEdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private int                 numVertices;
    private int                 numEdges;
    private DirectedEdge[][]    adjacencyMatrix;
    
    public AdjMatrixEdgeWeightedDigraph(int numVertices) {
        if (numVertices < 0) {
            throw new RuntimeException("Number of vertices must be nonnegative");
        }
        
        this.numVertices = numVertices;
        this.numEdges = 0;
        adjacencyMatrix = new DirectedEdge[numVertices][numVertices];
    }

    public int getNumberOfVertices() {
        return numVertices;
    }

    public int getNumberOfEdges() {
        return numEdges;
    }

    public void addEdge(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        
        if (adjacencyMatrix[v][w] == null) {
            numEdges++;
        }
        
        adjacencyMatrix[v][w] = edge;
    }

    public Iterable<DirectedEdge> getAdjacentEdgesIterator(int vertex) {
        return new AdjacentEdgesIterator(vertex);
    }

    // support iteration over graph vertices
    private class AdjacentEdgesIterator implements Iterator<DirectedEdge>, 
            Iterable<DirectedEdge> {
        private int v;
        private int w = 0;

        public AdjacentEdgesIterator(int vertex) {
            v = vertex;
        }

        public Iterator<DirectedEdge> iterator() {
            return this;
        }

        public boolean hasNext() {
            while (w < numVertices) {
                if (adjacencyMatrix[v][w] != null) {
                    return true;
                }
                
                w++;
            }
            
            return false;
        }

        public DirectedEdge next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            return adjacencyMatrix[v][w++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        
        s.append(numVertices + " " + numEdges + NEWLINE);
        
        for (int vertex = 0; vertex < numVertices; vertex++) {
            s.append(vertex + ": ");
            
            for (DirectedEdge edge: getAdjacentEdgesIterator(vertex)) {
                s.append(edge + "  ");
            }
            
            s.append(NEWLINE);
        }
        
        return s.toString();
    }
}

class FloydWarshall {
    private boolean             hasNegativeCycle;   // is there a negative cycle?
    private double[][]          distanceTo;         // distTo[v][w] = length of shortest v->w path
    private DirectedEdge[][]    edgeTo;             // edgeTo[v][w] = last edge on shortest v->w path

    public FloydWarshall(AdjMatrixEdgeWeightedDigraph adjacencyMatrix) {
        int numVertices = adjacencyMatrix.getNumberOfVertices();
        
        distanceTo = new double[numVertices][numVertices];
        edgeTo = new DirectedEdge[numVertices][numVertices];

        // initialize distances to infinity
        for (int v = 0; v < numVertices; v++) {
            for (int w = 0; w < numVertices; w++) {
                distanceTo[v][w] = Double.POSITIVE_INFINITY;
            }
        }

        // initialize distances using edge-weighted digraph's
        for (int v = 0; v < numVertices; v++) {
            for (DirectedEdge edge: adjacencyMatrix.getAdjacentEdgesIterator(v)) {
                distanceTo[edge.from()][edge.to()] = edge.getWeight();
                edgeTo[edge.from()][edge.to()] = edge;
            }
            
            // in case of self-loops
            if (distanceTo[v][v] >= 0.0) {
                distanceTo[v][v] = 0.0;
                edgeTo[v][v] = null;
            }
        }

        for (int i = 0; i < numVertices; i++) {
            for (int v = 0; v < numVertices; v++) {
                if (edgeTo[v][i] == null) {
                    continue;  // optimization
                }
                
                for (int w = 0; w < numVertices; w++) {
                    if (distanceTo[v][w] > distanceTo[v][i] + distanceTo[i][w]) {
                        distanceTo[v][w] = distanceTo[v][i] + distanceTo[i][w];
                        edgeTo[v][w] = edgeTo[i][w];
                    }
                }
                
                // check for negative cycle
                if (distanceTo[v][v] < 0.0) {
                    hasNegativeCycle = true;
                    return;
                }
            }
        }
    }

    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    public boolean hasPath(int source, int target) {
        return distanceTo[source][target] < Double.POSITIVE_INFINITY;
    }

    public double getDistance(int source, int target) {
        if (hasNegativeCycle()) {
            throw new UnsupportedOperationException("Negative cost cycle exists");
        }
        
        return distanceTo[source][target];
    }

    public Iterable<DirectedEdge> getPath(int source, int target) {
        if (hasNegativeCycle()) {
            throw new UnsupportedOperationException("Negative cost cycle exists");
        }
        
        if (!hasPath(source, target)) {
            return null;
        }
        
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        
        for (DirectedEdge edge = edgeTo[source][target]; 
                edge != null; edge = edgeTo[source][edge.from()]) {
            path.push(edge);
        }
        
        return path;
    }
}

public class Solution {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner                         scanner = new Scanner(System.in);
        final int                       NUM_VERTICES = scanner.nextInt();
        final int                       NUM_EDGES = scanner.nextInt();
        AdjMatrixEdgeWeightedDigraph    adjacencyMatrix = new AdjMatrixEdgeWeightedDigraph(NUM_VERTICES);
        
        for (int edgeIndex = 0; edgeIndex < NUM_EDGES; edgeIndex++) {
            DirectedEdge    edge = new DirectedEdge(scanner.nextInt() - 1, 
                    scanner.nextInt() - 1, scanner.nextDouble());
            
            adjacencyMatrix.addEdge(edge);
        }
        
        FloydWarshall   algorithm = new FloydWarshall(adjacencyMatrix);
        final int       NUM_QUERIES = scanner.nextInt();
        
        for (int queryIndex = 0; queryIndex < NUM_QUERIES; queryIndex++) {
            int     source = scanner.nextInt() - 1;
            int     target = scanner.nextInt() - 1;
            long    theShortestPath;
                
            if (algorithm.hasPath(source, target)) {
                theShortestPath = (long) algorithm.getDistance(source, target);
            } else {
                theShortestPath = -1;
            }
            
            System.out.println(theShortestPath);
        }
        
        scanner.close();
    }
}