import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class JeaniesRouteForHackerrank<V> {
 static Graph<Integer, Integer> graph;
 static long countCall = 0;
 static long countTwo = 0;

 public static void main(String[] args)  {

  
  Scanner sc = new Scanner(System.in);

  int N = sc.nextInt();
  int K = sc.nextInt();
  graph = new DirectedGraph<Integer, Integer>(N);

  boolean[] nodesToVisit = new boolean[N + 1];
  for (int i = 1; i <= K; i++) {
   nodesToVisit[sc.nextInt() - 1] = true;
  }

  while (sc.hasNext()) {
   int startNode = sc.nextInt() - 1;
   int endNode = sc.nextInt() - 1;
   int weight = sc.nextInt();
   EdgeImpl<Integer> e1 = new EdgeImpl<Integer>(startNode, endNode, weight);
   EdgeImpl<Integer> e2 = new EdgeImpl<Integer>(endNode, startNode, weight);
   if (graph.getNodes()[startNode] == null) {
    graph.getNodes()[startNode] = new NodeImpl<Integer, Integer>(startNode);
   }
   graph.getNodes()[startNode].addEdge(e1);

   if (graph.getNodes()[endNode] == null) {
    graph.getNodes()[endNode] = new NodeImpl<Integer, Integer>(endNode);
   }
   graph.getNodes()[endNode].addEdge(e2);
  }
  long solution = JeaniesRouteForHackerrank.solve(K, nodesToVisit);
  System.out.println(solution);
  sc.close();

 }

 // could be improved expecially for big graphs
 private static void removeLeafsThatDontNeedToBeVisited(boolean visitedNode[]) {
  int numOfRemoved = 0;
  do {
   numOfRemoved = 0;
   for (Node<Integer, Integer> node : graph.getNodes()) {
    // System.out.println(node.getEdges().size());
    if (node != null && !visitedNode[node.getNameOfNode()] && node.getEdges().size() == 1) {
     ((DirectedGraph<Integer, Integer>) graph).removeNode(node);
     numOfRemoved = 1;
    }
   }
  } while (numOfRemoved > 0);
 }

 private static long calcMinimumSpanningTreeForTree() {
  long sum = 0;
  for (Node<Integer, Integer> node : graph.getNodes()) {
   // System.out.println("für knoten: " + node.getNameOfNode());

   for (Edge<Integer> edge : node.getEdges()) {
    sum += edge.getWeight();
    // System.out.println(edge.getWeight());
   }
  }
  sum = sum / 2;
  // System.out.println("alle kanten addiert");
  return sum;
 }

 private static long solve(int K, boolean[] nodesToVisit) {
  double time1 = System.currentTimeMillis();
  removeLeafsThatDontNeedToBeVisited(nodesToVisit);
  double time2 = System.currentTimeMillis();
  //System.out.println("zeit für leaf entfernen: " + (time2 - time1) / 1000);
  long valueMST = calcMinimumSpanningTreeForTree();
  double time3 = System.currentTimeMillis();
  //System.out.println("Zeit für MST " + (time3 - time2) / 1000);

  long valueEulerTour = 2 * valueMST;
  // System.out.println(valueEulerTour);
  long maxDistance = longestPath(nodesToVisit);
  double time4 = System.currentTimeMillis();
  //System.out.println("Zeit für längsten Weg " + (time4 - time3) / 1000);
 // long maxDistFloyd = FloydAlgorithm(graph);
 // System.out.println("maxDistance: " + maxDistance + " maxDistFloyd" + maxDistFloyd);
  long solution = valueEulerTour - maxDistance;
  return solution;
 }

 

 private static long longestPath(boolean[] nodesToVisit) {

  long longestPath = 0;

  int innerNode = findOuterNode(graph);

  //System.out.println("innerNode " + (innerNode + 1));
  int[] maxPathmaxDiameter = null;
  if (innerNode == -1) {
   longestPath = maxDistanceLessEqualTwoNodes(graph);
  } else {
   maxPathmaxDiameter = maxPathMaxDiameter(innerNode, innerNode);
   // System.out.println("  maxDiameter und maxPath "+maxPathmaxDiameter[0]
   // + " "+maxPathmaxDiameter[1]);
   if (maxPathmaxDiameter[0] > maxPathmaxDiameter[1]) {
    longestPath = maxPathmaxDiameter[0];
   } else {
    longestPath = maxPathmaxDiameter[1];
   }
  }

  return longestPath;
 }

 private static int[] maxPathMaxDiameter(int currentNode, int lastNode) {
  

  if (graph.getNodes()[currentNode].getEdges().size() == 1 && lastNode != currentNode) {
   int sol[] = { 0, 0 };
   return sol;
  }

  int maxDiameter = 0;
  // LinkedList<Integer> maxPath = new LinkedList<>();
  int maxPath1 = 0;
  int maxPath2 = 0;
  // Node<Integer, Integer> node = graph.getNodes()[currentNode];

  for (Edge<Integer> edge : graph.getNodes()[currentNode].getEdges()) {
   // System.out.println(curNode.getEdges().size());
   int currentTarget = edge.getTargetNode();
   int lastNodeCopy = lastNode;
   int currentNodeCopy=currentNode;
   if (currentTarget != lastNode) {
    int edgeSum = 0;
    Edge<Integer> curEdge = edge;
    edgeSum += curEdge.getWeight();
    while (graph.getNodes()[currentTarget].getEdges().size() == 2) {
     lastNodeCopy = currentNodeCopy;
     currentNodeCopy = currentTarget;
     if (graph.getNodes()[currentNodeCopy].getEdges().get(0).getTargetNode() != lastNodeCopy) {
      currentTarget = graph.getNodes()[currentNodeCopy].getEdges().get(0).getTargetNode();
      curEdge = graph.getNodes()[currentNodeCopy].getEdges().get(0);
     } else if (graph.getNodes()[currentNodeCopy].getEdges().get(1).getTargetNode() != lastNodeCopy) {
      currentTarget = graph.getNodes()[currentNodeCopy].getEdges().get(1).getTargetNode();
      curEdge = graph.getNodes()[currentNodeCopy].getEdges().get(1);
     }
    
     edgeSum += curEdge.getWeight();

    }

    int sol[] = maxPathMaxDiameter(currentTarget, currentNodeCopy);
    if (sol[0] > maxDiameter) {
     maxDiameter = sol[0];
    }
    if (sol[1] + edgeSum > maxPath1) {
     maxPath2 = maxPath1;
     maxPath1 = sol[1] + edgeSum;
    } else if (sol[1] + edgeSum > maxPath2) {
     maxPath2 = sol[1] + edgeSum;
    }

   }

  }
  int maximalPath = maxPath1;
  int maxDiameterDummy = maxPath1 + maxPath2;

  if (maxDiameterDummy > maxDiameter) {
   maxDiameter = maxDiameterDummy;
  }
  int[] maxPathmaxDiameter = new int[2];
  maxPathmaxDiameter[0] = maxDiameter;
  maxPathmaxDiameter[1] = maximalPath;

  return maxPathmaxDiameter;
 }

 private static long maxDistanceLessEqualTwoNodes(Graph<Integer, Integer> g) {
  long maxDistance = 0;
  for (Node<Integer, Integer> node : g.getNodes()) {
   if (node.getEdges().size() == 1) {
    return node.getEdges().get(0).getWeight();
   }
  }
  return maxDistance;
 }

 private static int findOuterNode(Graph<Integer, Integer> g) {

  int innerNode = -1;
  for (Node<Integer, Integer> node : g.getNodes()) {
   if (node.getEdges().size() == 1) {
    return node.getNameOfNode();
   }
  }

  return innerNode;
 }

 private static long FloydAlgorithm(Graph g) {
  long[][] distanceMatrix = new long[g.getNumOfNodes()][g.getNumOfNodes()];
  long[][] shortestPath = new long[g.getNumOfNodes()][g.getNumOfNodes()];
  for (Node<Integer, Integer> node : g.getNodes()) {
   for (Edge<Integer> e : node.getEdges()) {
    if (e.getWeight() != 0) {

     distanceMatrix[node.getNameOfNode()][e.getTargetNode()] = e.getWeight();
     shortestPath[node.getNameOfNode()][e.getTargetNode()] = e.getWeight();
    } else {
     distanceMatrix[node.getNameOfNode()][e.getTargetNode()] = Long.MAX_VALUE;
     shortestPath[node.getNameOfNode()][e.getTargetNode()] = Long.MAX_VALUE;

    }
    // System.out.println(distanceMatrix[node.getNameOfNode()][e.getTargetNode()]
    // );
   }
  }
  // System.out.println();
  for (int i = 0; i < g.getNumOfNodes(); i++) {
   for (int j = 0; j < g.getNumOfNodes(); j++) {
    if (distanceMatrix[i][j] == 0 && i != j) {
     distanceMatrix[i][j] = Long.MAX_VALUE;
     shortestPath[i][j] = Long.MAX_VALUE;
    }
    // System.out.print(distanceMatrix[i][j]+ " ");
   }
   // System.out.println();
  }

  for (int k = 0; k < g.getNumOfNodes(); k++) {
   for (int i = 0; i < g.getNumOfNodes(); i++) {
    for (int j = 0; j < g.getNumOfNodes(); j++) {
     if (shortestPath[i][k] != Long.MAX_VALUE && shortestPath[k][j] != Long.MAX_VALUE) {
      long dummy = shortestPath[i][k] + shortestPath[k][j];
      if (dummy < shortestPath[i][j]) {
       shortestPath[i][j] = dummy;
      }
     }
     // }
    }
   }
  }

  long maxDistance = 0;
  int icopy = 0;
  int jcopy = 0;
  for (int i = 0; i < g.getNumOfNodes(); i++) {
   // System.out.println();

   for (int j = 0; j < g.getNumOfNodes(); j++) {
    // System.out.print(shortestPath[i][j] + " ");
    if (shortestPath[i][j] > maxDistance && shortestPath[i][j] != Long.MAX_VALUE) {
     maxDistance = shortestPath[i][j];
     icopy = i;
     jcopy = j;
    }
   }
   // System.out.println();
  }
  // System.out.println(shortestPath[0][108] + " "+shortestPath[0][124]);
  // System.out.println("max i und j "+icopy + " "+jcopy);
  return maxDistance;
 }
 
 static abstract class Node<U,V>{
  protected int nameOfNode;
  protected LinkedList<Edge<V>> edges;
  
  public Node(int nameOfNode){
   this.nameOfNode=nameOfNode;
   edges=new LinkedList<Edge<V>>();
  }
  
  public void addEdge(Edge<V> edge){
   this.edges.add(edge);
  }
  public LinkedList<Edge<V>> getEdges() {
   return edges;
  }
  public void setEdges(LinkedList<Edge<V>> edges) {
   this.edges = edges;
  }
  
  public int getNameOfNode(){
   return this.nameOfNode;
  }
  
  public void removeEdge(Edge<V> edgeToRemove){
   this.edges.remove(edgeToRemove);
  }
  

 }
 
 static abstract class Edge<V>{
  
  protected int sourceNode;
  protected int targetNode;
  protected V weight;
  
  public Edge(int sourceNode, int targetNode, V weight) {
   this.sourceNode = sourceNode;
   this.targetNode = targetNode;
   this.weight=weight;
  }
  
  public Edge(int sourceNode, int targetNode) {
   this.sourceNode = sourceNode;
   this.targetNode = targetNode;
  }
  
  public int getSourceNode() {
   return sourceNode;
  }

  public void setSourceNode(int sourceNode) {
   this.sourceNode = sourceNode;
  }

  public int getTargetNode() {
   return targetNode;
  }

  public void setTargetNode(int targetNode) {
   this.targetNode = targetNode;
  }

  public V getWeight() {
   return weight;
  }

  public void setWeight(V weight) {
   this.weight = weight;
  }

  @Override
  public int hashCode() {
   final int prime = 31;
   int result = 1;
   result = prime * result + sourceNode;
   result = prime * result + targetNode;
   return result;
  }

  @Override
  public boolean equals(Object obj) {
   if (this == obj)
    return true;
   if (obj == null)
    return false;
   if (getClass() != obj.getClass())
    return false;
   Edge other = (Edge) obj;
   if (sourceNode != other.sourceNode)
    return false;
   if (targetNode != other.targetNode)
    return false;
   return true;
  }
  

 }
 static abstract class Graph<U, V> {

  protected int numOfNodes;
  protected Node<U, V>[] nodes;

  public Graph(int numOfNodes) {
   this.numOfNodes = numOfNodes;
  }

  public void setNode(Node<U, V> node) {
   int nameOfNode = node.getNameOfNode();
   nodes[nameOfNode] = node;
  }

  public int getNumOfNodes() {
   return numOfNodes;
  }

  public void setNumOfNodes(int numOfNodes) {
   this.numOfNodes = numOfNodes;
  }

  public Node<U, V>[] getNodes() {
   return nodes;
  }

 }
 
 static class DirectedGraph<U,V> extends Graph<U, V>{

  public DirectedGraph(int numOfNodes) {
   super(numOfNodes);
   super.nodes=new Node[numOfNodes];
   // TODO Auto-generated constructor stub
  }
  
  public void removeNode(Node<U,V> node){
   for(Edge<V> edge :node.getEdges()){
    int targetNode=edge.getTargetNode();
    this.removeEdge(new EdgeImpl<V>(targetNode,node.getNameOfNode()), this.nodes[targetNode]);
   }
   node.setEdges(new LinkedList<Edge<V>>());
  }
  //Remove the edge Node node, but only this direction
  private void removeEdge(Edge<V> edge, Node<U, V> node){
   node.removeEdge(edge);
  }

 }
 
 static class NodeImpl<U,V> extends Node<U,V>{

  public NodeImpl(int nameOfNode) {
   super(nameOfNode);
   // TODO Auto-generated constructor stub
  }


 }
 
 static class EdgeImpl<V>  extends Edge<V>{

  public EdgeImpl(int sourceNode, int targetNode, V weight) {
   super(sourceNode, targetNode, weight);
   // TODO Auto-generated constructor stub
  }

  public EdgeImpl(int sourceNode, int targetNode) {
   // TODO Auto-generated constructor stub
   super(sourceNode,targetNode);
  }

 }



}