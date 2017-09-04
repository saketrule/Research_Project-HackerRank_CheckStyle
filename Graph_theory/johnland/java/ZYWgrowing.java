import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args){
  Scanner scan = new Scanner(System.in);
  int N = scan.nextInt();
  int M = scan.nextInt();
  ArrayList<Vertex> allVertex = new ArrayList<Vertex>();
  for(int i=1;i<=N;i++){
   Vertex v = new Vertex(i);
   allVertex.add(v);
  }
  for(int i=0;i<M;i++){
   int v1 = scan.nextInt();
   int v2 = scan.nextInt();
   int distance = 1<<scan.nextInt();
   Edge edge = new Edge(v1,v2,distance);
   allVertex.get(v1-1).addAdjacentVertex(edge, allVertex.get(v2-1));
   allVertex.get(v2-1).addAdjacentVertex(edge, allVertex.get(v1-1));
  }
  scan.close();
  int sumResult = 0;
  for(int i=1;i<N;i++){
   sumResult+=sumShortestPath(allVertex, i);
  }
  System.out.println(Integer.toBinaryString(sumResult));
    }
 
 public static int sumShortestPath(ArrayList<Vertex> allVertex, int source){
  int sumSource = 0;
  BinaryMinHeap minHeap = new BinaryMinHeap();
  HashMap<Integer,Integer> distance = new HashMap<>();
  for(Vertex vertex : allVertex){
            minHeap.add(vertex.id,Integer.MAX_VALUE);
        }
  minHeap.decrease(source, 0);
  distance.put(source, 0);
  while(!minHeap.empty()){
            BinaryMinHeap.Node heapNode = minHeap.extractMinNode();
            int current = heapNode.v;
            distance.put(current, heapNode.distance);
            for(Edge edge : allVertex.get(current-1).edges){
                int adjacent = edge.v1==current?edge.v2:edge.v1;
                if(!minHeap.containsData(adjacent)){
                    continue;
                }
                int newDistance = distance.get(current) + edge.distance;
                if(minHeap.getDistance(adjacent) > newDistance) {
                    minHeap.decrease(adjacent, newDistance);
                }
            }
        }
  //Get sum
  for(int v: distance.keySet()){
   if(v>source)sumSource+=distance.get(v);
  }
  return sumSource;
 }
}
class Edge{
 int v1,v2,distance;
 public Edge(int v1, int v2, int distance){
  this.v1 = v1;
  this.v2 = v2;
  this.distance = distance;
 }
}
class Vertex{
 int id;
 ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Vertex> adjacentVertex = new ArrayList<Vertex>();
    Vertex(int id){
        this.id = id;
    }
    
    public void addAdjacentVertex(Edge e, Vertex v){
        edges.add(e);
        adjacentVertex.add(v);
    }
}
class BinaryMinHeap {

    private ArrayList<Node> allNodes = new ArrayList<>();
    private HashMap<Integer,Integer> nodePosition = new HashMap<>();
        
    public class Node {
        int distance;
        int v;
    }
    public boolean containsData(int v){
        return nodePosition.containsKey(v);
    }

    /**
     * Add v and its distance to they heap
     */
    public void add(int v, int distance) {
        Node node = new Node();
        node.distance = distance;
        node.v = v;
        allNodes.add(node);
        int size = allNodes.size();
        int current = size - 1;
        int parentIndex = (current - 1) / 2;
        nodePosition.put(node.v, current);

        while (parentIndex >= 0) {
            Node parentNode = allNodes.get(parentIndex);
            Node currentNode = allNodes.get(current);
            if (parentNode.distance > currentNode.distance) {
                swap(parentNode,currentNode);
                updatePositionMap(parentNode.v,currentNode.v,parentIndex,current);
                current = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Get the heap min without extracting the v
     */
    public int min(){
        return allNodes.get(0).v;
    }

    /**
     * Checks with heap is empty or not
     */
    public boolean empty(){
        return allNodes.size() == 0;
    }

    /**
     * Decreases the distance of given v to newWeight
     */
    public void decrease(int v, int newD){
        Integer position = nodePosition.get(v);
        allNodes.get(position).distance = newD;
        int parent = (position -1 )/2;
        while(parent >= 0){
            if(allNodes.get(parent).distance > allNodes.get(position).distance){
                swap(allNodes.get(parent), allNodes.get(position));
                updatePositionMap(allNodes.get(parent).v,allNodes.get(position).v,parent,position);
                position = parent;
                parent = (parent-1)/2;
            }else{
                break;
            }
        }
    }

    /**
     * Get the distance of given v
     */
    public int getDistance(int v) {
        Integer position = nodePosition.get(v);
        if( position == null ) {
            return -1;
        } else {
            return allNodes.get(position).distance;
        }
    }

    /**
     * Returns the min node of the heap
     */
    public Node extractMinNode() {
        int size = allNodes.size() -1;
        Node minNode = new Node();
        minNode.v = allNodes.get(0).v;
        minNode.distance = allNodes.get(0).distance;

        int lastNodeWeight = allNodes.get(size).distance;
        allNodes.get(0).distance = lastNodeWeight;
        allNodes.get(0).v = allNodes.get(size).v;
        nodePosition.remove(minNode.v);
        nodePosition.remove(allNodes.get(0));
        nodePosition.put(allNodes.get(0).v, 0);
        allNodes.remove(size);

        int currentIndex = 0;
        size--;
        while(true){
            int left = 2*currentIndex + 1;
            int right = 2*currentIndex + 2;
            if(left > size){
                break;
            }
            if(right > size){
                right = left;
            }
            int smallerIndex = allNodes.get(left).distance <= allNodes.get(right).distance ? left : right;
            if(allNodes.get(currentIndex).distance > allNodes.get(smallerIndex).distance){
                swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
                updatePositionMap(allNodes.get(currentIndex).v,allNodes.get(smallerIndex).v,currentIndex,smallerIndex);
                currentIndex = smallerIndex;
            }else{
                break;
            }
        }
        return minNode;
    }
    /**
     * Extract min value v from the heap
     */
    public int extractMin(){
        Node node = extractMinNode();
        return node.v;
    }

    private void swap(Node node1,Node node2){
        int distance = node1.distance;
        int data = node1.v;
        
        node1.v = node2.v;
        node1.distance = node2.distance;
        
        node2.v = data;
        node2.distance = distance;
    }

    private void updatePositionMap(int data1, int data2, int pos1, int pos2){
        nodePosition.remove(data1);
        nodePosition.remove(data2);
        nodePosition.put(data1, pos1);
        nodePosition.put(data2, pos2);
    }
    
    public void printHeap(){
        for(Node n : allNodes){
            System.out.println(n.distance + " " + n.v);
        }
    }
}