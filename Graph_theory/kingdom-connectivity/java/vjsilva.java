/*
 Enter your code here. Read input from STDIN. Print output to STDOUT
 Your class should be named Solution
*/


import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {

 /* Node status for DFS search */
 private static final int NOT_VISITED = 0;
 private static final int VISITING = 1;
 private static final int VISITED = 2;
 private static int PATH_FOUND = 1;
 private static int NO_PATH = -1;
 private static int INFINITE_PATHS = -2;
 private static int PATH_MOD = 1000000000;

 public void solve() {
  Graph graph = readInput();

  int count = graph.countPaths(1, graph.getVertexCount());
  if (count < 0)
   System.out.println("INFINITE PATHS");
  else
   System.out.println(count);
 }

 private Graph readInput() {
  try {
   String line = readLine();
   String[] data = line.split(" ");
   Graph graph = new Graph(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
   int edgeCount = graph.getEdgeCount();

   for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
    line = readLine();
    data = line.split(" ");
    graph.addEdge(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
   }

   return graph;
  } catch (IOException e) {
   throw new RuntimeException("Failed to read input.", e);
  }
 }

 /* Read a line from standard input */
 private String readLine() throws IOException {
  StringBuilder buf = new StringBuilder();
  int c = -1;
  while ((c = System.in.read()) != -1) {
   if (c == '\n')
    break;

   if (c != '\r')
    buf.append((char) c);
  }

  return buf.toString();
 }

 public static void main(String args[]) {
  Solution solution = new Solution();
  solution.solve();
 }

 private static class Graph {
  private Vertex[] vertices;
  private Edge[] edges;
  private int edgeCounter;

  public Graph(int vertexCount, int edgeCount) {
   this.vertices = new Vertex[vertexCount];
   for (int i = 1; i <= vertexCount; i++) {
    this.vertices[i - 1] = new Vertex(i);
   }

   this.edges = new Edge[edgeCount];
   this.edgeCounter = 0;
  }

  public int getVertexCount() {
   return vertices.length;
  }

  public int getEdgeCount() {
   return edges.length;
  }

  public Vertex getVertex(int vertexKey) {
   return vertices[vertexKey - 1];
  }

  public void addEdge(int sourceKey, int destinationKey) {
   Vertex source = getVertex(sourceKey);
   Vertex destination = getVertex(destinationKey);
   Edge edge = new Edge(source, destination);
   source.addEdge(edge);
   edges[edgeCounter++] = edge;
  }

  public int countPaths(int sourceKey, int destinationKey) {
   if (sourceKey == destinationKey)
    return 0;

   Vertex source = getVertex(sourceKey);
   Vertex destination = getVertex(destinationKey);
   VisitData data = dfsVisit(source, destination);
   if (data.hasCyclicPathToDestination() && data.pathCount > 0)
    return INFINITE_PATHS;

   return data.pathCount;
  }

  /* Perform depth first search for ALL paths that end at destination */
  private VisitData dfsVisit(Vertex vertex, Vertex destination) {
   if (vertex.equals(destination))
    return new VisitData(PATH_FOUND, null);

   Set<Vertex> cycleStarts = new HashSet<Vertex>();
   boolean hasCyclicPath = false;
   if (vertex.status == NOT_VISITED) {
    vertex.status = VISITING;
    for (Edge edge : vertex) {
     VisitData data = dfsVisit(edge.getDestination(), destination);

     /* update cyclic path marker */
     if (data.hasCyclicPathToDestination()) {
      hasCyclicPath = true;
      break;
     }

     /* Add paths */
     vertex.addPaths(data.getPathCount());

     /* Add the cycle starts */
     if (data.getCycleStarts() != null) {
      for (Vertex cycleStart : data.getCycleStarts()) {
       if (!vertex.equals(cycleStart)) {
        cycleStarts.add(cycleStart);
       }
      }
     }

     /* Mark vertex as part of cycle if there are valid cycle starts */
     if (!cycleStarts.isEmpty())
      vertex.markCycle();

     /* Mark the cyclic path to destination */
     if (vertex.isCycle() && vertex.hasPathToDestination()) {
      hasCyclicPath = true;
      break;
     }
    }

    vertex.status = VISITED;
   } else if (vertex.status == VISITING) {
    /* This vertex is first node found in a cycle */
    vertex.markCycle();
    cycleStarts.add(vertex);

    /* Mark the cyclic path to destination */
    if (vertex.hasPathToDestination())
     hasCyclicPath = true;
   }

   int pathCount = (vertex.hasPathToDestination()) ? vertex.getPathCount() : NO_PATH;
   VisitData visitData = new VisitData(pathCount, cycleStarts);
   if (hasCyclicPath)
    visitData.markCyclicPathToDestination();

   return visitData;
  }

  private static class Vertex implements Iterable<Edge> {
   private final int key;
   private final List<Edge> edges;
   int status = NOT_VISITED;
   private int pathCount = 0;
   private boolean pathExists = false;
   private boolean cycle = false;

   public Vertex(int key) {
    this.key = key;
    edges = new LinkedList<Solution.Graph.Edge>();
   }

   public void addEdge(Edge edge) {
    edges.add(0, edge);
   }

   public Iterator<Edge> iterator() {
    return edges.iterator();
   }

   public boolean isCycle() {
    return this.cycle;
   }

   public void markCycle() {
    this.cycle = true;
   }

   public void addPaths(int pathCount) {
    if (pathCount != NO_PATH) {
     this.pathCount = (this.pathCount + pathCount) % PATH_MOD;
     this.pathExists = true;
    }
   }

   public int getPathCount() {
    return this.pathCount;
   }

   public boolean hasPathToDestination() {
    return this.pathExists;
   }

   public String toString() {
    return "Vertex[" + key + "]";
   }
  }

  private static class Edge {
   private final Vertex source;
   private final Vertex destination;

   public Edge(Vertex source, Vertex destination) {
    this.source = source;
    this.destination = destination;
   }

   public Vertex getDestination() {
    return destination;
   }

   @Override
   public String toString() {
    return "Edge[" + source + " -> " + destination + "]";
   }
  }

  private static class VisitData {
   final int pathCount;
   final Set<Vertex> cycleStarts;
   boolean cycle = false;

   public VisitData(int pathCount, Set<Vertex> cycleStarts) {
    this.pathCount = pathCount;
    this.cycleStarts = cycleStarts;
   }

   public int getPathCount() {
    return pathCount;
   }

   public Set<Vertex> getCycleStarts() {
    return cycleStarts;
   }

   public boolean hasCyclicPathToDestination() {
    return this.cycle;
   }

   public void markCyclicPathToDestination() {
    this.cycle = true;
   }
  }
 }
}