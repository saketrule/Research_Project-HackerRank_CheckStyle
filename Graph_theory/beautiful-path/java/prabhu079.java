import java.util.Scanner;

public class Solution {

 static Graph graph;
 static int src;
 static int dest;
 static int dist[];

 public static void main(String[] args) {
  Scanner sc = new Scanner(System.in);
  int n = sc.nextInt();
  dist = new int[n + 1];
  for (int i = 0; i <= n; i++) {
   dist[i] = Integer.MAX_VALUE;
  }
  int m = sc.nextInt();

  Edges[] edge = new Edges[2*m];
  for (int i = 0; i < 2*m; i++) {
   int u=sc.nextInt();
   int v=sc.nextInt();
   int d=sc.nextInt();
   edge[i] = new Edges(u, v, d);
   i++;
   edge[i] = new Edges(v, u, d);
   
   
  }
  src = sc.nextInt();
  dest = sc.nextInt();
  dist[src] = 0;
  graph = new Graph(edge, n, 2*m);
  process();
  if(dist[dest]==Integer.MAX_VALUE || dist[dest]==0 )
  {
   System.out.println("-1");
  }
  else
  {
   System.out.println(dist[dest]);
  }
  sc.close();
 }

 public static  void process() {
  for (int i = 0; i < graph.V; i++) {
   for (int j = 0; j < graph.E; j++) {
    int u = graph.edges[j].src;
    int v = graph.edges[j].dest;
    int weight = graph.edges[j].weight;

    if (dist[u] != Integer.MAX_VALUE) {
     int res = dist[u] | weight;
     if (res < dist[v]) {
      dist[v] = res;
     }
    }
   }
  }
 }

}

class Stack {
 int[] arr;
 int capacity;

 public Stack(int capacity) {
  this.capacity = capacity;
  arr = new int[capacity];
 }

 public void push(int ele) {
  if (arr[0] == capacity) {
   capacity = 2 * capacity;
   int[] temp = new int[capacity];
   System.arraycopy(arr, 0, temp, 0, arr.length);
   arr = temp;
   temp = null;
  }
  arr[0]++;
  arr[arr[0]] = ele;

 }

 public int pop() {
  if (arr[0] == 0) {
   System.out.println("Stack is Empty!!!!!!!!!!!!!!");
   return 0;
  } else {
   int ele = arr[arr[0]];
   arr[0]--;
   return ele;
  }
 }

 public boolean isEmpty() {
  if (arr[0] == 0) {
   return true;
  } else {
   return false;
  }
 }

}

class Queue {
 Node first;
 Node last;

 public void insert(int ele) {
  Node newNode = new Node(ele, null);
  if (null == first) {
   first = newNode;
   last = first;
  } else {
   last.next = newNode;
   last = last.next;
  }
 }

 public int delete() {
  if (null == first) {
   System.out.println("Queue is Empty!!!!!!!!!!!!!");
   return 0;
  } else {
   int ele = first.data;
   first = first.next;
   return ele;
  }
 }

 public boolean isEmpty() {
  return (null == first) ? true : false;
 }

}

class Node {
 int data;
 Node next;

 public Node(int data, Node next) {
  this.data = data;
  this.next = next;
 }

}

class Graph {

 Edges[] edges;
 int V;
 int E;

 public Graph(Edges[] edges, int V, int E) {
  this.edges = edges;
  this.V = V;
  this.E = E;
 }

}

class Edges {
 int src;
 int dest;
 int weight;

 public Edges(int src, int dest, int weight) {
  this.src = src;
  this.dest = dest;
  this.weight = weight;
 }

}