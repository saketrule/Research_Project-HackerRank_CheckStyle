import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;
public class Solution {

 
 public static void main (String ar[]) {
  
  
  Scanner scanner = new Scanner(System.in);
  int numberOfTestCases = scanner.nextInt();
  for(int index = 0; index< numberOfTestCases; index++){ 
   int numberOfVerticies = scanner.nextInt();
   ArrayDeque<vertex> queue = new ArrayDeque<vertex>();
   HashSet<vertex> backLog = new HashSet<vertex>();
   
   int numberOfBannedEdges = scanner.nextInt();
   vertex[] verticies = new vertex[numberOfVerticies];
   //System.out.println("1: "+new java.util.Date().toString());
   for(int vertexIndex =0; vertexIndex<numberOfVerticies ; vertexIndex++) {
    verticies[vertexIndex] = new vertex();
    verticies[vertexIndex].bannedVertexes = new HashSet<vertex>();
    verticies[vertexIndex].vertexNumber = vertexIndex;
    backLog.add(verticies[vertexIndex]);
   }
   //System.out.println("2: "+new java.util.Date().toString());
   for(int edgesIndex =0; edgesIndex < numberOfBannedEdges ; edgesIndex++) {
    int vertex1 = scanner.nextInt() -1;
    int vertex2 = scanner.nextInt() -1;
    verticies[vertex1].bannedVertexes.add(verticies[vertex2]);
    verticies[vertex2].bannedVertexes.add(verticies[vertex1]);
   }
   
   int startingVertex = scanner.nextInt();
   queue.add(verticies[startingVertex-1]);
      //vertQueue.add(verticies[startingVertex-1]);
      backLog.remove(verticies[startingVertex-1]);  
      
      //System.out.println("3: "+new java.util.Date().toString());
      
      while (backLog.size()>0) {
       if(queue.isEmpty()) {
        break;
       }
       int currentVertex = queue.pop().vertexNumber;
       verticies[currentVertex].visited = true;
       
       //vertQueue.remove(0);
       HashSet<vertex> visited = new HashSet<vertex>();
       
       for(vertex v : backLog) {       
        
        if( (currentVertex!=v.vertexNumber) && (!verticies[currentVertex].bannedVertexes.contains(v))) {
      v.shortestDistance = verticies[currentVertex].shortestDistance+1;
      v.visited = true;
      queue.add(v);
      visited.add(v);
     }
       }
       
       backLog.removeAll(visited);
       backLog.remove(verticies[currentVertex]);
       //System.out.println("BackLog Size: " + backLog.size() + "number of total verticies: "+ numberOfVerticies);
      }
      //System.out.println("4: "+new java.util.Date().toString());
   StringBuilder op = new StringBuilder();
   for(int vertexIndex =0; vertexIndex<numberOfVerticies ; vertexIndex++) {
    if(vertexIndex!=(startingVertex-1))
    op = op.append(" "+ verticies[vertexIndex].shortestDistance);
    
   }

   System.out.println(op.toString().trim());
  }
  
 }
 
}


class vertex {
 
 boolean visited;
 int shortestDistance;
 int Parent;
 int vertexNumber;
 HashSet<vertex> bannedVertexes;
 
 @Override 
 public int hashCode() {
  return this.vertexNumber;
 }
 @Override
 public boolean equals(Object o) {
 if(o == this) {
  return true;
 }
 if(!(o  instanceof vertex)) {
  return false;
 }
 if(this.vertexNumber == ((vertex)o).vertexNumber) {
  return true;
 } else {
  return false;
 }
 
 }
}