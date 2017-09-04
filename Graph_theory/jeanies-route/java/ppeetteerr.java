import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

 public static void main(String[] args) throws Exception {
//  BufferedReader br = new BufferedReader(new FileReader("in.txt" ));
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st = new StringTokenizer(br.readLine());
  int noVertices = Integer.parseInt(st.nextToken());
//  int noTargets = Integer.parseInt(st.nextToken());
  Set<Integer> targets = new HashSet<Integer>();
  st = new StringTokenizer(br.readLine());
  
  while(st.hasMoreTokens()){
   targets.add(Integer.parseInt(st.nextToken()));
  }

  List<Vertex> vertices = readGraph(br, noVertices, noVertices-1);
  
  removeLeafs(vertices, targets);
        
        //System.err.println(vertices);
  for(int i=0; i< vertices.size(); i++){
   if(!vertices.get(i).edges.isEmpty()){
    vertexMark(vertices.get(i));
    break;
   }
  }
  
  int totalW=0;
  int maxDiam=0;
  
  for (Vertex v : vertices) {
       if(v.edges.size()!=0){
        maxDiam = Math.max(maxDiam, v.first+v.second);
        for (Edge edge : v.edges) {
             totalW+=edge.w;
            }
       }
      }
  
  System.out.println((totalW-maxDiam));

 }
   
 // return first from vertex
 public static int vertexMark(Vertex vertex) {
  // define property
  // vertex.dist=1;
  for (Edge edge : vertex.edges) {
   if (!edge.end.equals(vertex.parent)) {
    edge.end.parent = vertex;
    // update property
    int childDist = vertexMark(edge.end)+edge.w;
    if(childDist>vertex.first){
                    vertex.second = vertex.first;
     vertex.first = childDist;
    } else if(childDist>=vertex.second){
     vertex.second = childDist;
    }
   }
  }

  return vertex.first;
 }
 
 public static void removeLeafs(List<Vertex> vertices, Set<Integer> targets){
  Deque<Vertex> q = new LinkedList<Vertex>();
  for (Vertex vertex : vertices) {
       if(vertex.edges.size()==1 && !targets.contains(vertex.index)) {
        q.addLast(vertex);
       }
      }
  
  while(!q.isEmpty()){
   Vertex v = q.pollFirst();
   if(v.edges.size()==1 && !targets.contains(v.index)){
    for (Edge e : v.edges) {
     e.end.edges.remove(new Edge(v, e.w));
     q.addLast(e.end);
            }
    v.edges.clear();
   }
  }
 }
 
  public static List<Vertex> readGraph(BufferedReader br, int noVertices, int noEdges) throws Exception{
  List<Vertex> vertices = new ArrayList<Vertex>();
  
  for (int i = 0; i <= noVertices; i++) {
       vertices.add(new Vertex(i));
      }
  
  for(int i=0; i<noEdges; i++){
   StringTokenizer st = new StringTokenizer(br.readLine());
   int v1 = Integer.parseInt(st.nextToken());
   int v2 = Integer.parseInt(st.nextToken());
   
//   vertices.get(v1).edges.add(new Edge(vertices.get(v2)));
//   vertices.get(v2).edges.add(new Edge(vertices.get(v1)));
   
   int w = Integer.parseInt(st.nextToken());
   vertices.get(v1).edges.add(new Edge(vertices.get(v2), w));
   vertices.get(v2).edges.add(new Edge(vertices.get(v1), w));
  }
  
  return vertices;
 }
}

class Vertex {
 int index;
// String name;
 Set<Edge> edges = new HashSet<Edge>();
 int dist;
 //for trees
 Vertex parent;
 int first;
 int second;
 int diam;
 
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
  return index+": "+edges;
 }
 
}

class Edge{
 Vertex start;
 Vertex end;
 int w;
 
 public Edge(Vertex end){
  this.end = end;
 }
 
 public Edge(Vertex start, Vertex end){
  this.start = start;
  this.end = end;
 }
 
 public Edge(Vertex end, int w){
  this.end = end;
  this.w = w;
 }
 
 public Edge(Vertex start, Vertex end, int w){
  this.start = start;
  this.end = end;
  this.w = w;
 }
 
     @Override
   public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((end == null) ? 0 : end.hashCode());
    result = prime * result + w;
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
    if (end == null) {
     if (other.end != null)
      return false;
    } else if (!end.equals(other.end))
     return false;
    if (w != other.w)
     return false;
    return true;
   }
    
 @Override
   public String toString(){
  //return "join "+start.index+" and "+end.index+", w="+w;
       return Integer.toString(end.index);
 }
}