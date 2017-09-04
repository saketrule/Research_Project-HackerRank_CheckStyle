import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
  int size =in.nextInt();
  int[][] idnodes = new int[size][size+2];
  in.nextLine();
  int setId = 1;
  List<Node> nodeList = new ArrayList<Node>();
  for(int i=0 ; i< size; i++){
   String str = in.nextLine();
   String action[] = str.split(" ");
   if(!(action.length > 1))
    continue;
   
   if(action[0].trim().equalsIgnoreCase("A")){
    addEntry(Integer.parseInt(action[1]), nodeList, setId);
    setId++;
   }else if(action[0].trim().equalsIgnoreCase("B")){
    addEdges(Integer.parseInt(action[1]), Integer.parseInt(action[2]), nodeList);
   }else{
   addCopyAndEmpty(Integer.parseInt(action[1]), nodeList, setId);
    setId++;
   }
   
   
  }
  
  /*for(Node node : nodeList){
   System.out.println(node);
  }*/
  
  printAnswer(nodeList);
 }
 
 public  static void printAnswer(List<Node> nodeList){
  
  int answer = 0, id = 0;
  id = getMinimumCoveredNode(nodeList);
  while(id != 0){
   //System.out.println("Minimum"+id);   
   
   if(id == 0)
    break;
   answer++;
   
   Node node = getNodeByID(id, nodeList);
   node.setInGraph(false);
   
   for(int j =0 ; j < node.getNeighbour().size(); j++){
    int nid = node.getNeighbour().get(j);
    Node nnode = getNodeByID(nid, nodeList);
    nnode.setInGraph(false);
    
    for(int i = 0; i < nnode.getNeighbour().size(); i++){
     
     Node nnnode = getNodeByID(nnode.getNeighbour().get(i) , nodeList);
     if(nnnode.isInGraph()){
      nnnode.removeNeighbour(nnode.getId());
     }
    }
    
   }
   
   id = getMinimumCoveredNode(nodeList);
  }
  
  System.out.println(answer);
 }
 
 public static void addEntry(int vertexsCount, List<Node> nodeList, int setId){
 
   for(int i =0; i< vertexsCount; i++){
    nodeList.add(new Node((nodeList.size()+1),setId));
   }
 }
 
 public static void addEdges(int startSetId, int endSetId,  List<Node> nodeList){
  
  List<Integer> startNodeList = getSetNodes(startSetId, nodeList);
  List<Integer> endNodeList = getSetNodes(endSetId, nodeList);
  
  for(Node node : nodeList){
   if(startNodeList.contains(node.getId())){
    for(int id : endNodeList){
     node.addNeighbour(id);
    }
   }
   
   if(endNodeList.contains(node.getId())){
    for(int id : startNodeList){
     node.addNeighbour(id);
    }
   }
  }
 }
 
 public static List<Integer> getSetNodes(int setId,  List<Node> nodeList){
  List<Integer> setNodeList = new ArrayList<Integer>();
  for(Node node : nodeList){
   
   if(node.getSetId() == setId){
    setNodeList.add(node.getId());
   }
  }
  
  return setNodeList;
 }
 public static void addCopyAndEmpty(int setIdToCopy,  List<Node> nodeList, int setId){
  
  List<Integer> setNodeList = getSetNodes(setIdToCopy, nodeList);
  Map<Integer, Integer> oldCopy = new HashMap<Integer, Integer>();
  for(int i = 0 ; i< nodeList.size(); i++){
   Node node = nodeList.get(i);
   
    if(setNodeList.contains(node.getId())){
    
     node.setInGraph(false);
     Node copyNode = new Node((nodeList.size()+1), setId); 
     nodeList.add(copyNode);
     oldCopy.put(node.getId(), copyNode.getId());
     for(int id : node.getNeighbour()){
      Node oldNeighNode = getNodeByID(id, nodeList);
      oldNeighNode.setInGraph(false);
      Node neighNode;
      if(oldCopy.get(id) == null){
       
       neighNode = new Node((nodeList.size()+1), setId);
       oldCopy.put(id, neighNode.getId());
       nodeList.add(neighNode);
       
      }else{       
       //neighNode = getNodeByID(oldCopy.get(id), nodeList);       
      }
      //neighNode.addNeighbour(copyNode.getId());
      //copyNode.addNeighbour(neighNode.getId());
     }
   
    }   
  }
  
  for(int id : oldCopy.keySet()){
   Node oldNode = getNodeByID(id, nodeList);
   oldNode.setInGraph(false);
   Node newNode = getNodeByID(oldCopy.get(id), nodeList);
   for(int nid : oldNode.getNeighbour()){
    newNode.addNeighbour(oldCopy.get(nid));
   }
  }
  
 }
 
 public static Node getNodeByID(int id, List<Node> nodeList){
  for(Node node : nodeList){
   if( node.getId() == id)
    return node;
  }
  
  return null;
 }
 
 public static int getMinimumCoveredNode(List<Node> nodeList){
  int id = 0, count = nodeList.size();
  for(Node node : nodeList){
   
   if(node.getNeighbour().size() < count && node.isInGraph()){
    count = node.getNeighbour().size();
    id = node.getId();
   }
  }
  return id;
 }
 
}
class Node {
 
 private int id;
 
 private int setId;
 
 private boolean inGraph;
 
 private List<Integer> neighbour;
 
 public Node(int id, int setId) {
  this.id = id;
  this.setId = setId;
  inGraph = true;
  neighbour = new ArrayList<Integer>();
 }

 public int getId() {
  return id;
 }
 
 public int getSetId() {
  return setId;
 }
 
 public boolean isInGraph() {
  return inGraph;
 } 
 
 public void setInGraph(boolean inGraph) {
  this.inGraph = inGraph;
 }

 public List<Integer> getNeighbour() {
  return neighbour;
 }

 public boolean isNeighbour(int id){
  
  return neighbour.contains(id); 
  
 }
 
 public void addNeighbour(int id){
  neighbour.add(id);
 }
 public void removeNeighbour(Integer id){
  neighbour.remove(id);
 }
 
 @Override
 public String toString() {
  // TODO Auto-generated method stub
  StringBuilder st = new StringBuilder();
  st.append("Node Id "+id+", SetID "+setId+", INgraph "+inGraph+", Neighbour :");
  for(int id: neighbour){
   st.append(id+" ");
  }
  return st.toString();
 }
 
}