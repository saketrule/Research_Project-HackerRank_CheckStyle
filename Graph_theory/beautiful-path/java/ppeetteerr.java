import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
            int noVertices = sc.nextInt();
            int noEdges = sc.nextInt();
            Vertex[] vert = readGraph(sc, noVertices, noEdges);
            int startVert = sc.nextInt();
            
            shortestPath(Arrays.asList(vert), vert[startVert]);
            int dist =  vert[sc.nextInt()].dist;   
            
            System.out.println(dist==Integer.MAX_VALUE ? -1 : dist);
    }
    
    public static Vertex[] readGraph(Scanner sc, int noVertices, int noEdges){
  Vertex[] vertices = new Vertex[noVertices+1];
  
  for (int i = 0; i < vertices.length; i++) {
       vertices[i] = new Vertex(i);
      }
  
  for(int i=0; i<noEdges; i++){
   int v1 = sc.nextInt();
   int v2 = sc.nextInt();
   
//   vertices[v1].edges.add(new Edge(vertices[v2]));
//   vertices[v2].edges.add(new Edge(vertices[v1]));
   
   int w = sc.nextInt();
   vertices[v1].edges.add(new Edge(vertices[v2], w));
   vertices[v2].edges.add(new Edge(vertices[v1], w));
  }
  
  
  return vertices;
 }
    
    public static void shortestPath(Collection<Vertex> vertices, Vertex start) {
  for (Vertex vertex : vertices) {
   vertex.dist = Integer.MAX_VALUE;
  }
  
  Comparator<Vertex> comparator = new Comparator<Vertex>() {

   @Override
         public int compare(Vertex o1, Vertex o2) {
    if(o1.dist!=o2.dist) return o1.dist - o2.dist;
    return o1.index - o2.index;
         }
  };
  
  SortedSet<Vertex> notDone = new TreeSet<Vertex>(comparator);
  notDone.addAll(vertices);
  notDone.remove(start);
  
  start.dist = 0;
  Vertex lastAdded = start;

  while (!notDone.isEmpty() && lastAdded.dist != Integer.MAX_VALUE) {
   for (Edge edge : lastAdded.edges) {
    Vertex end = edge.end;
                int or = lastAdded.dist | edge.w;
    if(end.dist > or){
     notDone.remove(end);
     end.dist = or;
     notDone.add(end);
    }
    
   }
   
   lastAdded = notDone.first();
   notDone.remove(lastAdded);
  }
 }
}

class Vertex{
 int index;
// String name;
 Set<Edge> edges = new HashSet<Edge>();
 int dist;
 
 public Vertex(){
 }
 
// public Vertex(String name){
//  this.name = name;
// }
 
 public Vertex(int index){
  this.index = index;
 }
 
 @Override
   public String toString(){
  return Integer.toString(dist);
 }
}

class Edge{
 Vertex end;
 int w;
 
 public Edge(Vertex end){
  this.end = end;
 }
 
 public Edge(Vertex end, int w){
  this.end = end;
  this.w = w;
 }
 
 @Override
   public String toString(){
  return "vertex " + end.index;
 }
}