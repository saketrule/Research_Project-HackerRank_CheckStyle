/* Enter your code here. Read input from STDIN. Print output to STDOUT */

import java.util.LinkedList;
import java.util.Scanner;


public class Solution {
 enum NodeState 
 {
  NOTSTARTED,
  INPOGRESS, 
  DONE
  
 }

 public static class Node
 {
  NodeState state = NodeState.NOTSTARTED; 
  LinkedList<Node> adjeicentNodes = new LinkedList<Node>(); 
  boolean cycleNode = false;  
  int town; 
  long paths; 
 }

 public static long MODULO = 1000000000;
 
 
 public long countConnectivity(Node start, Node end)
 {

  LinkedList<Node> path = new LinkedList<Node>();
  Node currentNode = start; 
  currentNode.state = NodeState.INPOGRESS;
   
  while (currentNode.state != NodeState.DONE)
  {
    
   Node nextNode = currentNode.adjeicentNodes.pollFirst();
   if (nextNode == null)
   {
    currentNode.state = NodeState.DONE;
    
    if (currentNode.paths > 0 && currentNode.cycleNode )
    {
     return -1; //cycle on the path, infinite result
    }
    Node oldNode = currentNode; 
    
    if (path.size() > 0 )
    {
     currentNode = path.pop();
     currentNode.paths+= oldNode.paths; 
     continue;
    }else
     break; 
   }

   if (nextNode == end)
   {
    currentNode.paths++;
    currentNode.paths %= MODULO; 
   }
   
   if (nextNode.state == NodeState.DONE)
   {
    currentNode.paths+= nextNode.paths;
    currentNode.paths %= MODULO; 
    
   }else if (nextNode.state == NodeState.INPOGRESS)
   {
    for (Node pathNode : path)
    {
     pathNode.cycleNode = true; 
     if (pathNode == nextNode)
      break; 
    }
    
   }else 
   {
    path.push(currentNode); 
    currentNode = nextNode;
    currentNode.state = NodeState.INPOGRESS;
   }
   
  }
  return start.paths; 
 }
 
 public static void main(String args[])
 {
  Scanner in = new Scanner(System.in);
  int towns = in.nextInt();
  int connections = in.nextInt(); 
  Node testArray[] = new Node[towns];
  
  for (int i = 0; i< towns; ++i)
  {
   testArray[i] = new Node();
   testArray[i].town = i+1; 
  }
  
  for (int i=0; i<connections; ++i)
  {
   int fromTown = in.nextInt(); 
   int toTown = in.nextInt(); 
   testArray[fromTown-1].adjeicentNodes.add(testArray[toTown-1]); 
  }
  
  long pathCount = new Solution().countConnectivity(testArray[0],testArray[towns-1]); 
  
  if (pathCount >= 0)
   System.out.println(pathCount);
  else 
   System.out.println("INFINITE PATHS"); 

 }

}