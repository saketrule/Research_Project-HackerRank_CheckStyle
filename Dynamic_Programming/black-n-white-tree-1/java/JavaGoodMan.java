import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

  Scanner in = new Scanner(System.in);
  
  String inputStr = in.nextLine();
  
  String[] strArr = inputStr.split(" ");
  
  int numberOfEdge = Integer.valueOf(strArr[1]).intValue();
  
  SimpleGraph obj = new SimpleGraph (Integer.valueOf (strArr[0]).intValue(), numberOfEdge);
  
  for (int k=0; k < numberOfEdge; k++)
  {
   String edge = in.nextLine();
   
   String[] edgeStr = edge.split(" ");
   
   obj.addEdge (Integer.valueOf(edgeStr[0]).intValue(), Integer.valueOf(edgeStr[1]).intValue());   
  }
  
  obj.traverseNodes();  
  
  obj.calculateWhiteNodeCount();
  
  obj.findDisjointNodes ();
  
  obj.findRealDisjointNodes ();
  
  obj.calculateWhiteNodeCount();
  
  obj.printOutput();
  
  obj.printGraph();
 }
}

class SimpleGraph
{
 private Node[] node;
 
 private int numberOfnodes;
 
 private int blackNodeCnt;
 
 private int actualNodeCnt;
 
 private List<Edge> edgeList;
 
 private String outputStr;
 
 private int whiteNodeCnt;
 
 int numberOfEdgeAdded;
 
 SimpleGraph (int nodes, int edges)
 {
  actualNodeCnt = nodes;
  
  blackNodeCnt = 0;
  
  numberOfnodes = nodes;
  
  edgeList = new ArrayList<Edge> ();
  
  outputStr = "";
  
  whiteNodeCnt = 0;
  
  node = new Node[nodes];
  
  numberOfEdgeAdded = 0;
  
  for(int i=0; i < nodes; i++)
  {
   node[i] = new Node(i+1);
  }
 }

 
 public void printOutput ()
 {
  int blackNode = 0;
  
  int whiteNode = 0;
  
  for (int i=0; i < numberOfnodes; i++)
  {
   Node nodeObj = node[i];
   
   if (nodeObj.colorWhite == true)
   {
    whiteNode ++;
   }
   else
   {
    blackNode ++;
   }   
  }
  
  int output = 0;
  
  if (whiteNode >= blackNode )
  {
   output = whiteNode - blackNode;
  }
  else
  {
   output = blackNode - whiteNode;
  }
  
  System.out.println (output + " " + numberOfEdgeAdded); 
  
  System.out.println (outputStr);
 }
 
 public void traverseNodes ()
 {
  for (int i=0; i < numberOfnodes; i++)
  {
   Node tmpNode = node[i];
   
   Edge tmpEdge = tmpNode.firstEdge;
   
   if (tmpEdge == null)
   {
    actualNodeCnt --;
   }
  }  
 }
 
 public void findPlacetoAttach ()
 {
  
 }
 
 public void findRealDisjointNodes ()
 {
  int initialCnt = 0;
  
  for (int i=0; i < numberOfnodes; i++)
  {
   Node tmpNode = node[i];
   
   int connectedNodes = getNumberOfConnectedNodes (tmpNode);
   
   if ( i > 0)
   {
    if (initialCnt != connectedNodes)
    {
     tmpNode.needAttach = true;
     
     // attaching the node
     
     Node nodeObj = null;
     
     if (whiteNodeCnt >= blackNodeCnt )
     {
      nodeObj = findNodeToAttach (true);      
     }
     else
     {
      nodeObj = findNodeToAttach (false);      
     }
     
     numberOfEdgeAdded ++;
     
     addEdge(nodeObj.name, tmpNode.name);
     
     if (outputStr.length() > 0)
     {
      outputStr = outputStr + "\n" + nodeObj.name + " " + tmpNode.name; 
     }
     else
     {
      outputStr = nodeObj.name + " " + tmpNode.name;
     }
     
    }
   }
   
   initialCnt = connectedNodes;
   
//   System.out.println (" Connected Nodes of Node : " + tmpNode.name + " is => " + connectedNodes);   
  }
 }
 
 public Node findNodeToAttach (boolean colorWhite)
 {
  Node tmpNode = null;
  
  for (int i=0; i < numberOfnodes; i++)
  {
   tmpNode = node[i];
   
   if (tmpNode.colorWhite == colorWhite)
   {
    break;
   }
  }
  
  return tmpNode;
 }
 
 public void calculateWhiteNodeCount ()
 {
  for (int i=0; i < numberOfnodes; i++)
  {
   Node tmpNode = node[i];
   
   if (tmpNode.colorWhite == true)
   {
    whiteNodeCnt++;
   }
  }
 }
 
 public int getNumberOfConnectedNodes (Node nodeObj)
 {
  int cnt = nodeObj.nodeConnectedCnt;
  
  if ( nodeObj.firstEdge != null)
  {
   cnt = cnt + node[nodeObj.firstEdge.edgeName-1].nodeConnectedCnt;
  }
  
  return cnt;
 }
 
 public void findDisjointNodes ()
 {
  for (int i=0; i < numberOfnodes; i++)
  {
   findDisjointNodes (node[i]);   
  }
  
  for (int i=0; i < numberOfnodes; i++)
  {
//   System.out.println(" NEW Connected Node " + node[i].name + ": " + node[i].nodeConnectedCnt);   
  }
 }
 
 public void findDisjointNodes (Node nodeObj)
 {
  for (int i=0; i < numberOfnodes; i++)
  {
   Node objTmp = node[i];
   
   Edge edgeObj = objTmp.firstEdge;
   
   while ( edgeObj != null)
   {
    if (edgeObj.edgeName == nodeObj.name)
    {     
     node[nodeObj.name-1].nodeConnectedCnt++;
    }
    
    edgeObj = edgeObj.next;
   }  
  }
 }
 
 public void printGraph ()
 {
  for (int i=0; i < numberOfnodes; i++)
  {
   Node nodeObj = node[i];
   
   if (nodeObj.colorWhite == true)
   {
//    System.out.println("Node '" + nodeObj.name + "' is White"); 
   }
   else
   {
//    System.out.println("Node '" + nodeObj.name + "' is Black");
   }   
   
   Edge edgeObj = nodeObj.firstEdge;
   
   while ( edgeObj != null)
   {
//    System.out.println("Node " + nodeObj.name + " is connected to " + edgeObj.edgeName);
    
    edgeObj = edgeObj.next;
   }
  }  
 }
 
 public List<Node> getConnected (Node srcNode)
 {
  List<Node> connectedNodes = new ArrayList<Node> ();
  
  for (int i=0; i < numberOfnodes; i++)
  {
   Node tmpNode = node[i];   
   
   Edge tmpEdge = tmpNode.firstEdge;
   
   while ( tmpEdge != null)
   {
    if (tmpEdge.edgeName == srcNode.name)
    {
     connectedNodes.add(tmpNode);
    }
    
    tmpEdge = tmpEdge.next;
   }   
  }
  
  return connectedNodes;
 }
 
 public Edge addEdge(int src, int dest)
 {
  Node tmpNode = node[src-1];
  
  List<Node> tmpNodes = getConnected (node[src-1]);
  
  boolean colorBlackNode = false;
  
  for (int i=0; i < tmpNodes.size(); i++)
  {
   if (tmpNodes.get(i).colorWhite == false)
   {
    colorBlackNode = true;
    
    break;
   }
  }
  
  if (colorBlackNode == false)
  {
   tmpNode.colorWhite = false;
   
   blackNodeCnt ++;
  }
  
  tmpNode.firstEdge = new Edge (dest, tmpNode.firstEdge);
  
  Node destNode = node[dest-1];
  
  if (tmpNode.colorWhite == true)
  {
   destNode.colorWhite = false;
  }
  
  destNode.firstEdge = new Edge (src, destNode.firstEdge);
  
  return tmpNode.firstEdge;
 }
 
 class Node
 { 
  private int name;
  
  Edge firstEdge;
  
  public int nodeConnectedCnt;
  
  boolean colorWhite;
  
  public boolean needAttach;
  
  Node (int nameTmp)
  {
   name = nameTmp;
   
   nodeConnectedCnt = 0;
   
   firstEdge = null;
   
   colorWhite = true;
   
   needAttach = true;
  }
 }
 
 class Edge
 {
  int edgeName;
  
  private Edge next;
  
  Edge (int name, Edge edge)
  {
   edgeName = name;
   
   next = edge;
  }
 }
}