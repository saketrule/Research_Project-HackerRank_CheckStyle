import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {

 private static class Node {
  private final Integer id;
  private final Map<Edge, Node> edges;

  private Node(Integer id) {
   this.id = id;
   this.edges = new HashMap<Edge, Node>();
  }

  @Override
  public String toString() {
   return "Node [id=" + id + "]";
  }

  private Map<Edge, Node> getEdges() {
   return edges;
  }
 }

 private static class Edge {
  private final Set<Node> nodes;
  private final Integer penalty;

  private Edge(Node node1, Node node2, Integer penalty) {
   this.nodes = new HashSet<Node>();
   nodes.add(node1);
   nodes.add(node2);
   this.penalty = penalty;
  }

  @Override
  public String toString() {
   return "Edge [nodes=" + nodes + ", penalty=" + penalty + "]";
  }

  private Integer getPenalty() {
   return penalty;
  }
 }

 private static Integer noOfNodes;
 private static Integer noOfEdges;
 private static Map<Integer, Node> nodes;
 private static Set<Edge> edges;

 private static Node startNode;
 private static Node endNode;

 private static void readInputs() {
  try (Scanner scan = new Scanner(System.in)) {
   String firstLine = scan.nextLine();
   String[] firstLineData = firstLine.trim().split(" ");
   noOfNodes = Integer.valueOf(firstLineData[0]);
   noOfEdges = Integer.valueOf(firstLineData[1]);

   nodes = new HashMap<Integer, Node>();
   for (int i = 1; i <= noOfNodes; i++) {
    nodes.put(i, new Node(i));
   }

   edges = new HashSet<Edge>();
   for (int i = 0; i < noOfEdges; i++) {
    String edgeLine = scan.nextLine();
    String[] edgeLineData = edgeLine.trim().split(" ");

    Node node1 = nodes.get(Integer.valueOf(edgeLineData[0]));
    Node node2 = nodes.get(Integer.valueOf(edgeLineData[1]));

    Edge edge = new Edge(node1, node2, Integer.valueOf(edgeLineData[2]));
    edges.add(edge);

    node1.getEdges().put(edge, node2);
    node2.getEdges().put(edge, node1);
   }

   String lastLine = scan.nextLine();
   String[] lastLineData = lastLine.trim().split(" ");
   startNode = nodes.get(Integer.valueOf(lastLineData[0]));
   endNode = nodes.get(Integer.valueOf(lastLineData[1]));
  }
 }

 private static void printInputs() {
  System.out.println("noOfNodes: " + noOfNodes);
  System.out.println("noOfEdges: " + noOfEdges);
  System.out.println("edges: " + edges);
  System.out.println("startNode: " + startNode);
  System.out.println("endNode: " + endNode);
 }

 private static Integer computeMinimumPenalty(Node startNode, Node endNode) {
  Map<Node, Integer> tentativePenalties = new HashMap<Node, Integer>();
  for (Node node : nodes.values()) {
   tentativePenalties.put(node, -1);
  }
  tentativePenalties.put(startNode, 0);

  Set<Node> unvisitedNodes = new HashSet<Node>(nodes.values());

  doDijkstrasAlgorithm(startNode, endNode, tentativePenalties, unvisitedNodes);

  return tentativePenalties.get(endNode);
 }

 private static void doDijkstrasAlgorithm(Node startNode, Node endNode, Map<Node, Integer> tentativePenalties, Set<Node> unvisitedNodes) {
  for (Map.Entry<Edge, Node> entry : startNode.getEdges().entrySet()) {
   Edge edge = entry.getKey();
   Node neighboringNode = entry.getValue();

   if(unvisitedNodes.contains(neighboringNode)) {
    Integer currentPenalty = tentativePenalties.get(neighboringNode);
    Integer calculatedPenalty = tentativePenalties.get(startNode) | edge.getPenalty();
    if(currentPenalty < 0 || currentPenalty > calculatedPenalty) {
     tentativePenalties.put(neighboringNode, calculatedPenalty);
    }
   }
  }

  unvisitedNodes.remove(startNode);

  if(startNode == endNode) {
   return;
  }

  Node minimumTentativePenaltyNode = null;
  for (Node unvisitedNode : unvisitedNodes) {
   Integer tentativePenalty = tentativePenalties.get(unvisitedNode);
   if(tentativePenalty > 0 && (minimumTentativePenaltyNode == null || tentativePenalty < tentativePenalties.get(minimumTentativePenaltyNode))) {
    minimumTentativePenaltyNode = unvisitedNode;
   }
  }
  if(minimumTentativePenaltyNode == null) {
   return;
  }

  doDijkstrasAlgorithm(minimumTentativePenaltyNode, endNode, tentativePenalties, unvisitedNodes);
 }

 public static void main(String[] args) {
  readInputs();
  //  printInputs();
  System.out.print(computeMinimumPenalty(startNode, endNode));
 }

}