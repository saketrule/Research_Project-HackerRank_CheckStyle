import java.util.*;
public class JeanieRoute{
/*
10 4
3 6 10 9
1 2 1
1 3 1
1 4 1
3 6 1
6 10 1
3 5 1
5 7 1
7 8 1
8 9 1
*/
 public static class Node implements Comparable<Node>{
  static ArrayList<Node> created = new ArrayList<Node>();
  ArrayList<Edge> adj = new ArrayList<Edge>();
  boolean letter;
  boolean visited;
  int distance;
  int id;
  public Node(int id){
   this.id = id;
   letter = false;
   visited = false;
   distance = Integer.MAX_VALUE;
   created.add(this);
  }
  public static void reset(){
   for(Node n: created){
    n.visited = false;
    n.distance = Integer.MAX_VALUE;
   }
  }
  public String toString(){
   return Integer.toString(id);
  }
  public int compareTo(Node argument){
   if(this.distance < argument.distance)
    return -1;
   return 1;
  }
 }
 public static class Edge{
  Node src, dest;
  int weight;
  Edge(Node src, Node dest, int weight){
   this.src = src;
   this.dest = dest;
   this.weight = weight;
  }
 }
 public static void addEdge(Node src, Node dest, int weight){
  src.adj.add(new Edge(src,dest,weight));                
  dest.adj.add(new Edge(dest,src,weight));
 }
 public static void bfs(Node start, Stack<Node> nodeStack){
  Queue<Node> queue = new LinkedList<Node>();
  queue.add(start);
  while(!queue.isEmpty()){
   Node focus = queue.poll();
   nodeStack.add(focus);   //add to the nodeStack
   focus.visited = true;
   for(Edge e: focus.adj){
    if(!e.dest.visited)
     queue.add(e.dest);
   }
  }
 }
 public static void dfs(Node start, Stack<Node> nodeStack){
  if(!start.visited){
   nodeStack.push(start);
   start.visited = true;
   for(Edge e: start.adj)
    dfs(e.dest, nodeStack);
  }
 }
 public static Node getEnd(Node start){ //returns a node farthest away
  Stack<Node> nodeStack = new Stack<Node>();
  bfs(start,nodeStack);
  //System.out.println("End node stack: "+nodeStack.toString());
  while(!nodeStack.peek().letter)
   nodeStack.pop();
  if(!nodeStack.isEmpty())
   return nodeStack.pop();
  return null;
 }
 public static Node getRoot(Node start){
  Stack<Node> nodeStack = new Stack<Node>();
  dfs(start,nodeStack);
  //System.out.println("Root Search Stack: "+nodeStack.toString());
  while(!nodeStack.peek().letter)
   nodeStack.pop();
  if(!nodeStack.isEmpty())
   return nodeStack.pop();
  return null;
 }
 public static boolean trim(Node focus){
  if(focus.visited)
   return false;
  focus.visited = true;
  boolean foundLetter = focus.letter;
  for(int i = 0; i < focus.adj.size(); i++){
   Edge e = focus.adj.get(i);
   if(trim(e.dest))
    foundLetter = true;
  }
  if(!foundLetter){
   focus.distance = -2;
   //System.out.println("leaf found at: "+focus.id);
  }
  return foundLetter;
 }
 public static int getWeight(Node root){
  int totalWeight = 0;
  root.visited = true;
  for(Edge e: root.adj)
   if(!e.dest.visited)
    totalWeight += getWeight(e.dest) + e.weight;
  return totalWeight;
 }
 public static int dijkstra(Node start, Node end){
  PriorityQueue<Node> queue = new PriorityQueue<Node>();
  queue.add(start);
  start.distance = 0;
  while(!queue.isEmpty()){
   Node focus = queue.poll();
   focus.visited = true;
   if(focus == end)
    return end.distance;
   for(Edge e: focus.adj){
    if(focus.distance + e.weight < e.dest.distance)
     e.dest.distance = focus.distance + e.weight;
    if(!e.dest.visited)
     queue.add(e.dest);
   }
  }
  return -1;
 }
 public static void main(String[] args){
  Scanner sc = new Scanner(System.in);
  int N = sc.nextInt();
  int L = sc.nextInt();
  Node[] cities = new Node[N];
  for(int i = 0; i < N; i++)
   cities[i] = new Node(i+1);
  for(int i = 0; i < L; i++)
   cities[sc.nextInt()-1].letter = true;
  for(int i = 0; i < N-1; i++){
   int src = sc.nextInt()-1;
   int dest = sc.nextInt()-1;
   int weight = sc.nextInt();
   addEdge(cities[src],cities[dest],weight);
  }
  Node root = getRoot(cities[0]);
  Node.reset();
  trim(root);
  for(int i = 0; i < N; i++){
   if(cities[i].distance == -2)
    continue;
   for(int j = 0; j < cities[i].adj.size(); j++){
    Node neighbor = cities[i].adj.get(j).dest;
    if(neighbor.distance == -2){
     //System.out.println("cut at: "+neighbor.id);
     cities[i].adj.remove(j);
     j--;
    }
   }
  }
  Node.reset();
  Node start = getEnd(root);
  Node.reset();
  Node end = getEnd(start);
  Node.reset();
  //System.out.println(start + " " + end);
  int weight = getWeight(root);
  Node.reset();
  //System.out.println("weight: "+getWeight(root));
  //Node.reset();
  int distance = dijkstra(start,end);
  System.out.println(weight*2 - distance);
 }
}