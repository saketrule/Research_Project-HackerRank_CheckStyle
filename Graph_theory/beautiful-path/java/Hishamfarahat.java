import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.HashMap;


public class Solution {

 public static void main(String[] args) {
  Scanner in = new Scanner(System.in);
        int t = 1;
        while(t--!=0){
            int n = in.nextInt();
            int m = in.nextInt();
            Graph g = new Graph(n,true);
            g.readEdges(m, in, true);
            int i = in.nextInt();
            int j = in.nextInt();
            g.Dijkestra(i);
            Node no = g.getNode(j);
            long cost1 = no.cost;
            g.Dijkestra(j);
            no = g.getNode(i);
            long cost2 = no.cost;
            long min = cost1<cost2?cost1:cost2;
            if(min==Long.MAX_VALUE)
                System.out.print("-1 ");
            else
                System.out.println(min);
        }
 }
}

class Edge{
 Node i;
 Node j;
 int c;
 public Edge(Node i,Node j,int c){
  this.i =i;
  this.j =j;
  this.c=c;
  i.edges.add(this);
  j.edges.add(this);
  
 }
 public Node getOtherNode(Node u){
  if(i.id == u.id)
   return j;
  return i;
 }

}
class Node implements Comparable<Node>{
 int id;
 LinkedList<Edge> edges;
 boolean visited;
 Edge prev;
 long cost;
 
 public Node(int i){
  id =i;
  edges = new LinkedList<Edge>();
 }

 @Override
 public int compareTo(Node o) {
  return Long.compare(cost, o.cost);
 }
}

class Graph {
 int n;
 int m;
 int a[][];
 HashMap<Integer,Node> nodes;
 public Graph(int n) {
  this.n = n;
  a = new int[n][n];
 }
 public Graph(int n,boolean b) {
  this.n = n;
  nodes =new  HashMap<Integer,Node>();
  for(int i=0;i<n;i++)
   nodes.put(i+1,new Node(i+1));
 }
 public Node getNode(int i){
return nodes.get(i);
    }
 public void readEdges(int m, Scanner in,boolean b) {
  while (m-- != 0) {
   int i = in.nextInt();
   int j = in.nextInt();
   int c = in.nextInt();
   new Edge(getNode(i),getNode(j),c);
  }
 }
 public void readEdges(int m, Scanner in) {
  while (m-- != 0) {
   int i = in.nextInt();
   int j = in.nextInt();
   int c = in.nextInt();
   a[i][j] = c;
  }
 }
 
 public void Dijkestra(int source){
  Node s = getNode(source);
  PriorityQueue<Node> Q = new PriorityQueue<Node>();
  
  for(Node n:nodes.values()){
   n.cost = Long.MAX_VALUE;
   n.prev = null;
   n.visited = false;
   //Q.add(n);
   
  }
  s.cost = 0;
  Q.add(s);
  while(!Q.isEmpty()){
   Node u = Q.poll();
   for(Edge e: u.edges){
    Node v = e.getOtherNode(u);
              //  if(v.visited)
                //   continue;
                long newCost = u.cost|e.c;//u.cost > e.c? u.cost:e.c;
    if(newCost < v.cost){
     v.cost =newCost;
                    if(v.prev!=null)
                        Q.remove(v);
                    v.prev = e;

                    Q.add(v);
    }

   }
           //  u.visited=true;
  }
 }

}