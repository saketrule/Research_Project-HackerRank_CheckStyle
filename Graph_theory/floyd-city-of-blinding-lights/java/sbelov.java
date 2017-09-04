import java.util.*;

public class SolutionCity {

  private static final class Node {
    private final int id;
    private Map<Node, Integer> distances = new HashMap<>();

    public Node(int id) {
      this.id = id;
    }

    public int getId() {
      return id;
    }

    public Map<Node, Integer> getDistances() {
      return distances;
    }

    public Node addNeighbor(Node other, int distance) {
      distances.put(other, distance);
      return this;
    }

    public int hashCode() {
      return id;
    }

    public boolean equals(Object other) {
      if (other instanceof Node) {
        Node otherNode = (Node)other;
        return otherNode.id == this.id;
      }
      return false;
    }
  }

  private static final class Edge implements Comparable<Edge> {
    private final Node from;
    private final Node to;
    private final int distance;

    public Edge(Node from, Node to, int distance) {
      this.from = from;
      this.to = to;
      this.distance = distance;
    }

    public Node getFrom() {
      return from;
    }

    public Node getTo() {
      return to;
    }

    public int getDistance() {
      return distance;
    }

    public int compareTo(Edge other) {
      return this.distance - other.distance;
    }
  }

  private final int[][] distances;
  private final Node[] nodes;
  private Set<Node> explored = new HashSet<>();

  public SolutionCity(int nodeCount) {
    this.nodes = new Node[nodeCount];
    for (int i = 0; i < nodeCount; i++) {
      nodes[i] = new Node(i + 1);
    }
    this.distances = new int[nodeCount][];
    for (int i = 0; i < nodeCount; i++) {
      distances[i] = new int[nodeCount];
      for (int j = 0; j < nodeCount; j++) {
        distances[i][j] = Integer.MAX_VALUE;
      }
      distances[i][i] = 0;
    }
  }

  public SolutionCity addEdge(int from, int to, int distance) {
    nodes[from - 1].addNeighbor(nodes[to - 1], distance);
    distances[from - 1][to - 1] = distance;
    return this;
  }

  public int shortestDistance(int from, int to) {
    Queue<Edge> queue = new PriorityQueue<>();
    Node fromNode = nodes[from - 1];
    if (!explored.contains(fromNode)) {
      queue.add(new Edge(fromNode, fromNode, 0));
      Set<Node> visited = new HashSet<>();
      while (!queue.isEmpty()) {
        Edge currentEdge = queue.poll();
        Node currentNode = currentEdge.getTo();
        if (!visited.contains(currentNode)) {
          visited.add(currentNode);
          int currentDistance = distances[from - 1][currentNode.getId() - 1];
          for (Node neighbor : currentNode.getDistances().keySet()) {
            int distance = currentNode.getDistances().get(neighbor) + currentDistance;
            if (distance < distances[from - 1][neighbor.getId() - 1]) {
              distances[from - 1][neighbor.getId() - 1] = distance;
            }
            queue.add(new Edge(fromNode, neighbor, distance));
          }
        }
      }
      explored.add(fromNode);
    }
    return distances[from - 1][to - 1] == Integer.MAX_VALUE ? -1 : distances[from - 1][to - 1];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int nodeCount = scanner.nextInt();
    SolutionCity solution = new SolutionCity(nodeCount);
    int edgeCount = scanner.nextInt();
    for (int i = 0; i < edgeCount; i++) {
      solution.addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
    }
    int queries = scanner.nextInt();
    for (int i = 0; i < queries; i++) {
      System.out.println(solution.shortestDistance(scanner.nextInt(), scanner.nextInt()));
    }
  }
}