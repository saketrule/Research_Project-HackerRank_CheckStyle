import java.util.*;

public class Solution {
 
 static class Node<T> {
  T value; 
  List<Node<T>> edges; 
  public Node(T value) {
   this.value = value;
   edges = new ArrayList<Node<T>>(); 
  }
  
  public void addEdge(Node<T> newNode) {
   edges.add(newNode); 
  }
  
  public T getValue() {
   return value; 
  }
  
  public void printEdges() {
   for(Node<T> node : edges) {
    System.out.println(node.getValue() + " " + value);
   }
  }
 }
    public static void main(String[] args) {
        
     Solution solution = new Solution(); 
     Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        int q = in.nextInt(); 
         
        
        solution.lovelyTrip(p, q); 
        
        in.close(); 
    }
    
    private void lovelyTrip(int p, int q) {
     
     int totalNumberOfNodes = 0; 
      
     Map<Integer, Node<Integer>> nodeList = new HashMap<Integer, Node<Integer>>(); 
     
     Node<Integer> node = new Node<Integer>(1);
     totalNumberOfNodes++; 
     nodeList.put(node.getValue(), node); 
     
     for(int i = 0; i < 3; i++) {
      for(int j = 0; j < q; j++) {
       totalNumberOfNodes++; 
       Node<Integer> newNode = new Node<Integer>(totalNumberOfNodes);
       
       
       if (totalNumberOfNodes != (q * 3 + 1)) {
        newNode.addEdge(nodeList.get(totalNumberOfNodes - 1));
       } else {
        newNode.addEdge(nodeList.get(1));
       }
       
       nodeList.put(totalNumberOfNodes, newNode);
      }
     }
     
     System.out.println(totalNumberOfNodes + " " + totalNumberOfNodes); 
     
     for(Integer nodeKey : nodeList.keySet()) {
      nodeList.get(nodeKey).printEdges();
     }
    }
    
    private int getDistance(int nodeI, int nodeJ) {
     return 0; 
    }
}