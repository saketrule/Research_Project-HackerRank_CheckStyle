import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

 Map<Integer, List<Node>> allSets = new HashMap<>(); 
 Map<Integer, Node> allNodes = new HashMap<>();
 int lastSetIndex = 1;
 int lastNodeIndex = 0;
 
 public static void main(String[] args) {
  new Solution().run();
 }

 private void run() {
  Scanner sc = new Scanner(System.in);
  int entries = sc.nextInt();
  sc.nextLine();
  for (int entry = 0; entry < entries; entry++) {
   String line = sc.nextLine();
   String[] items = line.split(" ");
   if (items[0].equals("A")) {
    int number = Integer.valueOf(items[1]);
    createASet(number);
   } else if (items[0].equals("B")) {
    int number = Integer.valueOf(items[1]);
    int otherNumber = Integer.valueOf(items[2]);
    createEdges(number, otherNumber);
   } else if (items[0].equals("C")) {
    int number = Integer.valueOf(items[1]);
    mergeSetsStartingAt(number);
   }
  }
  colorNodes();
  int countTrue = 0;
  int countFalse = 0;
  int countNull = 0;
  Set<Integer> keys = allNodes.keySet();
  for (Integer key : keys) {
   if (allNodes.get(key).group == true) {
    countTrue++;
   } else if (allNodes.get(key).group == false) {
    countFalse++;
   } else {
    countNull++;
   }
  }
  System.out.println(Math.max(countTrue, countFalse) + countNull);
 }

 private void colorNodes() {
  List<Node> nodes = new ArrayList<>(allNodes.values());
  nodes.get(0).group = true;
  for (Node node : nodes) {
   List<Node> adjacents = node.adjacents;
   for (Node adjacent : adjacents) {
    adjacent.changeGroupForParent(node.group);
   }
  }
 }

 private void printStuff() {
  Set<Integer> keySet = allSets.keySet();
  for (Integer key : keySet) {
   System.out.println("Set: " + key);
   List<Node> nodes = allSets.get(key);
   for (Node node : nodes) {
    System.out.println("\tNode " + node.nodeIndex + " from set " + node.setIndex);
   }
  }
 }

 private void createASet(int numberOfNodes) {
  List<Node> set = new ArrayList<Node>();
  int setIndex = lastSetIndex++;
  for (int i = 0; i < numberOfNodes; i++) {
   int nodeIndex = lastNodeIndex++;
   Node node = new Node(nodeIndex, setIndex);
   allNodes.put(nodeIndex, node);
   set.add(node);
  }
  allSets.put(setIndex, set);
 }
 
 private void createEdges(int setIndex, int otherSetIndex) {
  List<Node> nodesFromOrigin = allSets.get(setIndex);
  List<Node> nodesFromDestination = allSets.get(otherSetIndex);
  for (Node node : nodesFromOrigin) {
   for (Node otherNode : nodesFromDestination) {
    node.addEdge(otherNode);
   }
  }
 }
 
 private void mergeSetsStartingAt(int setIndex) {
  Set<Integer> deprecatedSets = new HashSet<>();
  int newSetIndex = lastSetIndex++;
  List<Node> newSet = new ArrayList<Node>();
  for (Node node : allSets.get(setIndex)) {
   node.setIndex = newSetIndex;
   newSet.add(node);
  }
  deprecatedSets.add(setIndex);
  
  for (int i = 0; i < newSet.size(); i++) {
   Node currentNode = newSet.get(i);
   List<Node> adjacentsNodes = currentNode.adjacents;
   for (Node node : adjacentsNodes) {
    deprecatedSets.add(node.setIndex);
    node.setIndex = newSetIndex;
    newSet.add(node);
   }
  }
  allSets.put(newSetIndex, newSet);
  
  for (Integer deprecatedSetsInteger : deprecatedSets) {
   allSets.remove(deprecatedSetsInteger);
  }
 }
 
 class Node {
  List<Node> adjacents = new ArrayList<>();
  int setIndex;
  int nodeIndex;
  public Boolean group;

  public Node(int nodeIndex, int setIndex) {
   this.nodeIndex = nodeIndex;
   this.setIndex = setIndex;
  }

  public void changeGroupForParent(boolean parentGroup) {
   this.group = !parentGroup;
  }

  public void addEdge(Node otherNode) {
   this.adjacents.add(otherNode);
  }
  
  @Override
  public String toString() {
   String result = "Node: " + nodeIndex + "\n";
   for (Node node : adjacents) {
    result += "\t" + node.toString() + "\n";
   }
   return result;
  }
 }
}